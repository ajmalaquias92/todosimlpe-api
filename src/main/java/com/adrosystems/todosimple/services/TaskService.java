package com.adrosystems.todosimple.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrosystems.todosimple.models.Task;
import com.adrosystems.todosimple.models.User;
import com.adrosystems.todosimple.repositories.TaskRepository;

@Service 
public class TaskService {
    @Autowired 
    private TaskRepository task;

    @Autowired 
    private UserService user;

    public Task findById(Long id) {
        Optional<Task> task = this.task.findById(id);
        return task.orElseThrow(() -> new RuntimeException("Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));
    }

    public List<Task> findAllByUserId(Long id) {
        return task.findByUser_Id(id);
    }

    @Transactional 
    public Task createTask(Task task) {
        User user = this.user.findById(task.getUser().getId());
        task.setId(null);
        task.setUser(user);
        task = this.task.save(task);
        return task;
    }

    @Transactional 
    public Task updateTask(Task task) {
        Task updatedTask = findById(task.getId());
        updatedTask.setDescription(task.getDescription());
        return this.task.save(updatedTask);
    }

    public void deleteTask(Long id) {
        findById(id);
        this.task.deleteById(id);
    }
}