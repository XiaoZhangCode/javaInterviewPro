package cn.xzhang.boot.service;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionAddReqDTO;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionPageReqDTO;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionUpdateReqDTO;
import cn.xzhang.boot.model.entity.QuestionBankQuestion;
import cn.xzhang.boot.model.entity.User;
import cn.xzhang.boot.model.vo.questionBank.QuestionBankVo;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionSimpleVo;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【questionBankQuestion(题库题目关联表)】的数据库操作Service
 */
public interface QuestionBankQuestionService extends IService<QuestionBankQuestion> {

    /**
     * 绑定题库题目关联信息。
     *
     * @param questionBankQuestionReqDTO 题库题目关联添加请求数据传输对象，包含要添加的题库题目关联的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long bindQuestionBankQuestion(QuestionBankQuestionAddReqDTO questionBankQuestionReqDTO);
    /**
     * 根据QuestionBankQuestion对象获取对应的QuestionBankQuestionVo对象。
     *
     * @param questionBankQuestion 一个包含题库题目关联信息的QuestionBankQuestion对象。
     * @return 返回一个包含题库题目关联信息的QuestionBankQuestionVo对象。
     */
    QuestionBankQuestionSimpleVo getSimpleQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion);

    /**
     * 根据QuestionBankQuestion对象获取对应的QuestionBankQuestionVo对象。
     *
     * @param questionBankQuestion 一个包含题库题目关联信息的QuestionBankQuestion对象。
     * @return 返回一个包含题库题目关联信息的QuestionBankQuestionVo对象。
     */
    QuestionBankQuestionVo getQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion);

    /**
     * 根据题库id获取关联的题目id
     *
     * @param questionBanId 题库id
     * @return 关联的题目id
     */
    List<QuestionBankQuestionVo> getListByQuestionBankId(Long questionBanId);

    /**
     * 修改题目所属题库
     * @param id 题目id
     * @param questionBankId 题库id
     */
    void updateQuestionBank(Long id, Long questionBankId);

    /**
     * 根据题目id获取关联的题库id
     *
     * @param id 题目id
     * @return 关联的题库id
     */
    List<QuestionBankVo> getListByQuestionId(Long id);

    /**
     * 解绑题库题目关联信息。
     *
     * @param questionBankQuestionReqDTO 题库题目关联添加请求数据传输对象，包含要解绑的题库题目关联的所有信息。
     * @return 返回解绑操作的结果，true表示解绑成功，false表示解绑失败。
     */
    long unbindQuestionBankQuestion(QuestionBankQuestionAddReqDTO questionBankQuestionReqDTO);

    /**
     * 批量添加题目到题库
     * @param questionIds 题目id列表
     * @param questionBankIds 题库id列表
     * @param user 登录用户
     */
    void batchAddQuestionsToBank(List<Long> questionIds, List<Long> questionBankIds, User user);

    /**
     * 批量移除题目到题库
     * @param questionIdList 题目id列表
     * @param questionBankIds 题库id列表
     */
    void batchRemoveQuestionsFromBank(List<Long> questionIdList, List<Long> questionBankIds);
}
