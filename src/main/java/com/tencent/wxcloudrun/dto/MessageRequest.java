package com.tencent.wxcloudrun.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MessageRequest {
    @JsonProperty("ToUserName")
    private String ToUserName;
    @JsonProperty("FromUserName")
    private String FromUserName;
    @JsonProperty("CreateTime")
    private long CreateTime;
    @JsonProperty("MsgType")
    private String MsgType;
    @JsonProperty("Content")
    private String Content;
    @JsonProperty("MsgId")
    private long MsgId;

}
