package cn.xzhang.boot.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.hutool.core.collection.CollUtil;
import cn.xzhang.boot.common.pojo.CommonResult;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.constant.UserConstant;
import cn.xzhang.boot.model.dto.questionBank.QuestionBankAddReqDTO;
import cn.xzhang.boot.model.dto.questionBank.QuestionBankPageReqDTO;
import cn.xzhang.boot.model.dto.questionBank.QuestionBankUpdateReqDTO;
import cn.xzhang.boot.model.entity.Question;
import cn.xzhang.boot.model.entity.QuestionBank;
import cn.xzhang.boot.model.vo.questionBank.QuestionBankSimpleVo;
import cn.xzhang.boot.model.vo.questionBank.QuestionBankVo;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionVo;
import cn.xzhang.boot.service.QuestionBankQuestionService;
import cn.xzhang.boot.service.QuestionBankService;
import cn.xzhang.boot.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST_PARAMS;

/**
 * 题库表管理
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Tag(name = "题库表管理-QuestionBank")
@RestController
@RequestMapping("/questionBank")
public class QuestionBankController {

    @Resource
    private QuestionBankService questionbankService;

    @Resource
    private QuestionBankQuestionService questionBankQuestionService;

    @Resource
    private QuestionService questionService;

    /**
     * 创建QuestionBank
     *
     * @param questionBankAddReqDTO QuestionBank 添加请求数据传输对象，包含新增QuestionBank的信息
     * @return 返回操作结果，其中包含新添加QuestionBank的ID
     */
    @PostMapping("/add")
    @Operation(summary = "创建题库表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Long> addQuestionBank(@RequestBody QuestionBankAddReqDTO questionBankAddReqDTO) {
        if (questionBankAddReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，添加，并获取添加结果
        long result = questionbankService.addQuestionBank(questionBankAddReqDTO);
        // 返回添加成功响应结果
        return CommonResult.success(result);
    }

    @PutMapping("/update")
    @Operation(summary = "更新题库表信息")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<Boolean> updateQuestionBank(@RequestBody @Valid QuestionBankUpdateReqDTO questionbankReqDTO) {
        // 检查传入的请求数据是否为空
        if (questionbankReqDTO == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，更新信息，并获取更新结果
        boolean result = questionbankService.updateQuestionBank(questionbankReqDTO);
        // 返回更新信息成功响应结果
        return CommonResult.success(result);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除题库表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    @Parameter(name = "id", description = "题库表ID", required = true)
    public CommonResult<Boolean> deleteQuestionBank(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，删除
        boolean result = questionbankService.deleteQuestionBank(id);
        // 返回删除成功响应结果
        return CommonResult.success(result);
    }

    @GetMapping("/get")
    @Operation(summary = "获取题库表")
    @Parameter(name = "id", description = "题库表ID", required = true)
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<QuestionBankVo> getQuestionBank(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(questionbankService.getQuestionBankVO(questionbankService.getById(id)));
    }

    @GetMapping("/get/vo")
    @Operation(summary = "获取题库表简要信息")
    @Parameter(name = "id", description = "题库表ID", required = true)
    public CommonResult<QuestionBankSimpleVo> getQuestionBankVO(@RequestParam("id") Long id) {
        // 检查传入的ID是否为空
        if (id == null) {
            return CommonResult.error(BAD_REQUEST_PARAMS);
        }
        QuestionBank questionbank = questionbankService.getById(id);
        QuestionBankSimpleVo questionBankVO = questionbankService.getSimpleQuestionBankVO(questionbank);
        if (questionBankVO != null) {
            List<QuestionBankQuestionVo> questionBankVos = questionBankQuestionService.getListByQuestionBankId(questionBankVO.getId());
            if (CollUtil.isEmpty(questionBankVos)) {
                questionBankVO.setQuestionVoList(new ArrayList<>());
            }
            List<Long> questionIds = questionBankVos.stream().map(QuestionBankQuestionVo::getQuestionId).collect(Collectors.toList());
            List<Question> questions = questionService.selectListInIds(questionIds);
            if (CollUtil.isEmpty(questions)) {
                questionBankVO.setQuestionVoList(new ArrayList<>());
            }
            questionBankVO.setQuestionVoList(questions.stream().map(questionService::getQuestionVO).collect(Collectors.toList()));
        }
        // 调用服务层方法，获取信息，并返回结果
        return CommonResult.success(questionBankVO);
    }

    @GetMapping("/page")
    @Operation(summary = "分页获取题库表列表")
    @SaCheckRole(UserConstant.ADMIN_ROLE)
    public CommonResult<PageResult<QuestionBankVo>> getQuestionBankPage(QuestionBankPageReqDTO questionbankPageReqDTO) {
        // 调用服务层方法，获取分页信息，并返回结果
        return CommonResult.success(questionbankService.getQuestionBankPage(questionbankPageReqDTO));
    }

}