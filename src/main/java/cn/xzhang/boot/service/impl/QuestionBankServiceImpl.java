package cn.xzhang.boot.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.xzhang.boot.common.exception.ServiceException;
import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.mapper.QuestionBankMapper;
import cn.xzhang.boot.model.dto.questionBank.QuestionBankAddReqDTO;
import cn.xzhang.boot.model.dto.questionBank.QuestionBankPageReqDTO;
import cn.xzhang.boot.model.dto.questionBank.QuestionBankUpdateReqDTO;
import cn.xzhang.boot.model.entity.QuestionBank;
import cn.xzhang.boot.model.vo.questionBank.QuestionBankSimpleVo;
import cn.xzhang.boot.model.vo.questionBank.QuestionBankVo;
import cn.xzhang.boot.service.QuestionBankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

import static cn.xzhang.boot.common.exception.enums.GlobalErrorCodeConstants.*;
import static cn.xzhang.boot.common.exception.util.ServiceExceptionUtil.exception;

/**
 * 针对表【questionBank(题库表表)】的数据库操作Service实现
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank> implements QuestionBankService {

    @Resource
    private QuestionBankMapper questionbankMapper;

    /**
     * 添加新题库表
     *
     * @param questionBankReqDTO 题库表信息请求DTO
     * @return 添加成功返回题库表id
     */
    @Override
    public long addQuestionBank(QuestionBankAddReqDTO questionBankReqDTO) {
        QuestionBank questionBank = new QuestionBank();
        BeanUtil.copyProperties(questionBankReqDTO, questionBank);
        if (!this.save(questionBank)) {
            throw exception(ADD_FAIL);
        }
        return questionBank.getId();
    }

    /**
     * 更新题库表信息
     *
     * @param questionBankReqDTO 题库表信息更新请求DTO
     * @return 更新成功返回true
     */
    @Override
    public boolean updateQuestionBank(QuestionBankUpdateReqDTO questionBankReqDTO) {
        if (questionBankReqDTO.getId() == null) {
            throw exception(BAD_REQUEST);
        }
        QuestionBank questionBank = new QuestionBank();
        BeanUtil.copyProperties(questionBankReqDTO, questionBank);
        boolean b = this.updateById(questionBank);
        if (!b) {
            throw exception(UPDATE_FAIL);
        }
        return true;
    }

    /**
     * 删除题库表
     *
     * @param id 题库表id
     * @return 删除成功返回true
     */
    @Override
    @Transactional(rollbackFor = ServiceException.class)
    public boolean deleteQuestionBank(Long id) {
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
     * 将题库表对象转换为题库表VO对象
     *
     * @param questionBank 题库表对象
     * @return 返回题库表VO对象
     */
    @Override
    public QuestionBankSimpleVo getSimpleQuestionBankVO(QuestionBank questionBank) {
        if (questionBank == null) {
            return null;
        }
        QuestionBankSimpleVo questionbankSimpleVo = new QuestionBankSimpleVo();
        BeanUtil.copyProperties(questionBank, questionbankSimpleVo);
        return questionbankSimpleVo;
    }

    /**
     * 获取题库表分页信息
     *
     * @param questionBankPageReqDTO 题库表分页请求DTO
     * @return 返回题库表分页结果
     */
    @Override
    public PageResult<QuestionBankVo> getQuestionBankPage(QuestionBankPageReqDTO questionBankPageReqDTO) {
        PageResult<QuestionBank> pageResult = questionbankMapper.selectPage(questionBankPageReqDTO);
        if (pageResult.getList() == null) {
            return PageResult.empty();
        }
        List<QuestionBankVo> questionBankVos = pageResult.getList().stream().map(questionBank -> {
            QuestionBankVo questionbankVo = new QuestionBankVo();
            BeanUtil.copyProperties(questionBank, questionbankVo);
            return questionbankVo;
        }).collect(Collectors.toList());
        return new PageResult<>(questionBankVos, pageResult.getTotal());
    }

    @Override
    public QuestionBankVo getQuestionBankVO(QuestionBank questionBank) {
        if (questionBank == null) {
            return null;
        }
        QuestionBankVo questionbankVo = new QuestionBankVo();
        BeanUtil.copyProperties(questionBank, questionbankVo);
        return questionbankVo;
    }

}



