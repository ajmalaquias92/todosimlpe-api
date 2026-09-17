package com.adrosystems.todosimple.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adrosystems.todosimple.models.User;
import com.adrosystems.todosimple.repositories.TaskRepository;
import com.adrosystems.todosimple.repositories.UserRepository;

import jakarta.transaction.Transactional;

@Service 
public class UserService {
    private UserRepository user;
    private TaskRepository task;

    public User findById(Long id) {
        Optional<User> user = this.user.findById(id);
        return user.orElseThrow(() -> new RuntimeException("Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
    }

    @Transactional 
    public User createUser(User user) {
        user.setId(null);
        user = this.user.save(user);
        this.task.saveAll(user.getTasks());
        return user;
    }

    @Transactional 
    public User updateUser(User user) {
        User updatedUser = findById(user.getId());
        updatedUser.setPassword(user.getPassword());
        return this.user.save(updatedUser);
    }

    public void deleteUser(Long id) {
        findById(id);
        try {
            this.user.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possívele eliminar este usuário, pois está relacionado com outras entidades.");
        }
    }
}