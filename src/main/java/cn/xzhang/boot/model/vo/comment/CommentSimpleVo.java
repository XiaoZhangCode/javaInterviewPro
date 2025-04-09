package cn.xzhang.boot.model.vo.comment;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.io.Serializable;

/**
 * 评论表信息Vo
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "评论表简要信息VO")
public class CommentSimpleVo implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "评论用户id")
    private Long userId;

    @Schema(description = "题目id")
    private Long questionId;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "父评论id，用于回复功能",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long parentId;

    @Schema(description = "点赞数")
    private Integer likeNum;


}
