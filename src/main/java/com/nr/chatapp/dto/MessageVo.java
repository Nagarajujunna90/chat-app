package com.nr.chatapp.dto;

import com.nr.chatapp.model.MessageTypes;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class MessageVo {
    @NotEmpty
    private String message;
    private MessageTypes messageType;
    private boolean isSeen=false;
    @NotEmpty
    private String receivedBy;


}
