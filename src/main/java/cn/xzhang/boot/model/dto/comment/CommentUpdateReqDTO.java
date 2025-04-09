package cn.xzhang.boot.model.dto.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

/**
 * 评论表添加请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "评论表添加请求")
@Valid
public class CommentUpdateReqDTO extends CommentBaseDTO{

    @Schema(description = "id" )
    @NotBlank(message = "id不能为空")
    private Long id;

}
