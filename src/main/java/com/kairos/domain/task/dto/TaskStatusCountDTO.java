package com.kairos.domain.task.dto;

import com.kairos.domain.task.TaskStatus;

public record TaskStatusCountDTO(
		TaskStatus status,
		Long count) {

}
