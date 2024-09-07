package cn.xzhang.boot.model.vo.questionBank;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;

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

    @Schema(description = "状态：0-待审核, 1-通过, 2-拒绝")
    private Integer reviewStatus;

    @Schema(description = "审核信息",requiredMode = Schema.RequiredMode.REQUIRED)
    private String reviewMessage;

    @Schema(description = "审核人 id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long reviewerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "审核时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private java.util.Date reviewTime;

    @Schema(description = "浏览量")
    private Integer viewNum;

    @Schema(description = "优先级")
    private Integer priority;

}
