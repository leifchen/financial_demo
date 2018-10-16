package com.chen.manager.error;

import lombok.Getter;

/**
 * ErrorEnum
 *
 * @Author LeifChen
 * @Date 2018-10-15
 */
@Getter
public enum ErrorEnum {
    /**
     * 编号不可为空
     */
    ID_NOT_NULL("E001", "编号不可为空", false),
    /**
     * 收益率范围错误
     */
    ILLEGAL_REWARD_RATE_RANGE("E002", "收益率范围错误", false),
    /**
     * 投资步长需为整数
     */
    ILLEGAL_STEP_AMOUNT("E003", "投资步长需为整数", false),
    /**
     * 未知异常
     */
    UNKNOWN("E999", "未知异常", false);

    private String code;
    private String message;
    private boolean canRetry;

    ErrorEnum(String code, String message, boolean canRetry) {
        this.code = code;
        this.message = message;
        this.canRetry = canRetry;
    }

    public static ErrorEnum getByCode(String code) {
        for (ErrorEnum errorEnum : ErrorEnum.values()) {
            if (errorEnum.code.equals(code)) {
                return errorEnum;
            }
        }
        return UNKNOWN;
    }
}
