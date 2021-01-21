package com.bins.userprovider.controller;

import com.bins.userprovider.entity.UserEntity;
import com.bins.userprovider.model.UserModel;
import com.bins.userprovider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public UserModel findById(@PathVariable Long id) {
        UserEntity userEntity = this.userRepository.findOne(id);
        UserModel userModel = null;
        if (userEntity != null) {
            userModel = new UserModel();
            userModel.setId(userEntity.getId());
            userModel.setUsername(userEntity.getUsername());
        }
        return userModel;
    }
}
