package ua.foxminded.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Student extends Users {

	@NonNull
	@Column
	private String firstName;
	@NonNull
	@Column
	private String lastName;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne()
	@JoinColumn(name = "groups_id", referencedColumnName = "id")
	private Groups groups;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany()
	@JoinTable(
			name = "student_course",
			joinColumns = @JoinColumn(name = "student_id"),
			inverseJoinColumns = @JoinColumn(name = "course_id"))
	private List<Course> course;
	
}