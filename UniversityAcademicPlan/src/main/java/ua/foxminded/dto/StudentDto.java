package ua.foxminded.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class StudentDto extends UsersDto{
	
	@NonNull
	private String firstName;
	@NonNull
	private String lastName;
		
	private GroupsDto groups;
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private List<CourseDto> course;
}
