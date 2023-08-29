package org.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.ManyToMany;

@Entity
public class Teacher extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	public Teacher() {
	}
	@Column
	private String firstName;
	@Column
	private String lastName;
	@ManyToMany(mappedBy = "teacher")
	private List<Course> course;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String param) {
		this.firstName = param;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String param) {
		this.lastName = param;
	}

	public List<Course> getCourse() {
	    return course;
	}

	public void setCourse(List<Course> param) {
	    this.course = param;
	}

}