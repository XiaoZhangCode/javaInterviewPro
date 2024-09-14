package cn.xzhang.boot.model.dto.question;

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
@Schema(description = "批量审核题目请求")
public class QuestionBatchReviewReqDTO {

    @Schema(description = "审核状态： 1-通过, 2-拒绝")
    @NotNull(message = "审核状态不能为空！")
    private Integer reviewStatus;

    @Schema(description = "审核信息")
    @NotBlank(message = "审核信息不能为空！")
    private String reviewMessage;

    @Schema(description = "题目id")
    @NotNull(message = "请选择正确的题目！")
    private List<Long> idList;

}
