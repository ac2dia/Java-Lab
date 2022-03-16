package io.springfox.springfoxswagger2.service;

import io.springfox.springfoxswagger2.domain.User;
import io.springfox.springfoxswagger2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(InvalidParameterException::new);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
