package com.msjava.user.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.msjava.user.models.UserModel;
import com.msjava.user.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        return this.userRepository.save(userModel);
    }

    public Page<UserModel> findAll(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    public Optional<UserModel> findById(UUID id) {
        return this.userRepository.findById(id);
    }

    @Transactional
    public void delete(UserModel userModel) {
        this.userRepository.delete(userModel);
    }
}
