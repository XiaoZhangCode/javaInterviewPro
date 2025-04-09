package cn.xzhang.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.mapper.ThumbMapper;
import cn.xzhang.boot.model.dto.thumb.ThumbAddReqDTO;
import cn.xzhang.boot.model.dto.thumb.ThumbPageReqDTO;
import cn.xzhang.boot.model.entity.Thumb;
import cn.xzhang.boot.model.enums.ThumbTargetTypeEnum;
import cn.xzhang.boot.model.vo.thumb.ThumbSimpleVo;
import cn.xzhang.boot.model.vo.thumb.ThumbVo;
import cn.xzhang.boot.service.CommentService;
import cn.xzhang.boot.service.QuestionService;
import cn.xzhang.boot.service.ThumbService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.LIKE_ERROR;
import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.USER_IS_LIKED;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【thumb(点赞表表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class ThumbServiceImpl extends ServiceImpl<ThumbMapper, Thumb> implements ThumbService {

    @Resource
    private ThumbMapper thumbMapper;

    @Resource
    private QuestionService questionService;

    @Resource
    private CommentService commentService;


    /**
     * 添加新点赞表
     *
     * @param thumbReqDTO 点赞表信息请求DTO
     * @return 添加成功返回点赞表id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public long addThumb(ThumbAddReqDTO thumbReqDTO) {
        // 校验userId和targetId
        Long userId = thumbReqDTO.getUserId();
        Long targetId = thumbReqDTO.getTargetId();
        Byte targetType = thumbReqDTO.getTargetType();
        Thumb thumb = new Thumb();
        BeanUtil.copyProperties(thumbReqDTO, thumb);
        synchronized (userId) {
            // 判断此用户是否已点赞
            boolean isThumb = isLiked(userId, targetId, targetType);
            if (isThumb) {
                throw exception(USER_IS_LIKED);
            }
            if (!this.save(thumb)) {
                throw exception(LIKE_ERROR);
            }
        }
        ThumbTargetTypeEnum thumbTargetTypeEnum = ThumbTargetTypeEnum.getEnumByValue(Byte.toUnsignedInt(targetType));
        switch (thumbTargetTypeEnum) {
            case QUESTION:
                // 更新题目点赞数
                questionService.updateQuestionFavoriteCount(targetId, true);
                break;
            case COMMENT:
                // 更新评论点赞数
                commentService.updateCommentFavoriteCount(targetId, true);
                break;
            default:
                break;
        }


        return thumb.getId();
    }

    /**
     * 将点赞表对象转换为点赞表VO对象
     *
     * @param thumb 点赞表对象
     * @return 返回点赞表VO对象
     */
    @Override
    public ThumbSimpleVo getSimpleThumbVO(Thumb thumb) {
        if (thumb == null) {
            return null;
        }
        ThumbSimpleVo thumbSimpleVo = new ThumbSimpleVo();
        BeanUtil.copyProperties(thumb, thumbSimpleVo);
        return thumbSimpleVo;
    }

    /**
     * 获取点赞表分页信息
     *
     * @param thumbPageReqDTO 点赞表分页请求DTO
     * @return 返回点赞表分页结果
     */
    @Override
    public PageResult<ThumbVo> getThumbPage(ThumbPageReqDTO thumbPageReqDTO) {
        PageResult<Thumb> pageResult = thumbMapper.selectPage(thumbPageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<ThumbVo> thumbVos = pageResult.getList().stream().map(thumb -> {
            ThumbVo thumbVo = new ThumbVo();
            BeanUtil.copyProperties(thumb, thumbVo);
            return thumbVo;
        }).collect(Collectors.toList());
        return new PageResult<>(thumbVos, pageResult.getTotal());
    }

    @Override
    public ThumbVo getThumbVO(Thumb thumb) {
        if (thumb == null) {
            return null;
        }
        ThumbVo thumbVo = new ThumbVo();
        BeanUtil.copyProperties(thumb, thumbVo);
        return thumbVo;
    }

    @Override
    public Thumb getThumbVOByUserIdAndQuestionId(Long userId, Long questionId) {
        if (Objects.isNull(userId) || Objects.isNull(questionId)) {
            return null;
        }
        return thumbMapper.selectOne(
                new LambdaQueryWrapper<Thumb>()
                        .eq(Thumb::getUserId, userId)
                        .eq(Thumb::getTargetId, questionId)
                        .eq(Thumb::getTargetType, 0)
        );
    }

    @Override
    public boolean isLiked(Long userId, Long targetId, Byte targetType) {
        if (Objects.isNull(userId) || Objects.isNull(targetId) || Objects.isNull(targetType)) {
            return false;
        }
        return thumbMapper.selectCount(
                new LambdaQueryWrapper<Thumb>()
                        .eq(Thumb::getUserId, userId)
                        .eq(Thumb::getTargetId, targetId)
                        .eq(Thumb::getTargetType, targetType)
        ) > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean unLike(ThumbAddReqDTO thumbReqDTO) {
        // 校验userId和targetId
        Long userId = thumbReqDTO.getUserId();
        Long targetId = thumbReqDTO.getTargetId();
        Byte targetType = thumbReqDTO.getTargetType();
        Thumb thumb = new Thumb();
        BeanUtil.copyProperties(thumbReqDTO, thumb);
        synchronized (userId) {
            // 判断此用户是否已点赞
            boolean isThumb = isLiked(userId, targetId, targetType);
            if (!isThumb) {
                return true;
            }
            // 删除对应的点赞信息
            int delete = thumbMapper.delete(
                    new LambdaQueryWrapper<Thumb>()
                            .eq(Thumb::getTargetId, targetId)
                            .eq(Thumb::getTargetType, targetType)
                            .eq(Thumb::getUserId, userId)
            );
            ThumbTargetTypeEnum thumbTargetTypeEnum = ThumbTargetTypeEnum.getEnumByValue(Byte.toUnsignedInt(targetType));
            switch (thumbTargetTypeEnum) {
                case QUESTION:
                    // 更新题目点赞数
                    questionService.updateQuestionFavoriteCount(targetId, false);
                    break;
                case COMMENT:
                    // 更新评论点赞数
                    commentService.updateCommentFavoriteCount(targetId, false);
                    break;
                default:
                    break;
            }
            return delete > 0;
        }
    }

    @Override
    public List<Thumb> getThumbList(Set<Long> ids, Integer type) {
        return thumbMapper.selectList(
                new LambdaQueryWrapper<Thumb>()
                        .in(Thumb::getTargetId, ids)
                        .eq(Thumb::getTargetType, type)
        );
    }

}



