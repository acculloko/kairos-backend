package com.kairos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.kairos.domain.project.Project;
import com.kairos.domain.project.dto.ProjectCreationDTO;
import com.kairos.domain.project.dto.ProjectResponseDTO;
import com.kairos.domain.user.User;
import com.kairos.domain.user.dto.UserResponseDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creation_date", ignore = true)
	@Mapping(target = "responsible_user", ignore = true)
	Project projectCreationDtoToProject(ProjectCreationDTO dto);
	
	ProjectResponseDTO projectToProjectResponseDto(Project project);
	
	List<ProjectResponseDTO> projectListToProjectResponseDto(List<Project> projects);

}
