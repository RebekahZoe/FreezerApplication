
package com.bae.persistence.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "freezers")
@ToString
@EqualsAndHashCode
public class Freezers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "freezerId")
	private Long id;
	private String freezerName;

	@OneToMany
	private Set<Items> items = new HashSet<>();

	public Freezers(String freezerName) {
		this.freezerName = freezerName;
	}

	public Freezers(String freezerName, Long id) {
		this.freezerName = freezerName;
		this.id = id;
	}

	public Freezers() {

	}

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

	public Set<Items> getItems() {
		return items;
	}

	public void setItems(Set<Items> items) {
		this.items = items;
	}

}
