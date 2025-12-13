package com.DevGuiBan.ToDoList.domain.taskList;

import com.DevGuiBan.ToDoList.domain.task.Task;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity(name = "taskList")
@Table(name = "taskList")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @OneToOne
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    private Task task;


}
