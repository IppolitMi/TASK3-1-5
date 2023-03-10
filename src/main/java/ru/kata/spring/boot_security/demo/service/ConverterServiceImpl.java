package ru.kata.spring.boot_security.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.dto.RoleDTO;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

@Service
public class ConverterServiceImpl implements ConverterService {
    private ModelMapper modelMapper;

    public ConverterServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDTO convertToDto(Role role) {
        return modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public User convertToUser(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }
}