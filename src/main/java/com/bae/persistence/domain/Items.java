
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
	private  String itemName;
	private  int quantity;
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
	
	public  void setQuantity(int quantity) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
		result = prime * result + quantity;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (itemName == null) {
			if (other.itemName != null)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Items [id=" + id + ", itemName=" + itemName + ", quantity=" + quantity + ", freezer=" + freezer + "]";
	}

	
	
}

