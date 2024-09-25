package cn.xzhang.boot.model.dto.question;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * 题目分页请求
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "题目分页请求")
@EqualsAndHashCode(callSuper = true)
public class QuestionPageReqDTO extends PageParam implements Serializable {



    @Schema(description = "id")
    private Long id;

    @Schema(description = "标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "审核状态",requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer reviewStatus;

    @Schema(description = "题库id")
    private Long questionBankId;


    @Schema(description = "题目列表")
    private List<Long> questionIdList;


}
