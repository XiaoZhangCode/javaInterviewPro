package cn.xzhang.boot.model.dto.questionBank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author code_zhang
 * @Date 2024/10/11 19:20
 */
@Data
@Schema(description = "批量向题库添加题目请求")
public class QuestionBankQuestionBatchReqDTO {

    @Schema(description = "题目id列表")
    @NotNull(message = "题目列表不能为空")
    @NotEmpty(message = "题目列表不能为空")
    private List<Long> questionIds;

    @Schema(description = "题库id")
    @NotNull(message = "题库不能为空")
    private List<Long> questionBankIds;

}
