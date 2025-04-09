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
* 点赞表
* @TableName Thumb
* @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
*/
@TableName(value ="thumb")
@Data
@EqualsAndHashCode(callSuper = true)
public class Thumb extends BaseDO implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "目标id（题目id或评论id）")
    private Long targetId;

    @Schema(description = "目标类型：0-题目，1-评论")
    private Byte targetType;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}