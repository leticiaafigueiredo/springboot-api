package com.leticiafigueiredo.demo.services;

import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leticiafigueiredo.demo.models.Task;
import com.leticiafigueiredo.demo.models.User;

import com.leticiafigueiredo.demo.repositories.TaskRepository;


@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id) {

        Optional<Task> task = this.taskRepository.findById(id);

        return task.orElseThrow(() -> new RuntimeException(

                "Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()));

    }

    public List<Task> findAllByUserId(Long userId) {
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task obj) {

        User user = this.userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);

        return obj;

    }

    @Transactional
    public Task update(Task obj) { //so pode atualizar a descricao

        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());

        return this.taskRepository.save(newObj);

    }

    public void delete(Long id) {

        findById(id);

        try {

            this.taskRepository.deleteById(id);

        } catch (Exception e) {

            throw new RuntimeException("Não é possível excluir pois há entidades relacionadas!");

        }

    }

}