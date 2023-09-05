package ua.foxminded.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import ua.foxminded.entity.Course;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
//@Table(name = "Course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column
	@NonNull
	private String name;
	@ManyToMany(mappedBy = "course")
	private List<Student> student;
	@ManyToMany
	@JoinTable(
			name = "course_teacher",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "teacher_id"))
	private List<Teacher> teacher;
	@ManyToOne
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private Location location;
	@OneToMany(mappedBy = "course")
	private List<Schedule> schedule;
	@OneToMany(mappedBy = "course")
	private List<Lecture> lecture;
	@ManyToMany(mappedBy = "course")
	private List<GroupSt> groupSt;

}