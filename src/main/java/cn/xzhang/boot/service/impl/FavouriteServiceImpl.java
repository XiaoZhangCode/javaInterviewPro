package cn.xzhang.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.mapper.FavouriteMapper;
import cn.xzhang.boot.mapper.QuestionMapper;
import cn.xzhang.boot.model.dto.favourite.FavouriteAddReqDTO;
import cn.xzhang.boot.model.dto.favourite.FavouritePageReqDTO;
import cn.xzhang.boot.model.dto.favourite.FavouriteUpdateReqDTO;
import cn.xzhang.boot.model.entity.Favourite;
import cn.xzhang.boot.model.vo.favourite.FavouriteSimpleVo;
import cn.xzhang.boot.model.vo.favourite.FavouriteVo;
import cn.xzhang.boot.service.FavouriteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【favourite(收藏表表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class FavouriteServiceImpl extends ServiceImpl<FavouriteMapper, Favourite> implements FavouriteService {

    @Resource
    private FavouriteMapper favouriteMapper;

    @Resource
    private QuestionMapper questionMapper;

    /**
     * 添加新收藏表
     *
     * @param favouriteReqDTO 收藏表信息请求DTO
     * @return 添加成功返回收藏表id
     */
    @Override
    public long addFavourite(FavouriteAddReqDTO favouriteReqDTO) {
        Long userId = favouriteReqDTO.getUserId();
        Long questionId = favouriteReqDTO.getQuestionId();
        Favourite favourite = new Favourite();
        BeanUtil.copyProperties(favouriteReqDTO, favourite);

        synchronized (userId) {
            // 获取当前用户是否已经收藏
            Favourite favourite1 = this.getFavouriteVOByUserIdAndQuestionId(userId, questionId);
            if (Objects.nonNull(favourite1)) {
                throw exception(ALREADY_FAVOURITE);
            }
            if (!this.save(favourite)) {
                throw exception(ADD_FAIL);
            }

            // 更新题目收藏数量
            questionMapper.updateFavoriteCountAdd(questionId);


        }

        return favourite.getId();
    }


    @Override
    public Favourite getFavouriteVOByUserIdAndQuestionId(Long userId, Long questionId) {
        if (Objects.isNull(userId) || Objects.isNull(questionId)) {
            return null;
        }
        return favouriteMapper.selectOne(Favourite::getUserId, userId, Favourite::getQuestionId, questionId);
    }

    @Override
    public boolean unFavourite(FavouriteAddReqDTO favouriteReqDTO) {
        Long userId = favouriteReqDTO.getUserId();
        Long questionId = favouriteReqDTO.getQuestionId();
        // 判断当前登录用户是否已收藏
        Favourite favourite = this.getFavouriteVOByUserIdAndQuestionId(userId, questionId);
        if (Objects.nonNull(favourite)) {
            // 删除收藏记录
            if (this.removeById(favourite.getId())) {
                // 更新题目收藏数量
                questionMapper.updateFavoriteCountReduce(questionId);
                return true;
            }
        }
        return true;
    }

}



