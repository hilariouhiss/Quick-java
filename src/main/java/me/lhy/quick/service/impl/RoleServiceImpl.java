package me.lhy.quick.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.lhy.quick.entity.Role;
import me.lhy.quick.mapper.RoleMapper;
import me.lhy.quick.service.RoleService;
import org.springframework.stereotype.Service;

/**
* @author lhy
* @description 针对表【roles(角色表)】的数据库操作Service实现
* @createDate 2026-03-21 19:01:58
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

    @Override
    public boolean save(Role entity) {
        validateUnique(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Role entity) {
        validateUnique(entity);
        return super.updateById(entity);
    }

    private void validateUnique(Role entity) {
        Long id = entity.getId();
        boolean codeExists = this.exists(Wrappers.<Role>lambdaQuery()
            .eq(Role::getCode, entity.getCode())
            .isNull(Role::getDeletedAt)
            .ne(id != null, Role::getId, id));
        if (codeExists) {
            throw new IllegalArgumentException("角色编码已存在");
        }
    }
}




