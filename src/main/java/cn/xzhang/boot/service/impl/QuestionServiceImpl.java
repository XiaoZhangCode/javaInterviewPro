package cn.xzhang.boot.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.mapper.QuestionBankQuestionMapper;
import cn.xzhang.boot.mapper.QuestionMapper;
import cn.xzhang.boot.model.dto.question.*;
import cn.xzhang.boot.model.entity.Question;
import cn.xzhang.boot.model.entity.QuestionBankQuestion;
import cn.xzhang.boot.model.vo.question.QuestionSimpleVo;
import cn.xzhang.boot.model.vo.question.QuestionVo;
import cn.xzhang.boot.service.QuestionService;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【question(题目表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Resource
    private QuestionMapper questionMapper;

    @Resource
    private QuestionBankQuestionMapper questionBankQuestionMapper;

    /**
     * 添加新题目
     *
     * @param questionReqDTO 题目信息请求DTO
     * @return 添加成功返回题目id
     */
    @Override
    public long addQuestion(QuestionAddReqDTO questionReqDTO) {
        Question question = new Question();
        BeanUtil.copyProperties(questionReqDTO, question, "tags");
        question.setTags(JSONUtil.toJsonStr(questionReqDTO.getTags()));
        if (!this.save(question)) {
            throw exception(ADD_FAIL);
        }
        return question.getId();
    }

    /**
     * 更新题目信息
     *
     * @param questionReqDTO 题目信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean updateQuestion(QuestionUpdateReqDTO questionReqDTO) {
        if (questionReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        Question question = new Question();
        BeanUtil.copyProperties(questionReqDTO, question, "tags");
        question.setTags(JSONUtil.toJsonStr(questionReqDTO.getTags()));
        boolean b = this.updateById(question);
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除题目
     *
     * @param id 题目id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteQuestion(Long id) {
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
     * 将题目对象转换为题目VO对象
     *
     * @param question 题目对象
     * @return 返回题目VO对象
     */
    @Override
    public QuestionSimpleVo getSimpleQuestionVO(Question question) {
        if (question == null) {
            return null;
        }
        QuestionSimpleVo questionSimpleVo = new QuestionSimpleVo();
        BeanUtil.copyProperties(question, questionSimpleVo, "tags");
        questionSimpleVo.setTags(JSONUtil.toList(question.getTags(), String.class));
        return questionSimpleVo;
    }

    /**
     * 获取题目分页信息
     *
     * @param questionPageReqDTO 题目分页请求DTO
     * @return 返回题目分页结果
     */
    @Override
    public PageResult<QuestionVo> getQuestionPage(QuestionPageReqDTO questionPageReqDTO) {
        Long questionBankId = questionPageReqDTO.getQuestionBankId();
        if (ObjectUtil.isNotEmpty(questionBankId)) {
            List<Long> questionIds =  questionBankQuestionMapper.selectListByBankId(questionBankId);
            if (CollectionUtil.isNotEmpty(questionIds)) {
                questionPageReqDTO.setQuestionIdList(questionIds);
            } else {
                return new PageResult<>(new ArrayList<>(), 0L);
            }
        }
        PageResult<Question> pageResult = questionMapper.selectPage(questionPageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<QuestionVo> questionVos = pageResult.getList().stream().map(question -> {
            QuestionVo questionVo = new QuestionVo();
            BeanUtil.copyProperties(question, questionVo, "tags");
            questionVo.setTags(JSONUtil.toList(question.getTags(), String.class));
            return questionVo;
        }).collect(Collectors.toList());
        return new PageResult<>(questionVos, pageResult.getTotal());
    }

    @Override
    public QuestionVo getQuestionVO(Question question) {
        if (question == null) {
            return null;
        }
        QuestionVo questionVo = new QuestionVo();
        BeanUtil.copyProperties(question, questionVo, "tags");
        questionVo.setTags(JSONUtil.toList(question.getTags(), String.class));
        return questionVo;
    }

    @Override
    public List<Question> selectListInIds(List<Long> questionIds) {
        return this.listByIds(questionIds);
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public Boolean reviewQuestion(QuestionReviewReqDTO reviewReqDTO) {
        long reviewUserId = StpUtil.getLoginIdAsLong();
        Long id = reviewReqDTO.getId();
        String reviewMessage = reviewReqDTO.getReviewMessage();
        Integer reviewStatus = reviewReqDTO.getReviewStatus();
        Question question = questionMapper.selectById(id);
        if (ObjectUtil.isEmpty(question)) {
            throw exception(QUESTION_BANK_NOT_EXISTS);
        }
        int updated = questionMapper.update(null,
                Wrappers.lambdaUpdate(Question.class)
                        .set(Question::getReviewMessage, reviewMessage)
                        .set(Question::getReviewStatus, reviewStatus)
                        .set(Question::getReviewerId, reviewUserId)
                        .set(Question::getReviewTime, DateUtil.now())
                        .eq(Question::getId, id)
        );
        return updated > 0;
    }

    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public Boolean reviewQuestionBatch(QuestionBatchReviewReqDTO reviewReqDTO) {
        long reviewUserId = StpUtil.getLoginIdAsLong();
        Integer reviewStatus = reviewReqDTO.getReviewStatus();
        String reviewMessage = reviewReqDTO.getReviewMessage();
        List<Long> idList = reviewReqDTO.getIdList();
        int updated = questionMapper.update(null,
                Wrappers.lambdaUpdate(Question.class)
                        .set(Question::getReviewMessage, reviewMessage)
                        .set(Question::getReviewStatus, reviewStatus)
                        .set(Question::getReviewerId, reviewUserId)
                        .set(Question::getReviewTime, DateUtil.now())
                        .in(Question::getId, idList)
        );
        return updated > 0;
    }

    @Override
    public PageResult<QuestionVo> getUserQuestionPage(UserQuestionPageReqDTO reqDTO) {
        PageResult<Question> pageResult;
        if (ObjectUtil.isNotEmpty(reqDTO.getQuestionBankId())) {
           List<Long> questionIds =  questionBankQuestionMapper.selectListByBankId(reqDTO.getQuestionBankId());
            if (CollectionUtil.isNotEmpty(questionIds)) {
                pageResult = questionMapper.selectUserPageByBankId(reqDTO, questionIds);
            } else {
               return new PageResult<>(new ArrayList<>(), 0L);
            }
        } else {
            pageResult = questionMapper.selectUserPage(reqDTO);

        }
        if (ObjectUtil.isEmpty(pageResult) || ObjectUtil.isEmpty(pageResult.getList())) {
            return PageResult.empty();
        }
        List<QuestionVo> questionVos = pageResult.getList().stream().map(question -> {
            QuestionVo questionVo = new QuestionVo();
            BeanUtil.copyProperties(question, questionVo, "tags");
            questionVo.setTags(JSONUtil.toList(question.getTags(), String.class));
            return questionVo;
        }).collect(Collectors.toList());
        return new PageResult<>(questionVos, pageResult.getTotal());
    }

}



