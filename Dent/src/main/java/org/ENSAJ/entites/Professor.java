package org.ENSAJ.entites;

import java.util.List;

import jakarta.persistence.Entity;

@Entity
public class Professor extends User{
	
	private String grade;


	public Professor() {
		super();
	}



	public Professor(String grade, List<Groupe> groups) {
		super();
		this.grade = grade;
	}



	public String getGrade() {
		return grade;
	}


	public void setGrade(String grade) {
		this.grade = grade;
	}


}
