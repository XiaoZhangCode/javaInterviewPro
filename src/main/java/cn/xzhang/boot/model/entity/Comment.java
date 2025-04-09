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
* 评论表
* @TableName Comment
* @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
*/
@TableName(value ="comment")
@Data
@EqualsAndHashCode(callSuper = true)
public class Comment extends BaseDO implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    @Schema(description = "评论用户id")
    private Long userId;

    @Schema(description = "题目id")
    private Long questionId;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "父评论id，用于回复功能",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long parentId;

    @Schema(description = "点赞数")
    private Integer likeNum;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}