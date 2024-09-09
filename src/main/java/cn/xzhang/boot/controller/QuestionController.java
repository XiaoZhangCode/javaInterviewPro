package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.hutool.core.collection.CollUtil;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.question.QuestionAddReqDTO;
import cn.xzhang.boot.model.dto.question.QuestionPageReqDTO;
import cn.xzhang.boot.model.dto.question.QuestionUpdateReqDTO;
import cn.xzhang.boot.model.entity.Question;
import cn.xzhang.boot.model.vo.question.QuestionSimpleVo;
import cn.xzhang.boot.model.vo.question.QuestionVo;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionVo;
import cn.xzhang.boot.service.QuestionBankQuestionService;
import cn.xzhang.boot.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;
import static cn.xzhang.boot.common.pojo.CommonResult.success;

/**
 * 题目管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "题目管理-Question")
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionBankQuestionService questionBankQuestionService;


    /**
     * 创建Question
     *
     * @param questionReqDTO Question添加请求数据传输对象，包含新增Question的信息
     * @return 返回操作结果，其中包含新添加Question的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建题目")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> addQuestion(@RequestBody QuestionAddReqDTO questionReqDTO) {
        if (questionReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = questionService.addQuestion(questionReqDTO);
        // 返回添加成功响应结果
        return success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新题目信息")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> updateQuestion(@RequestBody @Valid QuestionUpdateReqDTO questionReqDTO) {
        // 检查传入的请求数据是否为空
        if (questionReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = questionService.updateQuestion(questionReqDTO);
        // 返回更新信息成功响应结果
        return success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除题目")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "题目ID", required = true)
    public CommonResult<Boolean> deleteQuestion(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除
        boolean result = questionService.deleteQuestion(id);
        // 返回删除成功响应结果
        return success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取题目")
    @Parameter(name = "id", description = "题目ID", required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<QuestionVo> getQuestion(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return success(questionService.getQuestionVO(questionService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取题目简要信息")
    @Parameter(name = "id", description = "题目ID", required = true)
    public CommonResult<QuestionSimpleVo> getQuestionVO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        Question question = questionService.getById(id);
        // 调用服务层方法，获取信息，并返回结果
        return success(questionService.getSimpleQuestionVO(question));
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取题目列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<QuestionVo>> getQuestionPage(QuestionPageReqDTO questionPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return success(questionService.getQuestionPage(questionPageReqDTO));
    }


    @GetMapping("/list")
    @Operation(summary = "根据题库编号获取题目信息")
    @Parameter(name = "questionBankId", description = "题库id", required = true)
    public CommonResult<List<QuestionVo>> getQuestionList(@RequestParam("questionBankId") Long questionBankId) {
        List<QuestionBankQuestionVo> questionBankQuestionVo = questionBankQuestionService.getListByQuestionBankId(questionBankId);
        if (CollUtil.isEmpty(questionBankQuestionVo)) {
            return success(new ArrayList<>());
        }
        List<Long> questionIds = questionBankQuestionVo.stream().map(QuestionBankQuestionVo::getQuestionId).collect(Collectors.toList());
        List<Question> questions = questionService.selectListInIds(questionIds);
        if (CollUtil.isEmpty(questions)) {
            return success(new ArrayList<>());
        }
        return success(questions.stream().map(questionService::getQuestionVO).collect(Collectors.toList()));
    }


    @PutMapping("/updateQuestionBank")
    @Operation(summary = "修改题目所属题库")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameters({
            @Parameter(name = "id", description = "题目id", required = true),
            @Parameter(name = "questionBankId", description = "题目所属题库id", required = true)
    })
    public CommonResult<Boolean> updateQuestionBank(@RequestParam("id") Long id, @RequestParam("questionBankId") Long questionBankId) {
        questionBankQuestionService.updateQuestionBank(id, questionBankId);
        return success(true);
    }


}