package com.kairos;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kairos.domain.project.Project;
import com.kairos.domain.project.ProjectPriority;
import com.kairos.domain.project.ProjectStatus;
import com.kairos.domain.task.Task;
import com.kairos.domain.task.TaskStatus;
import com.kairos.domain.timelog.TimeLog;
import com.kairos.domain.user.User;
import com.kairos.domain.user.UserRole;
import com.kairos.repository.ProjectRepository;
import com.kairos.repository.TaskRepository;
import com.kairos.repository.TimeLogRepository;
import com.kairos.repository.UserRepository;

@SpringBootApplication
public class KairosApplication implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private TimeLogRepository timeLogRepository;

	public static void main(String[] args) {
		SpringApplication.run(KairosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
        // ===================== USERS =====================
        List<User> users = new ArrayList<>();
        users.add(new User("Lucca Costa", "lucca.costa@wise.com", "senhagamer", UserRole.ADMIN));
        users.add(new User("Roger", "roger@wise.com", "12345", UserRole.USER));
        users.add(new User("Ronaldo", "ronaldo@wise.com", "12345", UserRole.USER));
        users.add(new User("Jefferson", "jefferson@wise.com", "12345", UserRole.USER));
        users.add(new User("Sophia Martins", "sophia@wise.com", "pass123", UserRole.USER));
        users.add(new User("Daniel Smith", "daniel@wise.com", "adminPass", UserRole.ADMIN));
        users.add(new User("Emma Brown", "emma@wise.com", "test123", UserRole.USER));
        users.add(new User("Noah White", "noah@wise.com", "securepass", UserRole.USER));
        users.add(new User("Olivia Wilson", "olivia@wise.com", "welcome123", UserRole.USER));
        users.add(new User("Liam Green", "liam@wise.com", "mypassword", UserRole.ADMIN));
        users.add(new User("Ethan Black", "ethan@wise.com", "user123", UserRole.USER));
        users.add(new User("Ava Thomas", "ava@wise.com", "letmein", UserRole.USER));
        users.add(new User("Isabella Scott", "isabella@wise.com", "qwerty", UserRole.ADMIN));
        users.add(new User("Mason Lee", "mason@wise.com", "hello123", UserRole.USER));
        users.add(new User("Lucas Hall", "lucas@wise.com", "supersecure", UserRole.USER));
        users.add(new User("Mia Allen", "mia@wise.com", "work123", UserRole.USER));
        users.add(new User("James Adams", "james@wise.com", "pass4321", UserRole.USER));
        users.add(new User("Charlotte Hill", "charlotte@wise.com", "easyPass", UserRole.ADMIN));
        users.add(new User("Benjamin King", "benjamin@wise.com", "access123", UserRole.USER));
        users.add(new User("William Rivera", "william@wise.com", "testme", UserRole.USER));

        userRepository.saveAll(users);

        // ===================== PROJECTS =====================
        List<Project> projects = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            projects.add(new Project(
                    "Project " + i, 
                    "Description for project " + i, 
                    LocalDate.of(2024, (i % 12) + 1, (i % 28) + 1), 
                    LocalDate.of(2026, (i % 12) + 1, (i % 28) + 1), 
                    switch (i % 4) {
                        case 0 -> ProjectStatus.PENDING;
                        case 1 -> ProjectStatus.ONGOING;
                        case 2 -> ProjectStatus.COMPLETED;
                        default -> ProjectStatus.CANCELLED;
                    }, 
                    users.get(i % users.size()), 
                    switch (i % 3) {
                        case 0 -> ProjectPriority.HIGH;
                        case 1 -> ProjectPriority.MEDIUM;
                        default -> ProjectPriority.LOW;
                    }));
        }

        projectRepository.saveAll(projects);

        // ===================== TASKS =====================
        List<Task> tasks = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            tasks.add(new Task(
                    projects.get(i % projects.size()), 
                    "Task " + i, 
                    "Details for task " + i, 
                    LocalDate.of(2025, (i % 12) + 1, (i % 28) + 1), 
                    LocalDate.of(2025, ((i + 1) % 12) + 1, ((i + 1) % 28) + 1), 
                    switch (i % 5) {
                        case 0 -> TaskStatus.OPEN;
                        case 1 -> TaskStatus.ONGOING;
                        case 2 -> TaskStatus.DONE;
                        case 3 -> TaskStatus.PAUSED;
                        default -> TaskStatus.CANCELLED;
                    }, 
                    users.get(i % users.size())));
        }

        taskRepository.saveAll(tasks);

        // ===================== TIME LOGS =====================
        List<TimeLog> timeLogs = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            timeLogs.add(new TimeLog(
                    tasks.get(i % tasks.size()), 
                    tasks.get(i % tasks.size()).getResponsible_user(), 
                    "Worked on task " + i, 
                    LocalDateTime.of(2025, (i % 12) + 1, (i % 28) + 1, 9, 0), 
                    LocalDateTime.of(2025, (i % 12) + 1, (i % 28) + 1, 17, 0)));
        }

        timeLogRepository.saveAll(timeLogs);
		
	}

}
