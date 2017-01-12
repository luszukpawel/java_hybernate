package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

// ???
@Entity
@NamedQueries({
	@NamedQuery(name = "ammunition.all", query = "Select m from Ammunition m"),
	@NamedQuery(name = "ammunition.byId", query = "Select m from Ammunition m where m.id = :id"),
})
public class Ammunition {
	private Long id;
	private String name;
	private int cost;
	private int caliber;
	private boolean inWeapon = false;
	
	public boolean isInWeapon() {
		return inWeapon;
	}
	public void setInWeapon(boolean inWeapon) {
		this.inWeapon = inWeapon;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public int getCaliber() {
		return caliber;
	}
	public void setCaliber(int caliber) {
		this.caliber = caliber;
	}
	
	
	
}
