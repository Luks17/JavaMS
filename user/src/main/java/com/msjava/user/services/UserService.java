package com.msjava.user.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import com.msjava.user.models.UserModel;
import com.msjava.user.producers.UserProducer;
import com.msjava.user.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserProducer userProducer;

    public UserService(
            UserRepository userRepository,
            UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public UserModel save(UserModel userModel) {
        userModel = this.userRepository.saveAndFlush(userModel);
        this.userProducer.sendEmail(userModel);

        return userModel;
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
