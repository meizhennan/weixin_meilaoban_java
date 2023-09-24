package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.MessageRequest;
import com.tencent.wxcloudrun.service.MessageService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class MessageController {
    final MessageService messageService;
    final Logger logger;

    public MessageController(@Autowired MessageService messageService){
        this.messageService = messageService;
        this.logger = LoggerFactory.getLogger(MessageController.class);
    }

    @PostMapping(value = "/api/autoReply")
    ApiResponse autoReply(@RequestBody MessageRequest request){
        logger.info("/api/autoReply post request, request: {}",request.toString());
        Map<String, Object> replyMessage = new HashMap();
        replyMessage.put("ToUserName",request.getToUserName());
        replyMessage.put("FromUserName",request.getFromUserName());
        replyMessage.put("CreateTime",System.currentTimeMillis()/1000);
        replyMessage.put("MsgType","text");
        replyMessage.put("Content","测试自动回复");

        return ApiResponse.ok(replyMessage);
    }

}
