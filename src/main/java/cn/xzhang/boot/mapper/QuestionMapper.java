package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.question.QuestionPageReqDTO;
import cn.xzhang.boot.model.dto.question.UserQuestionPageReqDTO;
import cn.xzhang.boot.model.entity.Question;
import cn.xzhang.boot.model.enums.QuestionBankReviewStatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Collection;
import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【question(题目表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface QuestionMapper extends BaseMapperPlus<Question> {

    default PageResult<Question> selectPage(QuestionPageReqDTO questionPageReqDTO) {
        return selectPage(questionPageReqDTO, new LambdaQueryWrapper<Question>()
                .eq(Objects.nonNull(questionPageReqDTO.getId()), Question::getId, questionPageReqDTO.getId())
                .like(Objects.nonNull(questionPageReqDTO.getTitle()), Question::getTitle, questionPageReqDTO.getTitle())
                .eq(Objects.nonNull(questionPageReqDTO.getReviewStatus()), Question::getReviewStatus, questionPageReqDTO.getReviewStatus())
                .orderByDesc(Question::getCreateTime)

        );
    }

    default PageResult<Question> selectUserPage(UserQuestionPageReqDTO reqDTO) {
        return selectPage(reqDTO, new LambdaQueryWrapper<Question>()
                .like(Objects.nonNull(reqDTO.getTitle()), Question::getTitle, reqDTO.getTitle())
                .like(Objects.nonNull(reqDTO.getTag()), Question::getTags, reqDTO.getTag())
                .eq(Objects.nonNull(reqDTO.getNeedVip()), Question::getNeedVip, reqDTO.getNeedVip())
                .eq(Question::getReviewStatus, QuestionBankReviewStatusEnum.PASS.getValue())
                .orderByDesc(Question::getCreateTime)

        );
    }

    default PageResult<Question> selectUserPageByBankId(UserQuestionPageReqDTO reqDTO, Collection<Long> ids) {
        return selectPage(reqDTO, new LambdaQueryWrapper<Question>()
                .like(Objects.nonNull(reqDTO.getTitle()), Question::getTitle, reqDTO.getTitle())
                .like(Objects.nonNull(reqDTO.getTag()), Question::getTags, reqDTO.getTag())
                .eq(Objects.nonNull(reqDTO.getNeedVip()), Question::getNeedVip, reqDTO.getNeedVip())
                .eq(Question::getReviewStatus, QuestionBankReviewStatusEnum.PASS.getValue())
                .in(Question::getId, ids)
                .orderByDesc(Question::getCreateTime)

        );
    }

}




