package cn.xzhang.boot.model.vo.questionBank;

import cn.xzhang.boot.model.vo.question.QuestionVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.List;

/**
 * 题库表VO
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "题库表VO")
public class QuestionBankVo implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "描述",requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @Schema(description = "图片",requiredMode = Schema.RequiredMode.REQUIRED)
    private String picture;

    @Schema(description = "浏览量")
    private Integer viewNum;

    @Schema(description = "优先级")
    private Integer priority;

    @Schema(description = "题目信息")
    private List<QuestionVo> questionList;

}
