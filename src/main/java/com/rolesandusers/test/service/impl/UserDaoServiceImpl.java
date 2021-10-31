package com.rolesandusers.test.service.impl;

import com.rolesandusers.test.exception.UserAlreadyExistException;
import com.rolesandusers.test.exception.UserNotFoundException;
import com.rolesandusers.test.model.entity.User;
import com.rolesandusers.test.model.repository.UserRepository;
import com.rolesandusers.test.service.UserDaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserDaoServiceImpl implements UserDaoService {

    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        this.checkByNameAndThrowIfExist(user.getUserName());
        this.checkByEmailAndThrowIfExist(user.getEmail());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userName) {
        User user = getUserByNameOrThrowIfNotExist(userName);
        user.setDeleted(true);
        userRepository.save(user);
    }

    @Override
    public User updateUser(String username, User user) {
        User userFromDb = this.getUserByNameOrThrowIfNotExist(username);
        if (! userFromDb.getEmail().equals(user.getEmail())) {
            this.checkByEmailAndThrowIfExist(user.getEmail());
        }
        userFromDb.setEmail(user.getEmail());
        userFromDb.setDateOfBirth(user.getDateOfBirth());
        return userRepository.save(userFromDb);
    }

    @Override
    public User getUserByName(String userName) {
        User user = this.getUserByNameOrThrowIfNotExist(userName);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findUserByDeletedIsFalse();
    }

    @Override
    public List<User> getUsersBornInPeriod(LocalDate after, LocalDate before) {
        return userRepository.findUserByDateOfBirthAfterAndDateOfBirthBeforeAndDeletedIsFalse(after, before);
    }

    private User getUserByNameOrThrowIfNotExist(String username) {
        return userRepository.findByUserNameAndDeletedIsFalse(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь '" + username + "' не найден"));
    }

    private void checkByNameAndThrowIfExist(String name){
        Optional<User> userByName = userRepository.findByUserName(name);
        if (userByName.isPresent()) {
            throw new UserAlreadyExistException("Пользователь '" + name + "' уже существует");
        }
    }

    private void checkByEmailAndThrowIfExist(String email){
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isPresent()) {
            throw new UserAlreadyExistException("Необходимо ввести уникальный email");
        }
    }

}
