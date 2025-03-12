package com.kairos.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kairos.domain.timelog.TimeLog;
import com.kairos.domain.timelog.dto.TimeLogCreationDTO;
import com.kairos.domain.timelog.dto.TimeLogResponseDTO;
import com.kairos.mapper.TimeLogMapper;
import com.kairos.service.TimeLogService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/timelog")
public class TimeLogController {
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	@Autowired
	private TimeLogService timeLogService;
	
	@Autowired
	private TimeLogMapper timeLogMapper;
	
	@GetMapping
	public ResponseEntity<List<TimeLogResponseDTO>> getTimeLogs() {
		List<TimeLog> list = timeLogService.getTimeLogs();
		
		return ResponseEntity.ok().body(timeLogMapper.timeLogListToTimeLogResponseDto(list));
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<List<TimeLogResponseDTO>> getTimeLogsByUserId(@PathVariable Integer id) {
		List<TimeLog> list = timeLogService.getTimeLogsByUserId(id);
		
		return ResponseEntity.ok().body(timeLogMapper.timeLogListToTimeLogResponseDto(list));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TimeLogResponseDTO> findTimeLogById(@PathVariable Integer id) {
		TimeLog timeLog = timeLogService.findById(id);
		
		return ResponseEntity.ok().body(timeLogMapper.timeLogToTimeLogResponseDto(timeLog));
	}
	
	// Endpoint to get total logged hours for a specific user
    @GetMapping("/total-logged-hours/user/{id}")
    public ResponseEntity<?> getTotalLoggedHoursByUser(@PathVariable Integer id) {
    	return ResponseEntity.ok().body(timeLogMapper.numberToTotalHoursDto(timeLogService.getTotalLoggedHoursByUser(id)));
    }

    // Endpoint to get total logged hours for all users
    @GetMapping("/total-logged-hours")
    public ResponseEntity<?> getTotalLoggedHours() {
        return ResponseEntity.ok().body(timeLogMapper.numberToTotalHoursDto(timeLogService.getTotalLoggedHours()));
    }
	
	@GetMapping("/period-logged-hours")
	public ResponseEntity<?> getTotalLoggedHours(
	        @RequestParam Integer userId,
	        @RequestParam String startDate,
	        @RequestParam String endDate
	    ) {
	        // Parse the date without the time
	        LocalDate start = LocalDate.parse(startDate, FORMATTER);
	        LocalDate end = LocalDate.parse(endDate, FORMATTER);

	        // Convert to LocalDateTime with 00:00:00 for the start date and 23:59:59 for the end date
	        LocalDateTime startDateTime = start.atStartOfDay(); // 00:00:00
	        LocalDateTime endDateTime = end.atTime(23, 59, 59); // 23:59:59

	        return ResponseEntity.ok().body(timeLogMapper.numberToTotalHoursDto(timeLogService.getTotalLoggedHoursByUserByPeriod(userId, startDateTime, endDateTime)));
	    }
	
	@PostMapping
	public ResponseEntity<TimeLogResponseDTO> createTimeLog(@RequestBody @Valid TimeLogCreationDTO data) 
			throws URISyntaxException {
		TimeLog timeLog = timeLogService.createTimeLog(
				timeLogMapper.timeLogCreationDtoToTimeLog(data), 
				data.task_id(), 
				data.user_id());
		
		return ResponseEntity.created(new URI("/timelog/" + timeLog.getId()))
				.body(timeLogMapper.timeLogToTimeLogResponseDto(timeLog));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TimeLog> updateTimeLog(@RequestBody @Valid TimeLogCreationDTO data, @PathVariable Integer id) 
			throws URISyntaxException {
		TimeLog timeLog = timeLogMapper.timeLogCreationDtoToTimeLog(data);
		timeLog.setId(id);
		timeLogService.updateTimeLog(timeLog, data.task_id(), data.user_id());
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTimeLog(@PathVariable Integer id) 
			throws URISyntaxException {
		timeLogService.deleteTimeLogById(id);
		return ResponseEntity.noContent().build();
	}

}
