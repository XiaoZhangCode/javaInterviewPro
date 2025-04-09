package cn.xzhang.boot.model.vo.thumb;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.io.Serializable;

/**
 * 点赞表VO
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "点赞表VO")
public class ThumbVo implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "用户id")
    private Long userId;

    @Schema(description = "目标id（题目id或评论id）")
    private Long targetId;

    @Schema(description = "目标类型：0-题目，1-评论")
    private Byte targetType;


}
