package cn.xzhang.boot.model.dto.questionBankQuestion;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;


/**
 * 题库题目关联基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "题库题目关联基础信息类", description = "题库题目关联基础信息")
public class QuestionBankQuestionBaseDTO implements Serializable {

    @Schema(description = "题库 id")
    private Long questionBankId;

    @Schema(description = "题目 id")
    private Long questionId;

    @Schema(description = "题目顺序（题号）")
    private Integer questionOrder = 0;

    @Schema(description = "创建用户 id")
    private Long userId;


}
