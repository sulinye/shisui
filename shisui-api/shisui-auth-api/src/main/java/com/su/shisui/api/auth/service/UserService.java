package com.su.shisui.api.auth.service;

import com.su.shisui.auth.common.po.ImageCode;
import com.su.shisui.auth.common.po.User;

import java.io.IOException;

/**
 * author sly
 */
public interface UserService {

    User login(User user);

    ImageCode generateCode() throws IOException;

    User findUserById(Long id);
}
