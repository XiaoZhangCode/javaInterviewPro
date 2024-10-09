package cn.xzhang.boot.esdao;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * @author codeZhang
 * @Date 2024/10/9 11:37
 */
@SpringBootTest
public class QuestionEsDaoTest {

    @Resource
    private QuestionEsDao questionEsDao;

    @Test
    public void findByUserId() {
        questionEsDao.findByUserId(1L);
    }
}