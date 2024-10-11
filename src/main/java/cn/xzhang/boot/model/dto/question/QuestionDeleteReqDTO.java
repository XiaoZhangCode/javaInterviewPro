package cn.xzhang.boot.model.dto.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 题目删除请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "题目删除请求")
public class QuestionDeleteReqDTO{

    @Schema(description = "题目id列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "题目id列表不能为空")
    private List<Long> idList;

}
