package cn.xzhang.boot.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author code_zhang
 * @Date 2025/4/9 21:51
 */
@AllArgsConstructor
@Getter
public enum ThumbTargetTypeEnum {

    /**
     * 点赞类型枚举 0 题目 1 评论
     */
    QUESTION("题目", 0),
    COMMENT("评论", 1);

    private final String text;

    private final Integer value;

    /**
     * 根据数值获取对应的用户状态枚举。
     *
     * @param value 用户状态的数值表示。
     * @return 对应的用户状态枚举，如果找不到则返回null。
     */
    public static ThumbTargetTypeEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        // 遍历所有枚举值，查找数值匹配的枚举
        for (ThumbTargetTypeEnum statusEnum : values()) {
            if (statusEnum.getValue().equals(value)) {
                return statusEnum;
            }
        }
        return null;
    }

}
