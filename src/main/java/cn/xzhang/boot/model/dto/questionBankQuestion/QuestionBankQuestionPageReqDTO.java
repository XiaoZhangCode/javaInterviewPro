package cn.xzhang.boot.model.dto.questionBankQuestion;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;

/**
 * 题库题目关联分页请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "题库题目关联分页请求")
@EqualsAndHashCode(callSuper = true)
public class QuestionBankQuestionPageReqDTO extends PageParam implements Serializable {



    @Schema(description = "id")
    private Long id;



    @Schema(description = "题库 id")
    private Long questionBankId;


    @Schema(description = "题目 id")
    private Long questionId;


    @Schema(description = "题目顺序（题号）")
    private Integer questionOrder;


    @Schema(description = "创建用户 id")
    private Long userId;



}
