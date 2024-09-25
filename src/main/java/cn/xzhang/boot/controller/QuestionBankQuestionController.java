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
    @PostMapping("/bind")
    @Operation(summary = "绑定题库题目关联")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> bindQuestionBankQuestion(@RequestBody QuestionBankQuestionAddReqDTO questionBankQuestionReqDTO) {
        if (questionBankQuestionReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = questionbankquestionService.bindQuestionBankQuestion(questionBankQuestionReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PostMapping("/unbind")
    @Operation(summary = "解绑题库题目关联")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> unBindQuestionBankQuestion(@RequestBody QuestionBankQuestionAddReqDTO questionBankQuestionReqDTO) {
        if (questionBankQuestionReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = questionbankquestionService.unbindQuestionBankQuestion(questionBankQuestionReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }


}