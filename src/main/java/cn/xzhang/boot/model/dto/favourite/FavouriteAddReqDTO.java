package cn.xzhang.boot.model.dto.favourite;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 收藏表添加请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "收藏表添加请求")
public class FavouriteAddReqDTO extends FavouriteBaseDTO{


}
