package cn.xzhang.boot.model.dto.thumb;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * 点赞表基础信息类
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(name = "点赞表基础信息类", description = "点赞表基础信息")
public class ThumbBaseDTO implements Serializable {

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "目标id（题目id或评论id）")
    @NotNull(message = "目标Id不可为空")
    private Long targetId;

    @Schema(description = "目标类型：0-题目，1-评论")
    @NotNull(message = "目标类型不可为空")
    private Byte targetType;


}
