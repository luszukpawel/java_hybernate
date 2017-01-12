package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Ammunition;
import com.example.shdemo.domain.Weapon;

public interface ArsenalManager {

	void addWeapon(Weapon weapon);
	List<Weapon> getAllWeapons();
	void deleteWeapon(Weapon weapon);
	Weapon findWeaponbyId(Long id);
	public Weapon findWeaponbyName(String name);
	public boolean editWeapon(Weapon weapon);
	
	Long addNewAmmunition(Ammunition ammunition);
	List<Ammunition> getAllAmmunitions();
	void deleteAmmunition(Ammunition ammunition);
	Ammunition findAmmunitionById(Long id);
	public boolean editAmmunition(Ammunition ammunition);
	
	List<Ammunition> getAssignedAmmunitions(Weapon weapon);


}
