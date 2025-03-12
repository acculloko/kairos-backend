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
        projects.add(new Project(
        		"Kairos", 
        		"Web application designed for time logging, using SQL, Java Spring and Angular", 
        		LocalDate.of(2025, 03, 11), 
        		LocalDate.of(2026, 03, 11), 
        		ProjectStatus.ONGOING, 
        		users.get(0), 
        		ProjectPriority.HIGH));
        
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
        tasks.add(new Task(
        		projects.get(0),
        		"Create Database",
        		"Create SQL database for use with the API",
        		LocalDate.of(2025, 03, 11), 
        		LocalDate.of(2026, 03, 11),
        		TaskStatus.DONE,
        		users.get(0)));
        tasks.add(new Task(
                projects.get(0),
                "Develop REST API",
                "Implement the core REST API endpoints",
                LocalDate.of(2025, 4, 1),
                LocalDate.of(2025, 6, 30),
                TaskStatus.ONGOING,
                users.get(0)));
        tasks.add(new Task(
                projects.get(0),
                "UI Design",
                "Design wireframes and UI components",
                LocalDate.of(2025, 2, 15),
                LocalDate.of(2025, 5, 15),
                TaskStatus.OPEN,
                users.get(0)));
        tasks.add(new Task(
                projects.get(0),
                "Testing and QA",
                "Perform unit tests and integration tests",
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 7, 1),
                TaskStatus.PAUSED,
                users.get(0)));
        tasks.add(new Task(
                projects.get(0),
                "Deployment",
                "Deploy application to production environment",
                LocalDate.of(2025, 8, 1),
                LocalDate.of(2025, 8, 15),
                TaskStatus.CANCELLED,
                users.get(0)));
        tasks.add(new Task(
                projects.get(0),
                "Security Audit",
                "Conduct a security audit to identify vulnerabilities",
                LocalDate.of(2025, 6, 10),
                LocalDate.of(2025, 7, 10),
                TaskStatus.ONGOING,
                users.get(0)));
        tasks.add(new Task(
                projects.get(0),
                "Write Documentation",
                "Prepare API documentation for developers",
                LocalDate.of(2025, 3, 20),
                LocalDate.of(2025, 4, 15),
                TaskStatus.OPEN,
                users.get(0)));
        
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
        timeLogs.add(new TimeLog(
                tasks.get(0), 
                tasks.get(0).getResponsible_user(), 
                "Initial setup completed.", 
                LocalDateTime.of(2025, 3, 10, 9, 00), 
                LocalDateTime.of(2025, 3, 10, 11, 30)));
        timeLogs.add(new TimeLog(
                tasks.get(1), 
                tasks.get(0).getResponsible_user(), 
                "Database schema defined.", 
                LocalDateTime.of(2025, 3, 10, 14, 00), 
                LocalDateTime.of(2025, 3, 10, 16, 30)));
        timeLogs.add(new TimeLog(
                tasks.get(2), 
                tasks.get(0).getResponsible_user(), 
                "Started implementing API endpoints.", 
                LocalDateTime.of(2025, 3, 11, 10, 00), 
                LocalDateTime.of(2025, 3, 11, 13, 30)));
        timeLogs.add(new TimeLog(
                tasks.get(3), 
                tasks.get(0).getResponsible_user(), 
                "Optimized database queries.", 
                LocalDateTime.of(2025, 3, 11, 15, 00), 
                LocalDateTime.of(2025, 3, 11, 17, 30)));
        timeLogs.add(new TimeLog(
                tasks.get(4), 
                tasks.get(0).getResponsible_user(), 
                "Tested authentication module.", 
                LocalDateTime.of(2025, 3, 12, 9, 30), 
                LocalDateTime.of(2025, 3, 12, 12, 00)));
        timeLogs.add(new TimeLog(
                tasks.get(5), 
                tasks.get(0).getResponsible_user(), 
                "Fixed API response bugs.", 
                LocalDateTime.of(2025, 3, 12, 13, 00), 
                LocalDateTime.of(2025, 3, 12, 15, 30)));
        timeLogs.add(new TimeLog(
                tasks.get(6), 
                tasks.get(0).getResponsible_user(), 
                "Reviewed security vulnerabilities.", 
                LocalDateTime.of(2025, 3, 13, 10, 00), 
                LocalDateTime.of(2025, 3, 13, 12, 30)));
        timeLogs.add(new TimeLog(
                tasks.get(0), 
                tasks.get(0).getResponsible_user(), 
                "Implemented role-based access control.", 
                LocalDateTime.of(2025, 3, 13, 14, 30), 
                LocalDateTime.of(2025, 3, 13, 17, 00)));
        timeLogs.add(new TimeLog(
                tasks.get(1), 
                tasks.get(0).getResponsible_user(), 
                "Conducted final testing.", 
                LocalDateTime.of(2025, 3, 14, 9, 00), 
                LocalDateTime.of(2025, 3, 14, 11, 30)));
        timeLogs.add(new TimeLog(
                tasks.get(0), 
                tasks.get(0).getResponsible_user(), 
                "Prepared project documentation.", 
                LocalDateTime.of(2025, 3, 14, 13, 30), 
                LocalDateTime.of(2025, 3, 14, 16, 00)));

        
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
