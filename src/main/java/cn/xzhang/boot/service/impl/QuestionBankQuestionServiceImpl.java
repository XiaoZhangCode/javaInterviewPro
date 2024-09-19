package cn.xzhang.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.mapper.QuestionBankQuestionMapper;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionAddReqDTO;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionPageReqDTO;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionUpdateReqDTO;
import cn.xzhang.boot.model.entity.QuestionBankQuestion;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionSimpleVo;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionVo;
import cn.xzhang.boot.service.QuestionBankQuestionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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



    /**
     * 添加新题库题目关联
     *
     * @param questionBankQuestionReqDTO 题库题目关联信息请求DTO
     * @return 添加成功返回题库题目关联id
     */
    @Override
    public long addQuestionBankQuestion(QuestionBankQuestionAddReqDTO questionBankQuestionReqDTO) {
        QuestionBankQuestion questionBankQuestion = new QuestionBankQuestion();
        BeanUtil.copyProperties(questionBankQuestionReqDTO, questionBankQuestion);
        if (!this.save(questionBankQuestion)) {
            throw exception(ADD_FAIL);
        }
        return questionBankQuestion.getId();
    }

    /**
     * 更新题库题目关联信息
     *
     * @param questionBankQuestionReqDTO 题库题目关联信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean updateQuestionBankQuestion(QuestionBankQuestionUpdateReqDTO questionBankQuestionReqDTO) {
        if (questionBankQuestionReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        QuestionBankQuestion questionBankQuestion = new QuestionBankQuestion();
        BeanUtil.copyProperties(questionBankQuestionReqDTO, questionBankQuestion);
        boolean b = this.updateById(questionBankQuestion);
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除题库题目关联
     *
     * @param id 题库题目关联id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteQuestionBankQuestion(Long id) {
        if (id == null) {
            throw exception(BAD_REQUEST);
        }
        boolean b = this.removeById(id);
        if (!b) {
            throw exception(DELETE_FAIL);
        }
        return true;
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

    /**
     * 获取题库题目关联分页信息
     *
     * @param questionBankQuestionPageReqDTO 题库题目关联分页请求DTO
     * @return 返回题库题目关联分页结果
     */
    @Override
    public PageResult<QuestionBankQuestionVo> getQuestionBankQuestionPage(QuestionBankQuestionPageReqDTO questionBankQuestionPageReqDTO) {
        PageResult<QuestionBankQuestion> pageResult = questionbankquestionMapper.selectPage(questionBankQuestionPageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<QuestionBankQuestionVo> questionBankQuestionVos = pageResult.getList().stream().map(questionBankQuestion -> {
            QuestionBankQuestionVo questionbankquestionVo = new QuestionBankQuestionVo();
            BeanUtil.copyProperties(questionBankQuestion, questionbankquestionVo);
            return questionbankquestionVo;
        }).collect(Collectors.toList());
        return new PageResult<>(questionBankQuestionVos, pageResult.getTotal());
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
            List<QuestionBankQuestion> questions = questionbankquestionMapper.selectList(
                    new LambdaQueryWrapper<QuestionBankQuestion>()
                            .eq(QuestionBankQuestion::getQuestionBankId, questionBanId)
            );
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
                        .eq(QuestionBankQuestion::getQuestionBankId, id)
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

}



