package com.kairos.domain.project;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
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
	private ProjectStatus status;
	
	@ManyToOne
	@JoinColumn(name = "responsible_user_id")
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private User responsible_user;
	
	@Column(name = "creation_date", updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	@CreationTimestamp
	private LocalDateTime creation_date;
	
	@Column(name = "priority")
	@Enumerated(EnumType.STRING)
	private ProjectPriority priority;
	
//	Constructors
	public Project(
			String name, 
			String description, 
			LocalDate start_date, 
			LocalDate end_date, 
			ProjectStatus status, 
			User responsible_user, 
			ProjectPriority priority) {
		this.name = name;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
		this.status = status;
		this.responsible_user = responsible_user;
		this.priority = priority;
	}
	
}
