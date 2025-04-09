package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.comment.CommentPageReqDTO;
import cn.xzhang.boot.model.entity.Comment;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Select;

import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【comment(评论表表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface CommentMapper extends BaseMapperPlus<Comment> {

    default PageResult<Comment> selectPage(CommentPageReqDTO commentPageReqDTO) {
        return selectPage(commentPageReqDTO, new LambdaQueryWrapper<Comment>()
                .eq(Objects.nonNull(commentPageReqDTO.getContent()), Comment::getContent, commentPageReqDTO.getContent())
                .orderByDesc(Comment::getCreateTime)

        );
    }

    @Select("update comment set likeNum = likeNum + 1 where id = #{commentId}")
    void updateLikeNumAdd(Long commentId);

    @Select("update comment set likeNum = likeNum - 1 where id = #{commentId}")
    void updateLikeNumReduce(Long commentId);
}




