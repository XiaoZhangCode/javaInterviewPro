package cn.xzhang.boot.service;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.model.dto.comment.CommentAddReqDTO;
import cn.xzhang.boot.model.dto.comment.CommentPageReqDTO;
import cn.xzhang.boot.model.dto.comment.CommentUpdateReqDTO;
import cn.xzhang.boot.model.entity.Comment;
import cn.xzhang.boot.model.vo.comment.CommentSimpleVo;
import cn.xzhang.boot.model.vo.comment.CommentVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【comment(评论表表)】的数据库操作Service
 */
public interface CommentService extends IService<Comment> {

    /**
     * 添加评论表信息。
     *
     * @param commentReqDTO 评论表添加请求数据传输对象，包含要添加的评论表的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long addComment(CommentAddReqDTO commentReqDTO);

    /**
     * 更新评论表信息。
     *
     * @param commentReqDTO 包含评论表更新信息的请求DTO（数据传输对象）。该对象应包含需要更新的评论表属性。
     * @return boolean 返回true如果评论表信息更新成功，返回false如果更新失败或遇到错误。
     */
    boolean updateComment(CommentUpdateReqDTO commentReqDTO);

    /**
     * 删除评论表
     *
     * @param id 评论表的唯一标识符
     * @return boolean 返回操作是否成功。true表示删除成功，false表示删除失败。
     */
    boolean deleteComment(Long id);

    /**
     * 根据Comment对象获取对应的CommentVo对象。
     *
     * @param comment 一个包含评论表信息的Comment对象。
     * @return 返回一个包含评论表信息的CommentVo对象。
     */
    CommentSimpleVo getSimpleCommentVO(Comment comment);

    /**
     * 获取评论表页面信息
     *
     * @param commentPageReqDTO 包含分页和筛选条件的评论表请求数据传输对象
     * @return 返回评论表页面的结果，包括评论表列表和分页信息
     */
    PageResult<CommentVo> getCommentPage(CommentPageReqDTO commentPageReqDTO);

    /**
     * 根据Comment对象获取对应的CommentVo对象。
     *
     * @param comment 一个包含评论表信息的Comment对象。
     * @return 返回一个包含评论表信息的CommentVo对象。
     */
    CommentVo getCommentVO(Comment comment);

    void updateCommentFavoriteCount(Long commentId, boolean b);

    List<CommentVo> getCommentListByQuestionId(Long questionId);
}
