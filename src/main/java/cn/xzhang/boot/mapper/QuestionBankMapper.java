package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.questionBank.QuestionBankPageReqDTO;
import cn.xzhang.boot.model.entity.QuestionBank;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import java.util.Objects;

/**
 * @author XiaoZhangCode
 * @description 针对表【questionBank(题库表表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface QuestionBankMapper extends BaseMapperPlus<QuestionBank> {

    default PageResult<QuestionBank> selectPage(QuestionBankPageReqDTO questionbankPageReqDTO) {
        return selectPage(questionbankPageReqDTO, new LambdaQueryWrapper<QuestionBank>()
                .eq(Objects.nonNull(questionbankPageReqDTO.getId()), QuestionBank::getId, questionbankPageReqDTO.getId())
                .like(Objects.nonNull(questionbankPageReqDTO.getTitle()), QuestionBank::getTitle, questionbankPageReqDTO.getTitle())
                .eq(Objects.nonNull(questionbankPageReqDTO.getReviewStatus()), QuestionBank::getReviewStatus, questionbankPageReqDTO.getReviewStatus())
                .orderByDesc(QuestionBank::getCreateTime)
        );
    }

}




