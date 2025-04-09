package cn.xzhang.boot.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.mapper.CommentMapper;
import cn.xzhang.boot.model.dto.comment.CommentAddReqDTO;
import cn.xzhang.boot.model.dto.comment.CommentPageReqDTO;
import cn.xzhang.boot.model.dto.comment.CommentUpdateReqDTO;
import cn.xzhang.boot.model.entity.Comment;
import cn.xzhang.boot.model.entity.Thumb;
import cn.xzhang.boot.model.enums.ThumbTargetTypeEnum;
import cn.xzhang.boot.model.vo.comment.CommentSimpleVo;
import cn.xzhang.boot.model.vo.comment.CommentVo;
import cn.xzhang.boot.model.vo.user.UserVo;
import cn.xzhang.boot.service.CommentService;
import cn.xzhang.boot.service.ThumbService;
import cn.xzhang.boot.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【comment(评论表表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Resource
    private CommentMapper commentMapper;

    @Resource
    @Lazy
    private ThumbService thumbService;

    @Resource
    private UserService userService;


    /**
     * 添加新评论表
     *
     * @param commentReqDTO 评论表信息请求DTO
     * @return 添加成功返回评论表id
     */
    @Override
    public long addComment(CommentAddReqDTO commentReqDTO) {
        Comment comment = new Comment();
        BeanUtil.copyProperties(commentReqDTO, comment);
        if (!this.save(comment)) {
            throw exception(ADD_FAIL);
        }
        return comment.getId();
    }

    /**
     * 更新评论表信息
     *
     * @param commentReqDTO 评论表信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean updateComment(CommentUpdateReqDTO commentReqDTO) {
        if (commentReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        Comment comment = new Comment();
        BeanUtil.copyProperties(commentReqDTO, comment);
        boolean b = this.updateById(comment);
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除评论表
     *
     * @param id 评论表id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteComment(Long id) {
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
     * 将评论表对象转换为评论表VO对象
     *
     * @param comment 评论表对象
     * @return 返回评论表VO对象
     */
    @Override
    public CommentSimpleVo getSimpleCommentVO(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentSimpleVo commentSimpleVo = new CommentSimpleVo();
        BeanUtil.copyProperties(comment, commentSimpleVo);
        return commentSimpleVo;
    }

    /**
     * 获取评论表分页信息
     *
     * @param commentPageReqDTO 评论表分页请求DTO
     * @return 返回评论表分页结果
     */
    @Override
    public PageResult<CommentVo> getCommentPage(CommentPageReqDTO commentPageReqDTO) {
        PageResult<Comment> pageResult = commentMapper.selectPage(commentPageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<CommentVo> commentVos = pageResult.getList().stream().map(comment -> {
            CommentVo commentVo = new CommentVo();
            BeanUtil.copyProperties(comment, commentVo);
            return commentVo;
        }).collect(Collectors.toList());
        return new PageResult<>(commentVos, pageResult.getTotal());
    }

    @Override
    public CommentVo getCommentVO(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentVo commentVo = new CommentVo();
        BeanUtil.copyProperties(comment, commentVo);
        return commentVo;
    }

    @Override
    public void updateCommentFavoriteCount(Long commentId, boolean b) {
        if (b) {
            commentMapper.updateLikeNumAdd(commentId);
        } else {
            commentMapper.updateLikeNumReduce(commentId);
        }
    }

    @Override
    public List<CommentVo> getCommentListByQuestionId(Long questionId) {
        List<CommentVo> commentVoList = commentMapper.selectList(Comment::getQuestionId, questionId).stream()
                .map(comment -> {
                    CommentVo commentVo = new CommentVo();
                    BeanUtil.copyProperties(comment, commentVo);
                    return commentVo;
                })
                .sorted(Comparator.comparing(CommentVo::getCreateTime,
                        Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());


        // 获取当前登录用户
        long loginUserId = StpUtil.getLoginIdAsLong();
        if (loginUserId == 0L) {
            return commentVoList;
        }

        // 获取所有的评论Ids
        Set<Long> commentIds = commentVoList.stream().map(CommentVo::getId).collect(Collectors.toSet());
        if (CollUtil.isEmpty(commentIds)) {
            return commentVoList;
        }

        // 根据评论ID获取映射到Map
        Map<Long, CommentVo> commentVoMap = commentVoList.stream().collect(Collectors.toMap(CommentVo::getId, Function.identity()));

        // 查询根据评论Id查询点赞表
        List<Thumb> thumbs = thumbService.getThumbList(commentIds, ThumbTargetTypeEnum.COMMENT.getValue());

        // 构建评论ID -> 点赞用户ID集合的映射
        Map<Long, Set<Long>> commentLikedMap = thumbs.stream()
                .collect(Collectors.groupingBy(
                        Thumb::getTargetId,
                        Collectors.mapping(Thumb::getUserId, Collectors.toSet())
                ));

        // 设置是否点赞状态
        for (CommentVo commentVo : commentVoList) {
            Set<Long> likedUserIds = commentLikedMap.getOrDefault(commentVo.getId(), Collections.emptySet());
            commentVo.setIsLiked(likedUserIds.contains(loginUserId));
        }


        // 获取所有评论的用户Id
        Set<Long> userIds = commentVoList.stream().map(CommentVo::getUserId).collect(Collectors.toSet());
        if (CollUtil.isEmpty(userIds)) {
            return commentVoList;
        }
        List<UserVo> userVoList = userService.getUserList(userIds);


        Map<Long, UserVo> userVoMap = userVoList.stream().collect(Collectors.toMap(UserVo::getId, Function.identity()));
        commentVoList.forEach(commentVo -> Optional.ofNullable(userVoMap.get(commentVo.getUserId())).ifPresent(userVo -> {
            commentVo.setUsername(userVo.getUserName());
            commentVo.setUserAvatar(userVo.getUserAvatar());

            if (commentVo.getParentId() != 0) {
                CommentVo parentComment = commentVoMap.get(commentVo.getParentId());
                if (parentComment == null) {
                    return;
                }
                Long userId = parentComment.getUserId();
                if(userId==null){
                    return;
                }
                UserVo userVo1 = userVoMap.get(userId);
                if (userVo1 == null) {
                    return;
                }
                commentVo.setReplyUserName(userVo1.getUserName());
                commentVo.setReplyUserAvatar(userVo1.getUserAvatar());
            }
        }));


        return commentVoList;
    }

}



