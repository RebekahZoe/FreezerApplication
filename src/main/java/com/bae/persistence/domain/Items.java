package com.bae.persistence.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Items {

	private Long id;
	private String itemName;
	private int quantity;
	private static Freezers freezer;
	
	public Items() {
		super();
	}

	public Items(String itemName) { 
		this.itemName = itemName;
	}
	
	

	public Items(Freezers freezers) {
		super();
		this.freezer = freezers; 
	}

	public Items(String itemName, int quantity, Freezers freezers) {
		super();
		this.itemName = itemName;
		this.quantity = quantity; 
		this.freezer = freezers;
	}

	public Items(String itemName, int quantity) {
		this.itemName = itemName;
		this.quantity = quantity; 
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	@ManyToOne
	@JoinColumn(name = "freezerId")
	public static Freezers getFreezers() {
		return freezer;
	}

	public static void setFreezers(Freezers freezers) {
		Items.freezer = freezers;
	}

	
	
}
