package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.thumb.ThumbAddReqDTO;
import cn.xzhang.boot.model.dto.thumb.ThumbPageReqDTO;
import cn.xzhang.boot.model.dto.thumb.ThumbUpdateReqDTO;
import cn.xzhang.boot.model.entity.Thumb;
import cn.xzhang.boot.model.vo.thumb.ThumbSimpleVo;
import cn.xzhang.boot.model.vo.thumb.ThumbVo;
import cn.xzhang.boot.service.ThumbService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.Objects;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;
import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;

/**
 * 点赞表管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "管理后台 - 点赞表管理")
@RestController
@RequestMapping("/thumb")
public class ThumbController {

    @Resource
    private ThumbService thumbService;

    /**
     * 点赞Thumb
     *
     * @param thumbReqDTO Thumb添加请求数据传输对象，包含新增Thumb的信息
     * @return 返回操作结果，其中包含新添加Thumb的ID
     */
    @PostMapping("/like")
    @Operation(summary = "点赞")
    @SaCheckLogin
    public CommonResult<Long> addThumb(@RequestBody ThumbAddReqDTO thumbReqDTO) {
        if (thumbReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 获取当前登录用户
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        if(loginIdAsLong==0L){
            return CommonResult.error(UNAUTHORIZED);
        }
        thumbReqDTO.setUserId(loginIdAsLong);

        // 调用服务层方法，添加，并获取添加结果
        long result = thumbService.addThumb(thumbReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PostMapping("/unLike")
    @Operation(summary = "取消点赞")
    @SaCheckLogin
    public CommonResult<Boolean> unLike(@RequestBody ThumbAddReqDTO thumbReqDTO) {
        // 检查传入的ID是否为空
        if (thumbReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 获取当前登录用户
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        if(loginIdAsLong==0L){
            return CommonResult.error(UNAUTHORIZED);
        }
        thumbReqDTO.setUserId(loginIdAsLong);
        // 调用服务层方法，删除
        boolean result = thumbService.unLike(thumbReqDTO);
        // 返回删除成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取点赞表")
    @Parameter(name = "id", description = "点赞表ID",required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<ThumbVo> getThumb(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(thumbService.getThumbVO(thumbService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取点赞表简要信息")
    @Parameter(name = "id", description = "点赞表ID",required = true)
    public CommonResult<ThumbSimpleVo> getThumbVO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        Thumb thumb = thumbService.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(thumbService.getSimpleThumbVO(thumb));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取点赞表列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<ThumbVo>> getThumbPage(ThumbPageReqDTO thumbPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(thumbService.getThumbPage(thumbPageReqDTO));
    }

}