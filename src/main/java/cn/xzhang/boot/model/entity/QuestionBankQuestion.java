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
* 题库题目关联
* @TableName QuestionBankQuestion
* @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
*/
@TableName(value ="questionbankquestion")
@Data
@EqualsAndHashCode(callSuper = true)
public class QuestionBankQuestion extends BaseDO implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}