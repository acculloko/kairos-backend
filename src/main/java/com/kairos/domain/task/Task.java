package com.kairos.domain.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairos.domain.project.Project;
import com.kairos.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;
	
	@Column(name = "name")
	@NotBlank
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "start_date")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate start_date;
	
	@Column(name = "end_date")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate end_date;
	
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private TaskStatus status;
	
	@ManyToOne
	@JoinColumn(name = "responsible_user_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private User responsible_user;
	
	@Column(name = "creation_date", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime creation_date;
	
//	Constructors
	public Task(Project project, @NotBlank String name, String description, @NotNull LocalDate start_date,
			@NotNull LocalDate end_date, TaskStatus status, User responsible_user) {
		super();
		this.project = project;
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.responsible_user = responsible_user;
	}
	
}
