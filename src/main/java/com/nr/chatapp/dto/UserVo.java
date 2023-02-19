package com.nr.chatapp.dto;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UserVo {
    @NotEmpty(message = "name not cant be empty")
    private String name;
    private String language;
    @Min(value = 18, message = "must be equal or greater than 18")
    @Max(value = 100, message = "must be equal or less than 100")
    private Integer age;
    @NotBlank(message = "Country is mandatory")
    private String country;
    @NotNull
    @NotBlank(message = "Gender is mandatory")
    private String gender;
    private String password;
    private String emailId;
    @NotEmpty(message = "User name cant be empty or null")
    private String userName;


}
