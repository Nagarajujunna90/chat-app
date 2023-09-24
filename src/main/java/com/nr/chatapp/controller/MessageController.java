package com.nr.chatapp.controller;

import com.nr.chatapp.dto.MessageVo;
import com.nr.chatapp.model.Messages;
import com.nr.chatapp.service.MessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/message")
    public ResponseEntity<?> sendMessage(@RequestBody MessageVo messageVo, Principal principal){
        Messages sendMessage = messageService.sendMessage(messageVo,principal);
        return new ResponseEntity<>("Message sent",HttpStatus.CREATED);
    }
    @GetMapping("/message")
    public ResponseEntity<?> sendMessage( Principal principal){
        List<Messages> messagesList = messageService.getAllMessageByUserId(principal);
        return new ResponseEntity<>(messagesList,HttpStatus.OK);
    }
}
