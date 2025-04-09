package cn.xzhang.boot.model.dto.favourite;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 收藏表基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "收藏表基础信息类", description = "收藏表基础信息")
public class FavouriteBaseDTO implements Serializable {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "题目id")
    @NotNull(message = "题目id不能为空")
    private Long questionId;


}
