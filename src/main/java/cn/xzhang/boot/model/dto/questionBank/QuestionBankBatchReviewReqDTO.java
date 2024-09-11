package cn.xzhang.boot.model.dto.questionBank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 批量审核题库
 *
 * @author codeZhang
 * @Date 2024/9/11 13:50
 */
@Data
@Schema(description = "批量审核题库请求")
public class QuestionBankBatchReviewReqDTO {

    @Schema(description = "审核状态：0-待审核, 1-通过, 2-拒绝")
    @NotNull(message = "审核状态不能为空！")
    private Integer reviewStatus;

    @Schema(description = "审核信息")
    @NotBlank(message = "审核信息不能为空！")
    private String reviewMessage;

    @Schema(description = "题库id")
    @NotNull(message = "请选择正确的题库！")
    private List<Long> idList;

}
