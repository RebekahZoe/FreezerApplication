package com.bae.persistence.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "freezers")
public class Freezers {
	
	
	private Long id;
	private String freezerName;
	
	private Set<Items>items;
	
	public Freezers(String freezerName) {
		this.freezerName = freezerName;
	}

	public Freezers() {
		
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "freezerId")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFreezerName() {
		return freezerName;
	}

	public void setFreezerName(String freezerName) {
		this.freezerName = freezerName;
	}
	@OneToMany(mappedBy = "freezers", cascade = { CascadeType.ALL})
	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}
	
	
	

}
