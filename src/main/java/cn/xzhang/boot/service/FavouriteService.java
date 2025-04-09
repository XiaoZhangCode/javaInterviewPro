package cn.xzhang.boot.service;

import cn.xzhang.boot.model.dto.favourite.FavouriteAddReqDTO;
import cn.xzhang.boot.model.entity.Favourite;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【favourite(收藏表表)】的数据库操作Service
 */
public interface FavouriteService extends IService<Favourite> {

    /**
     * 添加收藏表信息。
     *
     * @param favouriteReqDTO 收藏表添加请求数据传输对象，包含要添加的收藏表的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long addFavourite(FavouriteAddReqDTO favouriteReqDTO);

    /**
     * 根据用户ID和问题ID获取收藏表对象。
     *
     * @param userId     用户ID
     * @param questionId 题目Id
     * @return 返回收藏表对象，如果不存在则返回null。
     */
    Favourite getFavouriteVOByUserIdAndQuestionId(Long userId, Long questionId);

    boolean unFavourite(FavouriteAddReqDTO favouriteReqDTO);
}
