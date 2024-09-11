package cn.xzhang.boot.model.entity;

import cn.xzhang.boot.common.pojo.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;

/**
* 题库表
* @TableName QuestionBank
* @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
*/
@TableName(value ="question_bank")
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionBank extends BaseDO implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}