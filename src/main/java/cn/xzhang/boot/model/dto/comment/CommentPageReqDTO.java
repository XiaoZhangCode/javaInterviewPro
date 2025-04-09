package cn.xzhang.boot.model.dto.comment;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 评论表分页请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "评论表分页请求")
@EqualsAndHashCode(callSuper = true)
public class CommentPageReqDTO extends PageParam implements Serializable {



    @Schema(description = "id")
    private Long id;



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
