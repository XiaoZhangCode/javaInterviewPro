package cn.xzhang.boot.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.mapper.QuestionBankMapper;
import cn.xzhang.boot.mapper.QuestionBankQuestionMapper;
import cn.xzhang.boot.mapper.QuestionMapper;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionAddReqDTO;
import cn.xzhang.boot.model.entity.Question;
import cn.xzhang.boot.model.entity.QuestionBank;
import cn.xzhang.boot.model.entity.QuestionBankQuestion;
import cn.xzhang.boot.model.entity.User;
import cn.xzhang.boot.model.vo.questionBank.QuestionBankVo;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionSimpleVo;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionVo;
import cn.xzhang.boot.service.QuestionBankQuestionService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【questionBankQuestion(题库题目关联表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class QuestionBankQuestionServiceImpl extends ServiceImpl<QuestionBankQuestionMapper, QuestionBankQuestion> implements QuestionBankQuestionService {

    @Resource
    private QuestionBankQuestionMapper questionbankquestionMapper;
    @Autowired
    private QuestionBankMapper questionBankMapper;
    @Autowired
    private QuestionMapper questionMapper;


    /**
     * 添加新题库题目关联
     *
     * @param questionBankQuestionReqDTO 题库题目关联信息请求DTO
     * @return 添加成功返回题库题目关联id
     */
    @Override
    public long bindQuestionBankQuestion(QuestionBankQuestionAddReqDTO questionBankQuestionReqDTO) {
        questionBankQuestionReqDTO.setUserId(StpUtil.getLoginIdAsLong());
        QuestionBankQuestion questionBankQuestion = new QuestionBankQuestion();
        BeanUtil.copyProperties(questionBankQuestionReqDTO, questionBankQuestion);
        if (!questionbankquestionMapper.saveBind(questionBankQuestion)) {
            throw exception(ADD_FAIL);
        }
        return questionBankQuestion.getId();
    }


    /**
     * 将题库题目关联对象转换为题库题目关联VO对象
     *
     * @param questionBankQuestion 题库题目关联对象
     * @return 返回题库题目关联VO对象
     */
    @Override
    public QuestionBankQuestionSimpleVo getSimpleQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion) {
        if (questionBankQuestion == null) {
            return null;
        }
        QuestionBankQuestionSimpleVo questionbankquestionSimpleVo = new QuestionBankQuestionSimpleVo();
        BeanUtil.copyProperties(questionBankQuestion, questionbankquestionSimpleVo);
        return questionbankquestionSimpleVo;
    }


    @Override
    public QuestionBankQuestionVo getQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion) {
        if (questionBankQuestion == null) {
            return null;
        }
        QuestionBankQuestionVo questionbankquestionVo = new QuestionBankQuestionVo();
        BeanUtil.copyProperties(questionBankQuestion, questionbankquestionVo);
        return questionbankquestionVo;
    }

    @Override
    public List<QuestionBankQuestionVo> getListByQuestionBankId(Long questionBanId) {
        if (questionBanId != null) {
            List<QuestionBankQuestion> questions = questionbankquestionMapper.selectAllListByBankId(questionBanId);
            if (CollUtil.isEmpty(questions)) {
                return Collections.emptyList();
            }
            return questions.stream().map(item -> {
                QuestionBankQuestionVo questionBankVo = new QuestionBankQuestionVo();
                BeanUtil.copyProperties(item, questionBankVo);
                return questionBankVo;
            }).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public void updateQuestionBank(Long id, Long questionBankId) {
        QuestionBankQuestion bankQuestion = questionbankquestionMapper.selectOne(
                new LambdaQueryWrapper<QuestionBankQuestion>()
                        .eq(QuestionBankQuestion::getQuestionId, id)
                        .eq(QuestionBankQuestion::getQuestionBankId, questionBankId)
        );
        if (bankQuestion == null) {
            QuestionBankQuestion bankQuestion1 = new QuestionBankQuestion();
            bankQuestion1.setQuestionBankId(questionBankId);
            bankQuestion1.setQuestionId(id);
            questionbankquestionMapper.insert(bankQuestion1);
            return;
        }
        if (bankQuestion.getQuestionBankId().equals(questionBankId)) {
            return;
        }
        bankQuestion.setQuestionBankId(questionBankId);
        questionbankquestionMapper.updateById(bankQuestion);
    }

    @Override
    public List<QuestionBankVo> getListByQuestionId(Long id) {
        if (id == null) {
            return Collections.emptyList();
        }
        List<Long> questionBanksIds = questionbankquestionMapper.selectListByQuestionId(id);
        if (CollUtil.isEmpty(questionBanksIds)) {
            return Collections.emptyList();
        }
        List<QuestionBank> questionBanks = questionBankMapper.selectList(QuestionBank::getId, questionBanksIds);
        return questionBanks.stream().map(questionBank -> {
            QuestionBankVo questionBankVo = new QuestionBankVo();
            BeanUtil.copyProperties(questionBank, questionBankVo);
            return questionBankVo;
        }).collect(Collectors.toList());
    }

    @Override
    public long unbindQuestionBankQuestion(QuestionBankQuestionAddReqDTO reqDTO) {
        if (reqDTO == null) {
            return 0;
        }
        if (reqDTO.getQuestionBankId() != null && reqDTO.getQuestionId() != null) {
            reqDTO.setUserId(StpUtil.getLoginIdAsLong());
            return questionbankquestionMapper.unbind(reqDTO);
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public void batchAddQuestionsToBank(List<Long> questionIdList, Long questionBankId, User loginUser) {
        // 参数校验
        // 检查题目 id 是否存在
        List<Question> questionList = questionMapper.selectList(
                Wrappers.lambdaQuery(Question.class)
                        .select(Question::getId)
                        .in(Question::getId, questionIdList)
        );
        // 合法的题目 id
        List<Long> validQuestionIdList = questionList.stream()
                .map(Question::getId)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(validQuestionIdList)) {
            throw exception(BAD_REQUEST_PARAMS_ERROR, "题目列表不合法!");
        }
        // 检查题库 id 是否存在
        QuestionBank questionBank = questionBankMapper.selectById(questionBankId);
        if (ObjectUtil.isEmpty(questionBank)) {
            throw exception(BAD_REQUEST_PARAMS_ERROR, "题库不存在!");
        }
        // 执行插入
        for (Long questionId : validQuestionIdList) {
            QuestionBankQuestion questionBankQuestion = new QuestionBankQuestion();
            questionBankQuestion.setQuestionBankId(questionBankId);
            questionBankQuestion.setQuestionId(questionId);
            questionBankQuestion.setUserId(loginUser.getId());
            boolean result = this.save(questionBankQuestion);
            if (!result) {
                throw exception(CUSTOMER_INTERNAL_SERVER_ERROR, "向题库添加题目失败");
            }
        }
    }

    @Override
    public void batchRemoveQuestionsFromBank(List<Long> questionIdList, Long questionBankId) {
        // 检查题目 id 是否存在
        List<Question> questionList = questionMapper.selectList(
                Wrappers.lambdaQuery(Question.class)
                        .select(Question::getId)
                        .in(Question::getId, questionIdList)
        );
        // 合法的题目 id
        List<Long> validQuestionIdList = questionList.stream()
                .map(Question::getId)
                .collect(Collectors.toList());
        if (CollUtil.isEmpty(validQuestionIdList)) {
            throw exception(BAD_REQUEST_PARAMS_ERROR, "题目列表不合法!");
        }
        // 检查题库 id 是否存在
        QuestionBank questionBank = questionBankMapper.selectById(questionBankId);
        if (ObjectUtil.isEmpty(questionBank)) {
            throw exception(BAD_REQUEST_PARAMS_ERROR, "题库不存在!");
        }
        // 执行插入
        for (Long questionId : validQuestionIdList) {
            Wrapper<QuestionBankQuestion> wrapper = Wrappers.lambdaQuery(QuestionBankQuestion.class)
                    .eq(QuestionBankQuestion::getQuestionBankId, questionBankId)
                    .eq(QuestionBankQuestion::getQuestionId, questionId);
            boolean result = this.remove(wrapper);
            if (!result) {
                throw exception(CUSTOMER_INTERNAL_SERVER_ERROR, "向题库移除题目失败");
            }
        }

    }

}



