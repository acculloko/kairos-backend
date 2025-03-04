package com.kairos.domain.timelog;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kairos.domain.task.Task;
import com.kairos.domain.user.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "time_log")
public class TimeLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "task_id")
	@OnDelete(action = OnDeleteAction.RESTRICT)
	@NotNull
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@NotNull
	private User user;
	
	@Column(name = "description")
	@NotBlank
	private String description;
	
	@Column(name = "start_date")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime start_date;
	
	@Column(name = "end_date")
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime end_date;
	
	@Column(name = "log_time")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime log_time;


//	Constructors
	public TimeLog(@NotNull Task task, @NotNull User user, @NotBlank String description,
			@NotNull LocalDateTime start_date, @NotNull LocalDateTime end_date) {
		super();
		this.task = task;
		this.user = user;
		this.description = description;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	
	
	
}
