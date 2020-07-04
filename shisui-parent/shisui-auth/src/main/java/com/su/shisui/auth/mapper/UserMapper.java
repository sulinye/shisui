package com.su.shisui.auth.mapper;

import com.su.shisui.auth.common.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * author sly
 */
@Mapper
public interface UserMapper {


    User findUser(User user);
}
