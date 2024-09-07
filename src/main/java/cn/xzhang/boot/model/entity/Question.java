package cn.xzhang.boot.model.entity;

import cn.xzhang.boot.common.pojo.BaseDO;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;

/**
* 题目
* @TableName Question
* @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
*/
@TableName(value ="question")
@Data
@EqualsAndHashCode(callSuper = true)
public class Question extends BaseDO implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    @Schema(description = "标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @Schema(description = "标签列表（json 数组）",requiredMode = Schema.RequiredMode.REQUIRED)
    private String tags;

    @Schema(description = "推荐答案",requiredMode = Schema.RequiredMode.REQUIRED)
    private String answer;

    @Schema(description = "题目来源",requiredMode = Schema.RequiredMode.REQUIRED)
    private String source;

    @Schema(description = "仅会员可见（1 表示仅会员可见）")
    private Byte needVip;

    @Schema(description = "浏览量")
    private Integer viewNum;

    @Schema(description = "点赞数")
    private Integer thumbNum;

    @Schema(description = "收藏数")
    private Integer favourNum;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}