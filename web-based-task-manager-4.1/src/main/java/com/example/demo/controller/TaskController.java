package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@Controller
public class TaskController {

    private final TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String showTaskList(Model model) {
        List<Task> tasks = repository.findAll();
        model.addAttribute("tasks", tasks);
        return "index";
    }

    @PostMapping("/tasks")
    @ResponseBody
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task savedTask = repository.save(task);
        return ResponseEntity.ok(savedTask); // Return the saved task as JSON
    }

    @PutMapping("/tasks/{id}/complete")
    @Transactional
    @ResponseBody
    public ResponseEntity<?> toggleTaskComplete(@PathVariable Long id) {
        return repository.findById(id)
            .map(task -> {
                task.setComplete(!task.isComplete()); // Make sure the getter is isComplete() and not getIsComplete()
                repository.save(task);
                return ResponseEntity.ok(task);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/tasks/{id}")
    @ResponseBody
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
