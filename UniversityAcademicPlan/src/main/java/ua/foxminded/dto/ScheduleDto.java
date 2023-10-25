package ua.foxminded.dto;

import java.time.DayOfWeek;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class ScheduleDto {
	
	private long id;
	@NonNull
	private LocalTime startTime;
	@NonNull
	private LocalTime endTime;
	@NonNull
	private DayOfWeek dayOfWeek;
	private CourseDto course;
	

}
