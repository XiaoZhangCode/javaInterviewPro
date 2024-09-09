package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionAddReqDTO;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionPageReqDTO;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionUpdateReqDTO;
import cn.xzhang.boot.model.entity.QuestionBankQuestion;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionSimpleVo;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionVo;
import cn.xzhang.boot.service.QuestionBankQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;

/**
 * 题库题目关联管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "题库题目关联管理-QuestionBankQuestion")
@RestController
@RequestMapping("/questionBankQuestion")
public class QuestionBankQuestionController {

    @Resource
    private QuestionBankQuestionService questionbankquestionService;

    /**
     * 创建QuestionBankQuestion
     *
     * @param questionBankQuestionReqDTO QuestionBankQuestion添加请求数据传输对象，包含新增QuestionBankQuestion的信息
     * @return 返回操作结果，其中包含新添加QuestionBankQuestion的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建题库题目关联")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> addQuestionBankQuestion(@RequestBody QuestionBankQuestionAddReqDTO questionBankQuestionReqDTO) {
        if (questionBankQuestionReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = questionbankquestionService.addQuestionBankQuestion(questionBankQuestionReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新题库题目关联信息")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> updateQuestionBankQuestion(@RequestBody @Valid QuestionBankQuestionUpdateReqDTO questionBankQuestionReqDTO) {
        // 检查传入的请求数据是否为空
        if (questionBankQuestionReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = questionbankquestionService.updateQuestionBankQuestion(questionBankQuestionReqDTO);
        // 返回更新信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除题库题目关联")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "题库题目关联ID",required = true)
    public CommonResult<Boolean> deleteQuestionBankQuestion(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除
        boolean result = questionbankquestionService.deleteQuestionBankQuestion(id);
        // 返回删除成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取题库题目关联")
    @Parameter(name = "id", description = "题库题目关联ID",required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<QuestionBankQuestionVo> getQuestionBankQuestion(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(questionbankquestionService.getQuestionBankQuestionVO(questionbankquestionService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取题库题目关联简要信息")
    @Parameter(name = "id", description = "题库题目关联ID",required = true)
    public CommonResult<QuestionBankQuestionSimpleVo> getQuestionBankQuestionVO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        QuestionBankQuestion questionbankquestion = questionbankquestionService.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(questionbankquestionService.getSimpleQuestionBankQuestionVO(questionbankquestion));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取题库题目关联列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<QuestionBankQuestionVo>> getQuestionBankQuestionPage(QuestionBankQuestionPageReqDTO questionbankquestionPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(questionbankquestionService.getQuestionBankQuestionPage(questionbankquestionPageReqDTO));
    }

}