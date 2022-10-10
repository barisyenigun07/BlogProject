package com.barisyenigun.blogserver.request.user;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ChangePasswordRequest {
    private String newPassword;
    private String newPasswordRepeat;
}
