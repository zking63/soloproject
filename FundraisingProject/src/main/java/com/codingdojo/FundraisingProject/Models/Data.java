package com.codingdojo.FundraisingProject.Models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fundraising_data")
public class Data {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	@OneToOne(mappedBy="email", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	private Double email_average;
}
