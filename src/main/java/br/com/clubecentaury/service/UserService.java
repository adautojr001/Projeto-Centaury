package br.com.clubecentaury.service;

import br.com.clubecentaury.model.User;
import br.com.clubecentaury.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

     private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com id: " + id));
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User updateUser(Long id, User user) {
        User existing = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        existing.setNome(user.getNome());
        existing.setRole(user.getRole());
        return userRepository.save(existing);
    }
}




