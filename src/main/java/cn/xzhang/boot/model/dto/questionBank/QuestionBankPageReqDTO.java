package cn.xzhang.boot.model.dto.questionBank;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 题库表分页请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "题库表分页请求")
@EqualsAndHashCode(callSuper = true)
public class QuestionBankPageReqDTO extends PageParam implements Serializable {



    @Schema(description = "id")
    private Long id;

    @NotNull(message = "标题不能为空")
    @Schema(description = "标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;


    @NotNull(message = "描述不能为空")
    @Schema(description = "描述",requiredMode = Schema.RequiredMode.REQUIRED)
    private String description;


    @Schema(description = "状态：0-待审核, 1-通过, 2-拒绝")
    private Integer reviewStatus;


}
