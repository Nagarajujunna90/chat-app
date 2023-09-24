package com.nr.chatapp.service;

import com.nr.chatapp.dto.MessageVo;
import com.nr.chatapp.exception.UserNotFoundException;
import com.nr.chatapp.model.MessageTypes;
import com.nr.chatapp.model.Messages;
import com.nr.chatapp.model.User;
import com.nr.chatapp.repository.MessageRepository;
import com.nr.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Messages sendMessage(MessageVo messageVo, Principal principal) {
        Messages messages = new Messages();
        messages.setMessage(messageVo.getMessage());
        messages.setMessageType(MessageTypes.TEXT);
        messages.setSeen(false);
        User user = userRepository.findById(Integer.valueOf(messageVo.getReceivedBy())).orElse(null);
        if (user != null) {
            messages.setUser(user);
            User senderDetails = userRepository.findByUserName(principal.getName());
            messages.setSender(String.valueOf(senderDetails.getId()));
            return messageRepository.save(messages);
        } else {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<Messages> getAllMessageByUserId(Principal principal) {
        User user = userRepository.findByUserName(principal.getName());
        return user.getMessages();
    }

    @Override
    public String deleteMessageById(String messageId) {
        return null;
    }
}
