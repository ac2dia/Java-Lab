package org.springframework.validation.model;

import lombok.*;
import org.springframework.validation.custom_validation.Phone;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {

    @NotNull
    private String name;

    @Phone
    private String phone;

    @Email
    private String email;

    @Builder
    public UserDto(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
