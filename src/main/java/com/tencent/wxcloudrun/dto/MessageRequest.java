package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class MessageRequest {
    private String ToUserName;
    private String FromUserName;
    private long CreateTime;
    private String MsgType;
    private String Content;
    private long MsgId;

}
