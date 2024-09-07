package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.question.QuestionPageReqDTO;
import cn.xzhang.boot.model.entity.Question;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【question(题目表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface QuestionMapper extends BaseMapperPlus<Question> {

    default PageResult<Question> selectPage(QuestionPageReqDTO questionPageReqDTO) {
        return selectPage(questionPageReqDTO, new LambdaQueryWrapper<Question>()
                .eq(Objects.nonNull(questionPageReqDTO.getTitle()), Question::getTitle, questionPageReqDTO.getTitle())
                .eq(Objects.nonNull(questionPageReqDTO.getContent()), Question::getContent, questionPageReqDTO.getContent())
                .eq(Objects.nonNull(questionPageReqDTO.getTags()), Question::getTags, questionPageReqDTO.getTags())
                .eq(Objects.nonNull(questionPageReqDTO.getAnswer()), Question::getAnswer, questionPageReqDTO.getAnswer())
                .eq(Objects.nonNull(questionPageReqDTO.getSource()), Question::getSource, questionPageReqDTO.getSource())
                .orderByDesc(Question::getCreateTime)

        );
    }

}




