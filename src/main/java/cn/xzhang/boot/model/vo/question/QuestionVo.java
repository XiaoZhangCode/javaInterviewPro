package cn.xzhang.boot.model.vo.question;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 题目VO
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "题目VO")
public class QuestionVo implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "标题",requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @Schema(description = "内容",requiredMode = Schema.RequiredMode.REQUIRED)
    private String content;

    @Schema(description = "标签列表（json 数组）",requiredMode = Schema.RequiredMode.REQUIRED)
    private List<String> tags;

    @Schema(description = "推荐答案",requiredMode = Schema.RequiredMode.REQUIRED)
    private String answer;

    @Schema(description = "题目来源",requiredMode = Schema.RequiredMode.REQUIRED)
    private String source;

    @Schema(description = "仅会员可见（1 表示仅会员可见）")
    private Boolean needVip;

    @Schema(description = "浏览量")
    private Integer viewNum;

    @Schema(description = "点赞数")
    private Integer thumbNum;

    @Schema(description = "收藏数")
    private Integer favourNum;

    @Schema(description = "审核状态")
    private Integer reviewStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "审核时间",requiredMode = Schema.RequiredMode.REQUIRED)
    private Date reviewTime;

    @Schema(description = "审核信息",requiredMode = Schema.RequiredMode.REQUIRED)
    private String reviewMessage;

    @Schema(description = "审核人")
    private String reviewer;

    @Schema(description = "审核人id")
    private Long reviewerId;

    @Schema(description = "创建人id")
    private String creator;

    @Schema(description = "创建人名称")
    private String creatorName;


}
