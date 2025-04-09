package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.thumb.ThumbPageReqDTO;
import cn.xzhang.boot.model.entity.Thumb;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * @author XiaoZhangCode
 * @description 针对表【thumb(点赞表表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface ThumbMapper extends BaseMapperPlus<Thumb> {

    default PageResult<Thumb> selectPage(ThumbPageReqDTO thumbPageReqDTO) {
        return selectPage(thumbPageReqDTO, new LambdaQueryWrapper<Thumb>()
                .orderByDesc(Thumb::getCreateTime)

        );
    }

}




