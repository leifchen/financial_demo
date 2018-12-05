package com.chen.seller.enums;

import lombok.Getter;

/**
 * 渠道配置信息
 *
 * @Author LeifChen
 * @Date 2018-12-04
 */
@Getter
public enum ChannelEnum {
    /**
     * 渠道配置ABC
     */
    ABC("C1", "ABC", "/opt/ABC");

    private String channelId;
    private String channelName;
    private String ftpPath, ftpUser, ftpPwd;
    private String rootDir;

    ChannelEnum(String channelId, String channelName, String rootDir) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.rootDir = rootDir;
    }

    /**
     * 根据渠道id获取渠道配置
     *
     * @param channelId
     * @return
     */
    public static ChannelEnum getByChannelId(String channelId) {
        for (ChannelEnum channelEnum : ChannelEnum.values()) {
            if (channelEnum.getChannelId().equals(channelId)) {
                return channelEnum;
            }
        }
        return null;
    }
}
