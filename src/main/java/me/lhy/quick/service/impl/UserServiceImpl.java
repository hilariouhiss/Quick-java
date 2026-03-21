package me.lhy.quick.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.lhy.quick.entity.User;
import me.lhy.quick.mapper.UserMapper;
import me.lhy.quick.service.UserService;
import org.springframework.stereotype.Service;

/**
* @author lhy
* @description 针对表【users(系统用户表)】的数据库操作Service实现
* @createDate 2026-03-21 19:01:58
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Override
    public boolean save(User entity) {
        validateUnique(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(User entity) {
        validateUnique(entity);
        return super.updateById(entity);
    }

    private void validateUnique(User entity) {
        Long id = entity.getId();
        boolean usernameExists = this.exists(Wrappers.<User>lambdaQuery()
            .eq(User::getUsername, entity.getUsername())
            .isNull(User::getDeletedAt)
            .ne(id != null, User::getId, id));
        if (usernameExists) {
            throw new IllegalArgumentException("用户名已存在");
        }
        boolean emailExists = this.exists(Wrappers.<User>lambdaQuery()
            .eq(User::getEmail, entity.getEmail())
            .isNull(User::getDeletedAt)
            .ne(id != null, User::getId, id));
        if (emailExists) {
            throw new IllegalArgumentException("邮箱已存在");
        }
        boolean phoneExists = this.exists(Wrappers.<User>lambdaQuery()
            .eq(User::getPhone, entity.getPhone())
            .isNull(User::getDeletedAt)
            .ne(id != null, User::getId, id));
        if (phoneExists) {
            throw new IllegalArgumentException("手机号已存在");
        }
    }
}




