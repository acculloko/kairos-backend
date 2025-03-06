package com.kairos;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

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
		
//		User example data
		User user1 = new User("Lucca Costa", "lucca.costa@wise.com", "senhagamer", UserRole.ADMIN);
		User user2 = new User("Roger", "roger@wise.com", "12345", UserRole.USER);
		User user3 = new User("Ronaldo", "ronaldo@wise.com", "12345", UserRole.USER);
		User user4 = new User("Jefferson", "jefferson@wise.com", "12345", UserRole.USER);
		
		userRepository.save(user1);
		userRepository.save(user2);
		userRepository.save(user3);
		userRepository.save(user4);
		
//		Project example data
		Project project1 = new Project(
				"Day-to-day", 
				"Day-to-day tasks that don't fall into any major projects", 
				LocalDate.of(2024, 7, 24), 
				LocalDate.of(2090, 7, 24), 
				ProjectStatus.ONGOING, 
				user1, 
				ProjectPriority.LOW);
		Project project2 = new Project(
				"Kairos", 
				"Time logging web application", 
				LocalDate.of(2025, 3, 3), 
				LocalDate.of(2025, 3, 14), 
				ProjectStatus.ONGOING, 
				user1, 
				ProjectPriority.HIGH);
		Project project3 = new Project(
				"Kanban", 
				"Kanban table", 
				LocalDate.of(2024, 7, 24), 
				LocalDate.of(2025, 6, 15), 
				ProjectStatus.ONGOING, 
				user3, 
				ProjectPriority.MEDIUM);
		
		projectRepository.save(project1);
		projectRepository.save(project2);
		projectRepository.save(project3);
		
//		Task example data
		Task task1 = new Task(
				project2, 
				"Create database", 
				"Create database and insert example data", 
				LocalDate.of(2025, 3, 3), 
				LocalDate.of(2025, 3, 4), 
				TaskStatus.ONGOING, 
				user1);
		Task task2 = new Task(
				project3, 
				"Database maintenance", 
				"Update kanban tables", 
				LocalDate.of(2025, 2, 23), 
				LocalDate.of(2025, 3, 6), 
				TaskStatus.DONE, 
				user4);
		Task task3 = new Task(
				project1, 
				"Send out paychecks", 
				"Self-explanatory", 
				LocalDate.of(2025, 3, 3), 
				LocalDate.of(2025, 3, 4), 
				TaskStatus.CANCELLED, 
				user2);
		Task task4 = new Task(
				project2, 
				"Frontend development", 
				"Develop the frontend", 
				LocalDate.of(2025, 3, 3), 
				LocalDate.of(2025, 3, 14), 
				TaskStatus.OPEN, 
				user1);
		
		taskRepository.save(task1);
		taskRepository.save(task2);
		taskRepository.save(task3);
		taskRepository.save(task4);
		
//		TimeLog example data
		
		TimeLog log1 = new TimeLog(
				task1, 
				task1.getResponsible_user(), 
				"Done!", 
				LocalDateTime.of(2025, 3, 3, 19, 30), 
				LocalDateTime.of(2025, 3, 3, 22, 30));
		TimeLog log2 = new TimeLog(
				task2, 
				task2.getResponsible_user(), 
				"Did about a third of the tables", 
				LocalDateTime.of(2025, 3, 3, 16, 30), 
				LocalDateTime.of(2025, 3, 3, 18, 0));
		TimeLog log3 = new TimeLog(
				task3, 
				task3.getResponsible_user(), 
				"Did something", 
				LocalDateTime.of(2025, 3, 3, 19, 30), 
				LocalDateTime.of(2025, 3, 3, 22, 30));
		TimeLog log4 = new TimeLog(
				task1, 
				task1.getResponsible_user(), 
				"Did a thing", 
				LocalDateTime.of(2025, 3, 3, 10, 30), 
				LocalDateTime.of(2025, 3, 3, 11, 30));
		
		timeLogRepository.save(log1);
		timeLogRepository.save(log2);
		timeLogRepository.save(log3);
		timeLogRepository.save(log4);
		
	}

}
