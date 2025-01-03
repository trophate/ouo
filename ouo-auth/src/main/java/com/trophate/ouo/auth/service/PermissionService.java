package com.trophate.ouo.auth.service;

import com.trophate.ouo.auth.dto.PermissionCreateDTO;
import com.trophate.ouo.auth.entity.Permission;
import com.trophate.ouo.auth.repository.PermissionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    /**
     * 创建。
     *
     * @param dto 参数
     */
    public void create(PermissionCreateDTO dto) {
        var permission = new Permission();
        permission.setName(dto.getName());
        permission.setCode(dto.getCode());
        permission.setType(dto.getType());
        permission.setParentId(dto.getParentId() != null ? dto.getParentId() : 0);
        permissionRepository.save(permission);
    }

    /**
     * 通过id集合获取编码表。
     *
     * @param ids id集合
     * @return List<String>
     */
    public List<String> getCodesByIds(List<Integer> ids) {
        List<Permission> permissions = permissionRepository.findByIdIn(ids);
        var codes = new ArrayList<String>();
        for (Permission permission : permissions) {
            codes.add(permission.getCode());
        }
        return codes;
    }

    /**
     * 获取所有编码。
     *
     * @return List<String>
     */
    public List<String> getAllCodes() {
        List<Permission> permissions = permissionRepository.findAll();
        var codes = new ArrayList<String>();
        for (Permission permission : permissions) {
            codes.add(permission.getCode());
        }
        return codes;
    }

    /**
     * 编辑。
     *
     * @param id id
     * @param dto 参数
     */
    public void edit(int id, PermissionCreateDTO dto) {
        Permission permission = permissionRepository.getById(id);
        if (dto.getName() != null) {
            permission.setName(dto.getName());
        }
        if (dto.getCode() != null) {
            permission.setCode(dto.getCode());
        }
        if (dto.getType() != null) {
            permission.setType(dto.getType());
        }
        permissionRepository.save(permission);
    }

    /**
     * 删除。
     *
     * @param id id
     */
    public void del(int id) {
        permissionRepository.deleteById(id);
    }

    /**
     * 保存。
     *
     * @param permission 参数
     */
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }

    /**
     * 批量保存。
     *
     * @param permissions 参数集合
     */
    public void saveInBatch(List<Permission> permissions) {
        permissionRepository.saveAll(permissions);
    }
}
