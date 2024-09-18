package cn.xzhang.boot.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 审核状态枚举
 *
 * @author <a href="https://github.com/XiaoZhangCode">XiaoZhangCode</a>
 */
@AllArgsConstructor
@Getter
public enum QuestionBankReviewStatusEnum {

    WAIT("待审核", 0),
    PASS("通过", 1),
    REJECT("拒绝", 2);

    private final String text;

    private final Integer value;

    /**
     * 根据数值获取对应的用户状态枚举。
     *
     * @param value 用户状态的数值表示。
     * @return 对应的用户状态枚举，如果找不到则返回null。
     */
    public static QuestionBankReviewStatusEnum getEnumByValue(Integer value) {
        if (value == null) {
            return null;
        }
        // 遍历所有枚举值，查找数值匹配的枚举
        for (QuestionBankReviewStatusEnum statusEnum : values()) {
            if (statusEnum.getValue().equals(value)) {
                return statusEnum;
            }
        }
        return null;
    }

    /**
     * 根据文本获取对应的用户状态枚举。
     *
     * @param text 用户状态的文本表示。
     * @return 对应的用户状态枚举，如果找不到则返回null。
     */
    public static QuestionBankReviewStatusEnum getEnumByText(String text) {
        if (text == null) {
            return null;
        }
        // 遍历所有枚举值，查找文本匹配的枚举
        for (QuestionBankReviewStatusEnum statusEnum : values()) {
            if (statusEnum.getText().equals(text)) {
                return statusEnum;
            }
        }
        return null;
    }


}
