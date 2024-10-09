package cn.xzhang.boot.esdao;


import cn.xzhang.boot.model.dto.question.QuestionEsDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


/**
 * 题目 ES 操作
 *
 * @author codeZhang
 * @Date 2024/10/9 11:34
 */
public interface QuestionEsDao
        extends ElasticsearchRepository<QuestionEsDTO, Long> {

    /**
     * 根据用户 id 查询
     * @param userId 用户id
     * @return 用户创建的 题目列表
     */
    List<QuestionEsDTO> findByUserId(Long userId);


}
