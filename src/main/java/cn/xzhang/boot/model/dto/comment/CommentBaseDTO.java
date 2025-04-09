package cn.xzhang.boot.model.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 评论表基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "评论表基础信息类", description = "评论表基础信息")
public class CommentBaseDTO implements Serializable {

    @Schema(description = "评论用户id")
    private Long userId;

    @Schema(description = "题目id")
    private Long questionId;

    @NotEmpty(message = "评论内容不能为空")
    @Schema(description = "评论内容")
    private String content;

    @NotNull(message = "父评论id，用于回复功能不能为空")
    @Schema(description = "父评论id，用于回复功能",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long parentId;

    @Schema(description = "点赞数")
    private Integer likeNum;



}
