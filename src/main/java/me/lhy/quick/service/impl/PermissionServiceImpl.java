package me.lhy.quick.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.lhy.quick.entity.Permission;
import me.lhy.quick.mapper.PermissionMapper;
import me.lhy.quick.service.PermissionService;
import org.springframework.stereotype.Service;

/**
* @author lhy
* @description 针对表【permissions(权限点表)】的数据库操作Service实现
* @createDate 2026-03-21 19:01:58
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

    @Override
    public boolean save(Permission entity) {
        validateUnique(entity);
        return super.save(entity);
    }

    @Override
    public boolean updateById(Permission entity) {
        validateUnique(entity);
        return super.updateById(entity);
    }

    private void validateUnique(Permission entity) {
        Long id = entity.getId();
        boolean permissionExists = this.exists(Wrappers.<Permission>lambdaQuery()
            .eq(Permission::getResource, entity.getResource())
            .eq(Permission::getAction, entity.getAction())
            .isNull(Permission::getDeletedAt)
            .ne(id != null, Permission::getId, id));
        if (permissionExists) {
            throw new IllegalArgumentException("资源与动作组合已存在");
        }
    }
}




