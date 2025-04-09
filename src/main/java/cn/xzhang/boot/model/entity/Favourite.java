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
* 收藏表
* @TableName Favourite
* @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
*/
@TableName(value ="favourite")
@Data
@EqualsAndHashCode(callSuper = true)
public class Favourite extends BaseDO implements Serializable {


    @TableId(type = IdType.ASSIGN_ID)
    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "题目id")
    private Long questionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}