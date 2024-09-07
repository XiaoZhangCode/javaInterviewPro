package cn.xzhang.boot.service;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionAddReqDTO;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionPageReqDTO;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionUpdateReqDTO;
import cn.xzhang.boot.model.entity.QuestionBankQuestion;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionSimpleVo;
import cn.xzhang.boot.model.vo.questionbankquestion.QuestionBankQuestionVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【questionBankQuestion(题库题目关联表)】的数据库操作Service
 */
public interface QuestionBankQuestionService extends IService<QuestionBankQuestion> {

    /**
     * 添加题库题目关联信息。
     *
     * @param questionBankQuestionReqDTO 题库题目关联添加请求数据传输对象，包含要添加的题库题目关联的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long addQuestionBankQuestion(QuestionBankQuestionAddReqDTO questionBankQuestionReqDTO);

    /**
     * 更新题库题目关联信息。
     *
     * @param questionBankQuestionReqDTO 包含题库题目关联更新信息的请求DTO（数据传输对象）。该对象应包含需要更新的题库题目关联属性。
     * @return boolean 返回true如果题库题目关联信息更新成功，返回false如果更新失败或遇到错误。
     */
    boolean updateQuestionBankQuestion(QuestionBankQuestionUpdateReqDTO questionBankQuestionReqDTO);

    /**
     * 删除题库题目关联
     *
     * @param id 题库题目关联的唯一标识符
     * @return boolean 返回操作是否成功。true表示删除成功，false表示删除失败。
     */
    boolean deleteQuestionBankQuestion(Long id);

    /**
     * 根据QuestionBankQuestion对象获取对应的QuestionBankQuestionVo对象。
     *
     * @param questionBankQuestion 一个包含题库题目关联信息的QuestionBankQuestion对象。
     * @return 返回一个包含题库题目关联信息的QuestionBankQuestionVo对象。
     */
    QuestionBankQuestionSimpleVo getSimpleQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion);

    /**
     * 获取题库题目关联页面信息
     *
     * @param questionBankQuestionPageReqDTO 包含分页和筛选条件的题库题目关联请求数据传输对象
     * @return 返回题库题目关联页面的结果，包括题库题目关联列表和分页信息
     */
    PageResult<QuestionBankQuestionVo> getQuestionBankQuestionPage(QuestionBankQuestionPageReqDTO questionBankQuestionPageReqDTO);

    /**
     * 根据QuestionBankQuestion对象获取对应的QuestionBankQuestionVo对象。
     *
     * @param questionBankQuestion 一个包含题库题目关联信息的QuestionBankQuestion对象。
     * @return 返回一个包含题库题目关联信息的QuestionBankQuestionVo对象。
     */
    QuestionBankQuestionVo getQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion);
}
