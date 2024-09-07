package cn.xzhang.boot.model.dto.questionBankQuestion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 题库题目关联添加请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "题库题目关联添加请求")
public class QuestionBankQuestionAddReqDTO extends QuestionBankQuestionBaseDTO{


}
