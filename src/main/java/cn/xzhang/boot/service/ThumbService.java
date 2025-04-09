package cn.xzhang.boot.service;

import cn.xzhang.boot.common.pojo.PageResult;
import cn.xzhang.boot.model.dto.thumb.ThumbAddReqDTO;
import cn.xzhang.boot.model.dto.thumb.ThumbPageReqDTO;
import cn.xzhang.boot.model.dto.thumb.ThumbUpdateReqDTO;
import cn.xzhang.boot.model.entity.Thumb;
import cn.xzhang.boot.model.vo.thumb.ThumbSimpleVo;
import cn.xzhang.boot.model.vo.thumb.ThumbVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;

/**
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 * @description 针对表【thumb(点赞表表)】的数据库操作Service
 */
public interface ThumbService extends IService<Thumb> {

    /**
     * 添加点赞表信息。
     *
     * @param thumbReqDTO 点赞表添加请求数据传输对象，包含要添加的点赞表的所有必要信息。
     * @return 返回添加操作的自增ID，用于标识此次添加操作。
     */
    long addThumb(ThumbAddReqDTO thumbReqDTO);



    /**
     * 根据Thumb对象获取对应的ThumbVo对象。
     *
     * @param thumb 一个包含点赞表信息的Thumb对象。
     * @return 返回一个包含点赞表信息的ThumbVo对象。
     */
    ThumbSimpleVo getSimpleThumbVO(Thumb thumb);

    /**
     * 获取点赞表页面信息
     *
     * @param thumbPageReqDTO 包含分页和筛选条件的点赞表请求数据传输对象
     * @return 返回点赞表页面的结果，包括点赞表列表和分页信息
     */
    PageResult<ThumbVo> getThumbPage(ThumbPageReqDTO thumbPageReqDTO);

    /**
     * 根据Thumb对象获取对应的ThumbVo对象。
     *
     * @param thumb 一个包含点赞表信息的Thumb对象。
     * @return 返回一个包含点赞表信息的ThumbVo对象。
     */
    ThumbVo getThumbVO(Thumb thumb);

    /**
     * 根据用户ID和问题ID获取点赞表对象。
     * @param userId 用户ID
     * @param questionId 题目Id
     * @return 点赞表对象
     */
    Thumb getThumbVOByUserIdAndQuestionId(Long userId, Long questionId);

    /**
     * 判断是否已经点赞
     * @param userId 用户id
     * @param targetId 目标ID
     * @param targetType 类型
     * @return 是否点赞
     */
    boolean isLiked(Long userId,Long targetId,Byte targetType);


    /**
     * 用户取消点赞
     * @param thumbReqDTO 取消点赞对象
     * @return 返回
     */
    boolean unLike(ThumbAddReqDTO thumbReqDTO);

    List<Thumb> getThumbList(Set<Long> ids, Integer type);
}
