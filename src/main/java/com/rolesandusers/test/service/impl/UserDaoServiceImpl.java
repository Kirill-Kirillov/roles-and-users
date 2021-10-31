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
        this.checkByNameAndThrowIfNotExist(userName);
        userRepository.deleteById(userName);
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
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new UserNotFoundException("Пользователь '" + userName + "' не найден"));
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUsersBornInPeriod(LocalDate after, LocalDate before) {
        return userRepository.findUserByDateOfBirthAfterAndDateOfBirthBefore(after, before);
    }

    private User getUserByNameOrThrowIfNotExist(String username) {
        return userRepository.findByUserName(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь '" + username + "' не найден"));
    }

    private void checkByNameAndThrowIfExist(String name){
        Optional<User> userByName = userRepository.findByUserName(name);
        if (userByName.isPresent()) {
            throw new UserAlreadyExistException("Пользователь '" + name + "' уже существует");
        }
    }

    private void checkByNameAndThrowIfNotExist(String name){
        userRepository.findByUserName(name)
                .orElseThrow(() -> new UserNotFoundException("Роль с именем '" + name + "' не найдена"));
    }

    private void checkByEmailAndThrowIfExist(String email){
        Optional<User> userByEmail = userRepository.findByEmail(email);
        if (userByEmail.isPresent()) {
            throw new UserAlreadyExistException("Необходимо ввести уникальный email");
        }
    }

}
