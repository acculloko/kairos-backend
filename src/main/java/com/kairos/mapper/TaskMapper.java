package com.kairos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.kairos.domain.task.Task;
import com.kairos.domain.task.dto.TaskCreationDTO;
import com.kairos.domain.task.dto.TaskResponseDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

	Task taskCreationDtoToTask(TaskCreationDTO dto);
	
	TaskResponseDTO taskToTaskResponseDto(Task task);
	
	List<TaskResponseDTO> taskListToTaskResponseDto(List<Task> tasks);

}
