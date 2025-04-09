package cn.xzhang.boot.mapper;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.core.mapper.BaseMapperPlus;
import cn.xzhang.boot.model.dto.favourite.FavouritePageReqDTO;
import cn.xzhang.boot.model.entity.Favourite;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

/**
 * @author XiaoZhangCode
 * @description 针对表【favourite(收藏表表)】的数据库操作Mapper
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
public interface FavouriteMapper extends BaseMapperPlus<Favourite> {

    default PageResult<Favourite> selectPage(FavouritePageReqDTO favouritePageReqDTO) {
        return selectPage(favouritePageReqDTO, new LambdaQueryWrapper<Favourite>()
                .orderByDesc(Favourite::getCreateTime)

        );
    }

}




