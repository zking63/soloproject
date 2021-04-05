package com.codingdojo.FundraisingProject.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table (name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="First name must not be empty.")
	private String firstName;
	@NotEmpty(message="Last name must not be empty.")
	private String lastName;
	@NotEmpty
	@Email(message="Must be a valid email.")
	private String email;
	@NotEmpty
	@Size(min=8, message="Password must be at least 8 characters.")
	private String password;
	@NotEmpty
	@Transient
	private String passwordConfirmation;
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;

}
