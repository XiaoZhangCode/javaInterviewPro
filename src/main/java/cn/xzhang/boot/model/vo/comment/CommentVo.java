package cn.xzhang.boot.model.vo.comment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


import java.io.Serializable;
import java.util.Date;

/**
 * 评论表VO
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Data
@Schema(description = "评论表VO")
public class CommentVo implements Serializable {

    @Schema(description = "id")
    private Long id;

    @Schema(description = "评论用户id")
    private Long userId;

    @Schema(description = "题目id")
    private Long questionId;

    @Schema(description = "评论内容")
    private String content;

    @Schema(description = "父评论id，用于回复功能",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long parentId;

    @Schema(description = "点赞数")
    private Integer likeNum;

    // ------------ 扩展 -----------

    @Schema(description = "当前登录用户是否点赞此评论")
    private Boolean isLiked = false;

    @Schema(description = "用户名称")
    private String username;

    @Schema(description = "用户头像")
    private String userAvatar;

    @Schema(description = "创建时间")
    private Date createTime;


    @Schema(description = "回复人名称")
    private String replyUserName;

    @Schema(description = "回复人头像")
    private String replyUserAvatar;



}
