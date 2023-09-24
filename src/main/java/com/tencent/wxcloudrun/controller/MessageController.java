package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.dto.MessageRequest;
import com.tencent.wxcloudrun.service.MessageService;
import com.tencent.wxcloudrun.utils.WeixinUtils;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
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
    Map<String,Object> autoReply(@RequestBody MessageRequest request) throws Exception {
        logger.info("/api/autoReply post request, request: {}",request.toString());

        String path = this.getClass().getClassLoader().getResource("").getPath();

        logger.info("path："+path);


        Map<String,Object> uploadResult = WeixinUtils.uploadFile(path+"/static/income_from_operation.png","image");
        logger.info("uploadResult"+uploadResult.get("responseCode"));
        logger.info("uploadResult"+uploadResult.get("responseBody"));

        Map<String, Object> replyMessage = new HashMap();
        replyMessage.put("ToUserName",request.getFromUserName());
        replyMessage.put("FromUserName",request.getToUserName());
        replyMessage.put("CreateTime",System.currentTimeMillis()/1000);
        replyMessage.put("MsgType","text");
        replyMessage.put("Content","测试自动回复");
        logger.info("返回的json:",replyMessage.toString());
        return replyMessage;
    }

}
