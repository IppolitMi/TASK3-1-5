package ru.kata.spring.boot_security.demo.service;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import javax.management.relation.RoleNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;

    }

    @Override
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Role findRoleByRole(String role) {
        return roleRepository.findRoleByRoleName(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role findRoleById(int id) throws RoleNotFoundException {
        Optional<Role> optional = roleRepository.findById(id);

        if (optional.isPresent()) {
            Role role = optional.get();
            Hibernate.initialize(role.getUsersSet());
            return role;
        } else {
            throw new RoleNotFoundException("No user with such id");
        }
    }
}