package cn.xzhang.boot.model.dto.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author codeZhang
 * @Date 2024/9/11 13:43
 */
@Data
@Schema(description = "题目审核请求")
public class QuestionReviewReqDTO {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "审核信息", requiredMode = Schema.RequiredMode.REQUIRED)
    private String reviewMessage;

    @Schema(description = "审核状态：1-通过, 2-拒绝")
    private Integer reviewStatus;

}
