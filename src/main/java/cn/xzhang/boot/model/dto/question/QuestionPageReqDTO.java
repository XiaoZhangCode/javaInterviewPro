package cn.xzhang.boot.model.dto.question;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.validation.constraints.NotNull;
import java.io.Serializable;

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



    @NotNull(message = "标题不能为空")
    @Schema(description = "标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;


    @NotNull(message = "内容不能为空")
    @Schema(description = "内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;


    @NotNull(message = "标签列表（json 数组）不能为空")
    @Schema(description = "标签列表（json 数组）",requiredMode = Schema.RequiredMode.REQUIRED)
    private String tags;


    @NotNull(message = "推荐答案不能为空")
    @Schema(description = "推荐答案",requiredMode = Schema.RequiredMode.REQUIRED)
    private String answer;


    @NotNull(message = "题目来源不能为空")
    @Schema(description = "题目来源",requiredMode = Schema.RequiredMode.REQUIRED)
    private String source;


    @Schema(description = "仅会员可见（1 表示仅会员可见）")
    private Byte needVip;


    @Schema(description = "浏览量")
    private Integer viewNum;


    @Schema(description = "点赞数")
    private Integer thumbNum;


    @Schema(description = "收藏数")
    private Integer favourNum;






}
