package cn.xzhang.boot.model.dto.question;

import cn.xzhang.boot.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 用户题目请求
 *
 * @author codeZhang
 * @Date 2024/9/19 17:27
 */
@Data
@Schema(description = "用户题目分页请求")
@EqualsAndHashCode(callSuper = true)
public class UserQuestionPageReqDTO extends PageParam implements Serializable {

    @Schema(description = "标题")
    private String title;

    @Schema(description = "是否仅会员可见")
    private Integer needVip;

    @Schema(description = "标签")
    private String tag;

    @Schema(description = "题库id")
    private Long questionBankId;

}
