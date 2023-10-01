package com.daedal00.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daedal00.app.api.dto.UserDTO;
import com.daedal00.app.model.PlaidData;
import com.daedal00.app.model.User;
import com.daedal00.app.repository.PlaidDataRepository;
import com.daedal00.app.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlaidDataRepository plaidDataRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
            .stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    public UserDTO getUserById(String id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return null;
        }
        return convertToDTO(user);
    }

    public UserDTO saveUser(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    private UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public User linkUserWithPlaidData(String userId, PlaidData plaidData) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        plaidData.setUserId(user.getId());
        plaidDataRepository.save(plaidData);
        return user;
    }
    

    public UserDTO getUserWithPlaidData(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        PlaidData plaidData = plaidDataRepository.findByUserId(userId);
        return convertToUserDTO(user, plaidData);
    }
    
    private UserDTO convertToUserDTO(User user, PlaidData plaidData) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        if (plaidData != null) {
            userDTO.setAccessToken(plaidData.getAccessToken());
            userDTO.setItemId(plaidData.getItemId());
        }
        return userDTO;
    }
    
    
    
    

}
