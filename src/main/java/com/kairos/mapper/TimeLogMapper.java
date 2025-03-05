package com.kairos.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.kairos.domain.timelog.TimeLog;
import com.kairos.domain.timelog.dto.TimeLogCreationDTO;
import com.kairos.domain.timelog.dto.TimeLogResponseDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TimeLogMapper {

	TimeLog timeLogCreationDtoToTimeLog(TimeLogCreationDTO dto);
	
	TimeLogResponseDTO timeLogToTimeLogResponseDto(TimeLog timeLog);
	
	List<TimeLogResponseDTO> timeLogListToTimeLogResponseDto(List<TimeLog> timeLogs);

}
