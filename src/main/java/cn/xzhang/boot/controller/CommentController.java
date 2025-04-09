package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.comment.CommentAddReqDTO;
import cn.xzhang.boot.model.dto.comment.CommentPageReqDTO;
import cn.xzhang.boot.model.dto.comment.CommentUpdateReqDTO;
import cn.xzhang.boot.model.entity.Comment;
import cn.xzhang.boot.model.vo.comment.CommentSimpleVo;
import cn.xzhang.boot.model.vo.comment.CommentVo;
import cn.xzhang.boot.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.List;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;
import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;

/**
 * 评论表管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "管理后台 - 评论表管理")
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Resource
    private CommentService commentService;

    /**
     * 创建Comment
     *
     * @param commentReqDTO Comment添加请求数据传输对象，包含新增Comment的信息
     * @return 返回操作结果，其中包含新添加Comment的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建评论表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> addComment(@RequestBody CommentAddReqDTO commentReqDTO) {
        if (commentReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 获取当前登录用户
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        if(loginIdAsLong==0L){
            return CommonResult.error(UNAUTHORIZED);
        }
        commentReqDTO.setUserId(loginIdAsLong);
        // 调用服务层方法，添加，并获取添加结果
        long result = commentService.addComment(commentReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }



    @GetMapping("/get")
    @Operation(summary = "获取评论表")
    @Parameter(name = "id", description = "评论表ID",required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<CommentVo> getComment(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(commentService.getCommentVO(commentService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取评论表简要信息")
    @Parameter(name = "id", description = "评论表ID",required = true)
    public CommonResult<CommentSimpleVo> getCommentVO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        Comment comment = commentService.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(commentService.getSimpleCommentVO(comment));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取评论表列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<CommentVo>> getCommentPage(CommentPageReqDTO commentPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(commentService.getCommentPage(commentPageReqDTO));
    }

    // 根据题目Id 获取评论列表
    @GetMapping("/get/list")
    @Operation(summary = "根据题目Id 获取评论列表")
    @Parameter(name = "questionId", description = "题目Id",required = true)
    public CommonResult<List<CommentVo>> getCommentListByQuestionId(@RequestParam("questionId") Long questionId){
        if(questionId==null){
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        return CommonResult.success(commentService.getCommentListByQuestionId(questionId));
    }



}