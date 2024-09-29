package cn.xzhang.boot.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.RedisConstant;
import cn.xzhang.boot.mapper.UserMapper;
import cn.xzhang.boot.model.dto.user.UserAddReqDTO;
import cn.xzhang.boot.model.dto.user.UserPageReqDTO;
import cn.xzhang.boot.model.dto.user.UserProfileUpdateReqDTO;
import cn.xzhang.boot.model.dto.user.UserUpdateReqDTO;
import cn.xzhang.boot.model.entity.User;
import cn.xzhang.boot.model.enums.UserStatusEnum;
import cn.xzhang.boot.model.vo.user.LoginUserVO;
import cn.xzhang.boot.model.vo.user.UserSimpleVo;
import cn.xzhang.boot.model.vo.user.UserVo;
import cn.xzhang.boot.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBitSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【user(用户表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * 盐值，用于混淆密码
     */
    public static final String SALT = "codeZhang";

    @Resource
    private UserMapper userMapper;

    @Resource
    private RedissonClient redissonClient;


    /**
     * 注册用户
     *
     * @param userAccount   用户账号，用于标识一个用户。
     * @param userPassword  用户密码，用于用户身份验证。
     * @param checkPassword 确认密码，用于验证用户输入的密码是否一致。
     * @return 返回注册结果，通常情况下，成功返回一个用户id。
     */
    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 校验输入参数
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw exception(BAD_REQUEST);
        }
        if (userAccount.length() < 4) {
            throw exception(BAD_REQUEST);
        }
        // 密码和确认密码是否相同
        if (!userPassword.equals(checkPassword)) {
            throw exception(PASSWORD_NOT_MATCH);
        }
        if (userPassword.length() < 8) {
            throw exception(PASSWORD_LENGTH_NOT_ENOUGH);
        }
        synchronized (userAccount.intern()) {
            // 账号重复性检查
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getUserAccount, userAccount);
            long count = this.baseMapper.selectCount(queryWrapper);
            if (count > 0) {
                throw exception(USER_NAME_REPEAT);
            }
            // 对密码进行加密
            String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
            // 创建新用户并保存
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserPassword(encryptPassword);
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw exception(USER_REGISTER_FAIL);
            }
            return user.getId();
        }
    }

    /**
     * 用户登录
     *
     * @param userAccount  用户账号。
     * @param userPassword 用户密码。
     * @return 登录成功返回用户信息的VO对象。
     */
    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword) {
        // 校验输入参数
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw exception(BAD_REQUEST);
        }
        if (userAccount.length() < 4) {
            throw exception(BAD_REQUEST);
        }
        if (userPassword.length() < 8) {
            throw exception(BAD_REQUEST);
        }
        // 对密码进行加密并验证用户是否存在
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        User user = this.baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserAccount, userAccount).eq(User::getUserPassword, encryptPassword));
        if (user == null) {
            throw exception(USER_NOT_EXIST);
        }
        if (!UserStatusEnum.isNormal(user.getUserStatus())) {
            throw exception(USER_NOT_NORMAL);
        }
        StpUtil.login(user.getId());
        return this.getLoginUserVO(user);
    }

    /**
     * 获取当前登录的用户
     *
     * @return 如果用户已登录，返回登录的用户对象；否则返回null。
     */
    @Override
    public User getLoginUser() {
        if (StpUtil.isLogin()) {
            return this.getById(StpUtil.getLoginIdAsLong());
        }
        return null;
    }

    /**
     * 将用户对象转换为登录用户VO对象
     *
     * @param user 用户对象
     * @return 返回登录用户VO对象
     */
    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            throw exception(UNAUTHORIZED);
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtil.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    /**
     * 添加新用户
     *
     * @param userReqDTO 用户信息请求DTO
     * @return 添加成功返回用户id
     */
    @Override
    public synchronized long addUser(UserAddReqDTO userReqDTO) {
        if (StrUtil.isBlank(userReqDTO.getUserPassword())) {
            userReqDTO.setUserPassword(DigestUtils.md5DigestAsHex((SALT + "123456789").getBytes()));
        }
        // 校验输入参数
        if (StringUtils.isAnyBlank(userReqDTO.getUserAccount(), userReqDTO.getUserPassword(), userReqDTO.getUserName())) {
            throw exception(BAD_REQUEST);
        }
        // 校验账号是否重复
        if (this.baseMapper.selectCount(new LambdaQueryWrapper<User>().eq(User::getUserAccount, userReqDTO.getUserAccount())) > 0) {
            throw exception(USER_NAME_REPEAT);
        }
        User user = new User();
        BeanUtil.copyProperties(userReqDTO, user);
        if (!this.save(user)) {
            throw exception(ADD_FAIL);
        }
        return user.getId();
    }

    /**
     * 更新用户信息
     *
     * @param userReqDTO 用户信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean updateUser(UserUpdateReqDTO userReqDTO) {
        // 校验输入参数
        if (StringUtils.isAnyBlank(userReqDTO.getUserAccount(), userReqDTO.getUserName())) {
            throw exception(BAD_REQUEST);
        }
        if (userReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        User user = new User();
        BeanUtil.copyProperties(userReqDTO, user);
        // 判断用户账号是否已存在
        if (this.baseMapper.selectCount(new LambdaQueryWrapper<User>()
                .eq(User::getUserAccount, user.getUserAccount())
                .ne(User::getId, user.getId())) > 0) {
            throw exception(USER_NAME_REPEAT);
        }
        boolean b = this.updateById(user);
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteUser(Long id) {
        if (id == null) {
            throw exception(BAD_REQUEST);
        }
        boolean b = this.removeById(id);
        if (!b) {
            throw exception(DELETE_FAIL);
        }
        return true;
    }

    /**
     * 将用户对象转换为用户VO对象
     *
     * @param user 用户对象
     * @return 返回用户VO对象
     */
    @Override
    public UserSimpleVo getSimpleUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserSimpleVo userSimpleVo = new UserSimpleVo();
        BeanUtil.copyProperties(user, userSimpleVo);
        return userSimpleVo;
    }

    /**
     * 获取用户分页信息
     *
     * @param userPageReqDTO 用户分页请求DTO
     * @return 返回用户分页结果
     */
    @Override
    public PageResult<UserVo> getUserPage(UserPageReqDTO userPageReqDTO) {
        PageResult<User> pageResult = userMapper.selectPage(userPageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<UserVo> userVos = pageResult.getList().stream().map(user -> {
            UserVo userVo = new UserVo();
            BeanUtil.copyProperties(user, userVo);
            return userVo;
        }).collect(Collectors.toList());
        return new PageResult<>(userVos, pageResult.getTotal());
    }

    /**
     * 更新用户个人资料
     *
     * @param userProfileUpdateReqDTO 用户个人资料更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean updateUserProfile(UserProfileUpdateReqDTO userProfileUpdateReqDTO) {
        if (userProfileUpdateReqDTO == null) {
            throw exception(BAD_REQUEST);
        }
        long userId = StpUtil.getLoginIdAsLong();
        User user = new User();
        user.setId(userId);
        BeanUtil.copyProperties(userProfileUpdateReqDTO, user);
        if (this.updateById(user)) {
            return true;
        }
        throw exception(UPDATE_FAIL);
    }

    @Override
    public UserVo getUserVO(User user) {
        if (user == null) {
            return null;
        }
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo);
        return userVo;
    }

    @Override
    public boolean resetUserPassword(Long userId) {
        User user = userMapper.selectOne(User::getId, userId);
        if (user == null) {
            throw exception(USER_NOT_EXIST);
        }
        user.setUserPassword(DigestUtils.md5DigestAsHex((SALT + "123456789").getBytes()));
        return this.updateById(user);
    }

    @Override
    public List<UserVo> getUserList(Collection<Long> userIds) {
        List<User> userList = userMapper.selectList(User::getId, userIds);
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    /**
     * 添加用户签到记录
     *
     * @param userId 用户签到
     * @return 当前是否已签到成功
     */
    public boolean addUserSignIn(long userId) {
        LocalDate date = LocalDate.now();
        String key = RedisConstant.getUserSignInRedisKey(date.getYear(), userId);
        RBitSet signInBitSet = redissonClient.getBitSet(key);
        // 获取当前日期是一年中的第几天，作为偏移量（从 1 开始计数）
        int offset = date.getDayOfYear();
        // 检查当天是否已经签到
        if (!signInBitSet.get(offset)) {
            // 如果当天还未签到，则设置
            return signInBitSet.set(offset, true);
        }
        // 当天已签到
        return true;
    }



    @Override
    public Map<LocalDate, Boolean> getUserSignInRecord(long userId, Integer year) {
        if (year == null) {
            LocalDate date = LocalDate.now();
            year = date.getYear();
        }
        String key = RedisConstant.getUserSignInRedisKey(year, userId);
        RBitSet signInBitSet = redissonClient.getBitSet(key);
        // LinkedHashMap 保证有序
        Map<LocalDate, Boolean> result = new LinkedHashMap<>();
        // 获取当前年份的总天数
        int totalDays = Year.of(year).length();
        // 依次获取每一天的签到状态
        for (int dayOfYear = 1; dayOfYear <= totalDays; dayOfYear++) {
            // 获取 key：当前日期
            LocalDate currentDate = LocalDate.ofYearDay(year, dayOfYear);
            // 获取 value：当天是否有刷题
            boolean hasRecord = signInBitSet.get(dayOfYear);
            // 将结果放入 map
            result.put(currentDate, hasRecord);
        }
        return result;
    }

    @Override
    public List<Integer> getUserSignInRecordOptimize(long userId, Integer year) {
        if (year == null) {
            LocalDate date = LocalDate.now();
            year = date.getYear();
        }
        String key = RedisConstant.getUserSignInRedisKey(year, userId);
        RBitSet signInBitSet = redissonClient.getBitSet(key);
        // 加载 BitSet 到内存中，避免后续读取时发送多次请求
        BitSet bitSet = signInBitSet.asBitSet();
        // 统计签到的日期
        List<Integer> dayList = new ArrayList<>();
        // 获取当前年份的总天数
        int totalDays = Year.of(year).length();
        // 依次获取每一天的签到状态
        for (int dayOfYear = 1; dayOfYear <= totalDays; dayOfYear++) {
            // 获取 value：当天是否有刷题
            boolean hasRecord = bitSet.get(dayOfYear);
            if (hasRecord) {
                dayList.add(dayOfYear);
            }
        }
        return dayList;
    }

    @Override
    public List<Integer> getUserSignInRecordFinal(long userId, Integer year) {
        if (year == null) {
            LocalDate date = LocalDate.now();
            year = date.getYear();
        }
        String key = RedisConstant.getUserSignInRedisKey(year, userId);
        RBitSet signInBitSet = redissonClient.getBitSet(key);
        // 加载 BitSet 到内存中，避免后续读取时发送多次请求
        BitSet bitSet = signInBitSet.asBitSet();
        // 统计签到的日期
        List<Integer> dayList = new ArrayList<>();
        // 从索引 0 开始查找下一个被设置为 1 的位
        int index = bitSet.nextSetBit(0);
        while (index >= 0) {
            dayList.add(index);
            // 查找下一个被设置为 1 的位
            index = bitSet.nextSetBit(index + 1);
        }
        return dayList;
    }



}



