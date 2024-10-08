package cn.xzhang.boot.model.dto.questionBank;

import org.springframework.format.annotation.DateTimeFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 题库表基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "题库表基础信息类", description = "题库表基础信息")
public class QuestionBankBaseDTO implements Serializable {

    @NotNull(message = "标题不能为空")
    @Schema(description = "标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotNull(message = "描述不能为空")
    @Schema(description = "描述",requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;

    @NotNull(message = "图片不能为空")
    @Schema(description = "图片",requiredMode = Schema.RequiredMode.REQUIRED)
    private String picture;

    @Schema(description = "状态：0-待审核, 1-通过, 2-拒绝")
    private Integer reviewStatus;

    @NotNull(message = "审核信息不能为空")
    @Schema(description = "审核信息",requiredMode = Schema.RequiredMode.REQUIRED)
    private String reviewMessage;

    @NotNull(message = "审核人 id不能为空")
    @Schema(description = "审核人 id",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long reviewerId;

    @NotNull(message = "审核时间不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "审核时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private java.util.Date reviewTime;

    @Schema(description = "浏览量")
    private Integer viewNum;

    @Schema(description = "优先级")
    private Integer priority;


}
