package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.favourite.FavouriteAddReqDTO;
import cn.xzhang.boot.model.dto.favourite.FavouritePageReqDTO;
import cn.xzhang.boot.model.dto.favourite.FavouriteUpdateReqDTO;
import cn.xzhang.boot.model.entity.Favourite;
import cn.xzhang.boot.model.vo.favourite.FavouriteSimpleVo;
import cn.xzhang.boot.model.vo.favourite.FavouriteVo;
import cn.xzhang.boot.service.FavouriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;
import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.UNAUTHORIZED;

/**
 * 收藏表管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "管理后台 - 收藏表管理")
@RestController
@RequestMapping("/favourite")
public class FavouriteController {

    @Resource
    private FavouriteService favouriteService;

    /**
     * 创建Favourite
     *
     * @param favouriteReqDTO Favourite添加请求数据传输对象，包含新增Favourite的信息
     * @return 返回操作结果，其中包含新添加Favourite的ID
     */
    @PostMapping("/favourite")
    @Operation(summary = "收藏")
    @SaCheckLogin
    public CommonResult<Long> addFavourite(@RequestBody FavouriteAddReqDTO favouriteReqDTO) {
        if (favouriteReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 获取当前登录用户
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        if(loginIdAsLong==0L){
            return CommonResult.error(UNAUTHORIZED);
        }
        favouriteReqDTO.setUserId(loginIdAsLong);

        // 调用服务层方法，添加，并获取添加结果
        long result = favouriteService.addFavourite(favouriteReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PutMapping("/unFavourite")
    @Operation(summary = "取消收藏")
    @SaCheckLogin
    public CommonResult<Boolean> unFavourite(@RequestBody @Valid FavouriteAddReqDTO favouriteReqDTO) {
        // 检查传入的请求数据是否为空
        if (favouriteReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }

        // 获取当前登录用户
        long loginIdAsLong = StpUtil.getLoginIdAsLong();
        if(loginIdAsLong==0L){
            return CommonResult.error(UNAUTHORIZED);
        }
        favouriteReqDTO.setUserId(loginIdAsLong);
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = favouriteService.unFavourite(favouriteReqDTO);
        // 返回更新信息成功响应结果
        return CommonResult.success(result);
    }


}