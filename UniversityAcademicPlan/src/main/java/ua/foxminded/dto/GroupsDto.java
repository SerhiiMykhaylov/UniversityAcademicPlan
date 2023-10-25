package ua.foxminded.dto;

import java.util.List;

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
public class GroupsDto {

	private long id;
	@NonNull
	private String name;
	private List<StudentDto> student;
	private List<CourseDto> course;

}
