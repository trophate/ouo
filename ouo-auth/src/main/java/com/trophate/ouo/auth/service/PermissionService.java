package com.trophate.ouo.auth.service;

import com.trophate.ouo.auth.dto.CreatePermissionDTO;
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
     * 该方法创建一个权限。
     *
     * @param dto 参数
     */
    public void create(CreatePermissionDTO dto) {
        var permission = new Permission();
        permission.setName(dto.getName());
        permission.setCode(dto.getCode());
        permission.setType(dto.getType());
        permission.setParentId(dto.getParentId() != null ? dto.getParentId() : 0);
        permissionRepository.save(permission);
    }

    /**
     * 该方法批量创建权限。
     *
     * @param dtoList 参数集合
     */
    public void createInBatch(List<CreatePermissionDTO> dtoList) {
        var permissions = new ArrayList<Permission>();
        for (var dto : dtoList) {
            var permission = new Permission();
            permission.setName(dto.getName());
            permission.setCode(dto.getCode());
            permission.setType(dto.getType());
            permission.setParentId(dto.getParentId() != null ? dto.getParentId() : 0);
            permissions.add(permission);
        }
        permissionRepository.saveAll(permissions);
    }

    /**
     * 该方法编辑指定权限。
     *
     * @param id id
     * @param dto 参数
     */
    public void edit(int id, CreatePermissionDTO dto) {
        Permission permission = permissionRepository.getReferenceById(id);
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
     * 该方法删除指定权限。
     *
     * @param id id
     */
    public void del(int id) {
        permissionRepository.deleteById(id);
    }

    /**
     * 该方法获取权限编码表。
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

    public Permission getByCode(String code) {
        return permissionRepository.findByCode(code);
    }

    /**
     * 该方法获取所有权限编码。
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
}
