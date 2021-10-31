package com.rolesandusers.test.service.impl;

import com.rolesandusers.test.exception.RoleAlreadyExistException;
import com.rolesandusers.test.exception.RoleNotFoundException;
import com.rolesandusers.test.exception.TargetRoleHasUsersException;
import com.rolesandusers.test.model.entity.Role;
import com.rolesandusers.test.model.repository.RoleRepository;
import com.rolesandusers.test.service.RoleDaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleDaoServiceImpl implements RoleDaoService {

    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        this.checkByNameAndThrowIfExist(role.getName());
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void deleteByName(String roleName) {
        Role role = getRoleByNameAndThrowIfNotExist(roleName);
        if (role.getUsers() == null || !role.getUsers().isEmpty()) {
            throw new TargetRoleHasUsersException("Невозможно удалить роль '" + roleName + "'. Имееются пользователи, которые ее используют");
        }
        roleRepository.deleteByName(roleName);
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    private void checkByNameAndThrowIfExist(String name){
        Optional<Role> roleByName = roleRepository.findByName(name);
        if (roleByName.isPresent()) {
            throw new RoleAlreadyExistException("Роль '" + name + "' уже существует");
        }
    }

    private void checkByNameAndThrowIfNotExist(String name){
        roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Роль с именем '" + name + "' не найдена"));
    }

    private Role getRoleByNameAndThrowIfNotExist(String name){
        return roleRepository.findByName(name)
                .orElseThrow(() -> new RoleNotFoundException("Роль с именем '" + name + "' не найдена"));
    }
}
