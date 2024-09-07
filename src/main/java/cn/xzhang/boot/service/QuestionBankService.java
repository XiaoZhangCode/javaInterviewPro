package cn.xzhang.boot.service;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.model.dto.questionBank.QuestionBankAddReqDTO;
import cn.xzhang.boot.model.dto.questionBank.QuestionBankPageReqDTO;
import cn.xzhang.boot.model.dto.questionBank.QuestionBankUpdateReqDTO;
import cn.xzhang.boot.model.entity.QuestionBank;
import cn.xzhang.boot.model.vo.questionBank.QuestionBankSimpleVo;
import cn.xzhang.boot.model.vo.questionBank.QuestionBankVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【questionBank(题库表表)】的数据库操作Service
 */
public interface QuestionBankService extends IService<QuestionBank> {

    /**
     * 添加题库表信息。
     *
     * @param questionBankReqDTO 题库表添加请求数据传输对象，包含要添加的题库表的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long addQuestionBank(QuestionBankAddReqDTO questionBankReqDTO);

    /**
     * 更新题库表信息。
     *
     * @param questionBankReqDTO 包含题库表更新信息的请求DTO（数据传输对象）。该对象应包含需要更新的题库表属性。
     * @return boolean 返回true如果题库表信息更新成功，返回false如果更新失败或遇到错误。
     */
    boolean updateQuestionBank(QuestionBankUpdateReqDTO questionBankReqDTO);

    /**
     * 删除题库表
     *
     * @param id 题库表的唯一标识符
     * @return boolean 返回操作是否成功。true表示删除成功，false表示删除失败。
     */
    boolean deleteQuestionBank(Long id);

    /**
     * 根据QuestionBank对象获取对应的QuestionBankVo对象。
     *
     * @param questionBank 一个包含题库表信息的QuestionBank对象。
     * @return 返回一个包含题库表信息的QuestionBankVo对象。
     */
    QuestionBankSimpleVo getSimpleQuestionBankVO(QuestionBank questionBank);

    /**
     * 获取题库表页面信息
     *
     * @param questionBankPageReqDTO 包含分页和筛选条件的题库表请求数据传输对象
     * @return 返回题库表页面的结果，包括题库表列表和分页信息
     */
    PageResult<QuestionBankVo> getQuestionBankPage(QuestionBankPageReqDTO questionBankPageReqDTO);

    /**
     * 根据QuestionBank对象获取对应的QuestionBankVo对象。
     *
     * @param questionBank 一个包含题库表信息的QuestionBank对象。
     * @return 返回一个包含题库表信息的QuestionBankVo对象。
     */
    QuestionBankVo getQuestionBankVO(QuestionBank questionBank);
}
