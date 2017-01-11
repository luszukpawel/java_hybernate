package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Ammunition;
import com.example.shdemo.domain.Ammunition;
import com.example.shdemo.domain.Weapon;
import com.example.shdemo.domain.Weapon;

public interface ArsenalManager 
{
	// add implementation 
	
	void addWeapon(Weapon Weapon);
	List<Weapon> getAllWeapons();
	void deleteWeapon(Weapon Weapon);
	Weapon findWeaponbyId(Long id);
	public Weapon findWeaponbyName(String name);
	public boolean editWeapon(Weapon Weapon);
	
	List<Ammunition> getOwnedAmmunitions(Weapon Weapon);
	List<Ammunition> getFreeAmmunitions();
	void sellAmmunition(Long WeaponId, Long AmmunitionId);
	
	Long addNewAmmunition(Ammunition Ammunition);
	List<Ammunition> getAllAmmunitions();
	void deleteAmmunition(Ammunition Ammunition);
	Ammunition findAmmunitionById(Long id);
	public boolean editAmmunition(Ammunition Ammunition);
}
