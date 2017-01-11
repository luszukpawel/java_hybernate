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

		
		// Weapon
		public void addWeapon(Weapon Weapon) {
			sessionFactory.getCurrentSession().persist(Weapon);
		}
		
		@SuppressWarnings("unchecked")
		public List<Weapon> getAllWeapons() {
			return sessionFactory.getCurrentSession().getNamedQuery("Weapon.all").list();
		}

		public void deleteWeapon(Weapon Weapon) {
			Weapon = (Weapon) sessionFactory.getCurrentSession().get(Weapon.class, Weapon.getId());
			for(Ammunition Ammunition : Weapon.getAmmunitions()){
				sessionFactory.getCurrentSession().delete(Ammunition);
			}
			sessionFactory.getCurrentSession().delete(Weapon);
		}

		public Weapon findWeaponbyId(Long id) {
			return (Weapon) sessionFactory.getCurrentSession().get(Weapon.class, id);
		}
		
		public Weapon findWeaponbyName(String name) {
			List<Weapon> pharmacies =  sessionFactory.getCurrentSession().getNamedQuery("Weapon.byName").setString("name", name).list();
			if(pharmacies.size() == 0){
				return null;
			}else{
				return pharmacies.get(0);
			}
		}
		
		public boolean editWeapon(Weapon Weapon){
			try{
				sessionFactory.getCurrentSession().update(Weapon);
			}catch(Exception e){
				return false;
			}
			return true;
		}
		
		
		//Ammunition

		public Long addNewAmmunition(Ammunition Ammunition) {
			Ammunition.setId(null);
			return (Long)sessionFactory.getCurrentSession().save(Ammunition);
			
		}

		@SuppressWarnings("unchecked")
		public List<Ammunition> getAllAmmunitions() {
			return sessionFactory.getCurrentSession().getNamedQuery("Ammunition.all").list();
		}

		public void deleteAmmunition(Ammunition Ammunition) {
			Ammunition _Ammunition = (Ammunition) sessionFactory.getCurrentSession().get(Ammunition.class, Ammunition.getId());
			
			List<Weapon> pharmacies = getAllWeapons();
			for(Weapon p : pharmacies){
				for(Ammunition m : p.getAmmunitions()){
					if(m.getId() == _Ammunition.getId()){
						p.getAmmunitions().remove(m);
						sessionFactory.getCurrentSession().update(p);
						break;
					}
				}
			}
			sessionFactory.getCurrentSession().delete(_Ammunition);
		}

		public Ammunition findAmmunitionById(Long id) {
			return (Ammunition) sessionFactory.getCurrentSession().get(Ammunition.class, id);
		}

		public List<Ammunition> getOwnedAmmunitions(Weapon Weapon) {
			Weapon = (Weapon) sessionFactory.getCurrentSession().get(Weapon.class, Weapon.getId());
			List<Ammunition> Ammunitions = new ArrayList<Ammunition>(Weapon.getAmmunitions());
			return Ammunitions;
		}
		
		@SuppressWarnings("unchecked")
		public List<Ammunition> getFreeAmmunitions() {
			return sessionFactory.getCurrentSession().getNamedQuery("Ammunition.notSold").list();
		}
		
		public boolean editAmmunition(Ammunition Ammunition){
			try{
				sessionFactory.getCurrentSession().update(Ammunition);
			}catch(Exception e){
				return false;
			}
			return true;
		}

		public void sellAmmunition(Long WeaponId, Long AmmunitionId) {
			Weapon Weapon = (Weapon) sessionFactory.getCurrentSession().get(Weapon.class, WeaponId);
			Ammunition Ammunition = (Ammunition) sessionFactory.getCurrentSession().get(Ammunition.class, AmmunitionId);
			Ammunition.setInWeapon(true);
			Weapon.getAmmunitions().add(Ammunition);
		}
	}

	

