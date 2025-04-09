package cn.xzhang.boot.model.dto.favourite;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


import java.io.Serializable;

/**
 * 收藏表分页请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "收藏表分页请求")
@EqualsAndHashCode(callSuper = true)
public class FavouritePageReqDTO extends PageParam implements Serializable {



    @Schema(description = "id")
    private Long id;



    @Schema(description = "用户id")
    private Long userId;


    @Schema(description = "题目id")
    private Long questionId;

}
