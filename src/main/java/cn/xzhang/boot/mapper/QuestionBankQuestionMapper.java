package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.questionBankQuestion.QuestionBankQuestionPageReqDTO;
import cn.xzhang.boot.model.entity.QuestionBankQuestion;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author XiaoZhangCode
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【questionbankquestion(题库题目关联表)】的数据库操作Mapper
 */
public interface QuestionBankQuestionMapper extends BaseMapperPlus<QuestionBankQuestion> {

    default PageResult<QuestionBankQuestion> selectPage(QuestionBankQuestionPageReqDTO questionbankquestionPageReqDTO) {
        return selectPage(questionbankquestionPageReqDTO, new LambdaQueryWrapper<QuestionBankQuestion>()
                .orderByDesc(QuestionBankQuestion::getCreateTime)

        );
    }

    @Select("select questionId from question_bank_question where questionBankId = #{questionBankId}")
    List<Long> selectListByBankId(@Param("questionBankId") Long questionBankId);
}




