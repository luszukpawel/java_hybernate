package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Ammunition;
import com.example.shdemo.domain.Weapon;

@Component
@Transactional
public class ArsenalManagerHibernateImpl implements ArsenalManager
{
	
		
		@Autowired
		private SessionFactory sessionFactory;
		
		public void setSessionFactory(SessionFactory _sessionFactory){
			this.sessionFactory = _sessionFactory;
		}

		
		
		public void addWeapon(Weapon Weapon) {
			
		}
		
		@SuppressWarnings("unchecked")
		public List<Weapon> getAllWeapons() {
			return null;
		}

		public void deleteWeapon(Weapon Weapon) {

		}

		public Weapon findWeaponbyId(Long id) {
			return null;
		}
		
		public Weapon findWeaponbyName(String name) {
		
				return null;
			
		}
		
		public boolean editWeapon(Weapon Weapon){
		
			return true;
		}
		
		
		//Ammunition

		public Long addNewAmmunition(Ammunition Ammunition) {
			
			return null;
			
		}

		@SuppressWarnings("unchecked")
		public List<Ammunition> getAllAmmunitions() {
			return null;
		}

		public void deleteAmmunition(Ammunition Ammunition) {

		}

		public Ammunition findAmmunitionById(Long id) {
			return null;
		}

		public List<Ammunition> getOwnedAmmunitions(Weapon Weapon) {
	;
			return null;
		}
		
		@SuppressWarnings("unchecked")
		public List<Ammunition> getFreeAmmunitions() {
			return null;
		}
		
		public boolean editAmmunition(Ammunition Ammunition){
		
			return true;
		}

		public void sellAmmunition(Long WeaponId, Long AmmunitionId) {
	
		}
	}

	

