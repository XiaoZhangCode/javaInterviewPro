package cn.xzhang.boot.model.dto.thumb;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 点赞表添加请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "点赞表添加请求")
public class ThumbAddReqDTO extends ThumbBaseDTO{


}
