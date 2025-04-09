package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.question.QuestionPageReqDTO;
import cn.xzhang.boot.model.dto.question.UserQuestionPageReqDTO;
import cn.xzhang.boot.model.entity.Question;
import cn.xzhang.boot.model.enums.QuestionBankReviewStatusEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.Date;
import java.util.List;
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
                .in(Objects.nonNull(questionPageReqDTO.getQuestionIdList()), Question::getId, questionPageReqDTO.getQuestionIdList())
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

    @Select("select * from question")
    List<Question> selectAllList();

    /**
     * 查询题目列表（包括已被删除的数据）
     */
    @Select("select * from question where updateTime >= #{minUpdateTime}")
    List<Question> listQuestionWithDelete(Date minUpdateTime);

    @Select("update question set viewNum = viewNum + 1 where id = #{id}")
    void updateViewNum(Long id);



    @Select("update question set thumbNum = thumbNum + 1 where id = #{questionId}")
    void updateThumbCountAdd(Long questionId);

    @Select("update question set thumbNum = thumbNum - 1 where id = #{questionId}")
    void updateThumbCountReduce(Long questionId);

    @Select("update question set favourNum = favourNum + 1 where id = #{questionId}")
    void updateFavoriteCountAdd(Long questionId);

    @Select("update question set favourNum = favourNum - 1 where id = #{questionId}")
    void updateFavoriteCountReduce(Long questionId);
}




