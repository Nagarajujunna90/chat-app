package com.nr.chatapp.service;

import com.nr.chatapp.dto.MessageVo;
import com.nr.chatapp.model.Messages;

import java.security.Principal;
import java.util.List;

public interface MessageService {
    Messages sendMessage(MessageVo messageVo, Principal principal);
    List<Messages> getAllMessageByUserId(Principal principal);
    String deleteMessageById(String messageId);

}
