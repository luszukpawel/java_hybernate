package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries(
{ @NamedQuery(name = "weapon.all", query = "Select p from Weapon p"),
		@NamedQuery(name = "weapon.byId", query = "Select p from Weapon p where p.id = :id"),
		@NamedQuery(name = "weapon.byName", query = "Select p from Weapon p where p.name = :name") })
public class Weapon
{
	private Long id;
	private String name = "";
	private List<Ammunition> ammunitions = new ArrayList<Ammunition>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Column(nullable = false)
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	// ???
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Ammunition> getAmmunitions()
	{
		return ammunitions;
	}

	public void setAmmunitions(List<Ammunition> ammunitions)
	{
		this.ammunitions = ammunitions;
	}
}
