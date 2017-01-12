package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Ammunition;
import com.example.shdemo.domain.Weapon;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = false)
@Transactional
public class ArsenalManagerTest {

	@Autowired
	ArsenalManager arsenalManager;

	private final String WEAPON_NAME_2 = "AK-47";
	
	private final String AMMO_NAME_1 = "FMJ";
	private final String AMMO_NAME_2 = "HP";
	
	private final int COST_1 = 100;
	private final int COST_2 = 200;

	private final int CALIBER_1 = 21;
	private final int CALIBER_2 = 44;

	@Before
	public void setup() {
		if (arsenalManager.findWeaponbyName(WEAPON_NAME_2) == null) {
			Ammunition ammunition = new Ammunition();
			ammunition.setName(AMMO_NAME_2);
			ammunition.setCost(COST_2);
			ammunition.setCaliber(CALIBER_2);
			ammunition.setInWeapon(false);

			Weapon weapon = new Weapon();
			weapon.setName(WEAPON_NAME_2);
			weapon.getAmmunitions().add(ammunition);
			ammunition.setInWeapon(true);

			arsenalManager.addWeapon(weapon);
		}
	}
	@Test
	public void addWeaponTest() {
		Weapon weapon = new Weapon();
		weapon.setName(AMMO_NAME_1);

		arsenalManager.addWeapon(weapon);
		Weapon recievedWeapon = arsenalManager.findWeaponbyName(AMMO_NAME_1);
		assertEquals(AMMO_NAME_1, recievedWeapon.getName());
	}

	@Test
	public void deleteWeaponTest() {
		int WeaponCount = arsenalManager.getAllWeapons().size();
		int AmmunitionCount = arsenalManager.getAllAmmunitions().size();

		Weapon weapon = arsenalManager.findWeaponbyName(WEAPON_NAME_2);
		Long weaponId = weapon.getId();
		arsenalManager.deleteWeapon(weapon);

		assertEquals(null, arsenalManager.findWeaponbyId(weaponId));
		assertEquals(WeaponCount - 1, arsenalManager.getAllWeapons().size());
		//assertEquals(AmmunitionCount, arsenalManager.getAllAmmunitions().size());
	}

	@Test
	public void editWeaponTest() {
		Weapon weapon = arsenalManager.findWeaponbyName(WEAPON_NAME_2);
		weapon.setName("HolyHandGranade");
		Long WeaponId = weapon.getId();
		arsenalManager.editWeapon(weapon);

		Weapon weapon2 = arsenalManager.findWeaponbyId(WeaponId);

		assertEquals("HolyHandGranade", weapon2.getName());
	}

	@Test
	public void getWeaponByIdTest() {
		Weapon weapon = arsenalManager.findWeaponbyName(WEAPON_NAME_2);

		Weapon byId = arsenalManager.findWeaponbyId(weapon.getId());

		assertEquals(weapon.getId(), byId.getId());
	}

	@Test
	public void getAssignedAmmunitionsTest() {
		Weapon weapon = arsenalManager.findWeaponbyName(WEAPON_NAME_2);
		List<Ammunition> ownedAmmunitions = arsenalManager.getAssignedAmmunitions(weapon);

		assertEquals(AMMO_NAME_2, ownedAmmunitions.get(0).getName());
		assertEquals(COST_2, ownedAmmunitions.get(0).getCost());
		assertEquals(CALIBER_2, ownedAmmunitions.get(0).getCaliber());

	}

	@Test
	public void addAmmunitionTest() {
		Ammunition ammunition = new Ammunition();
		ammunition.setName(AMMO_NAME_2);
		ammunition.setCost(COST_2);
		ammunition.setCaliber(CALIBER_2);
		ammunition.setInWeapon(false);

		Long ammunitionId = arsenalManager.addNewAmmunition(ammunition);
		Ammunition retrievedAmmunition = arsenalManager.findAmmunitionById(ammunitionId);

		assertEquals(AMMO_NAME_2, retrievedAmmunition.getName());
		assertEquals(COST_2, retrievedAmmunition.getCost());
		assertEquals(CALIBER_2, retrievedAmmunition.getCaliber());

	}

	@Test
	public void getAmmunitionByIdTest() {
		Weapon weapon = arsenalManager.findWeaponbyName(WEAPON_NAME_2);
		Ammunition med = weapon.getAmmunitions().get(0);
		Ammunition byId = arsenalManager.findAmmunitionById(weapon.getAmmunitions().get(0).getId());

		assertEquals(med.getId(), byId.getId());
	}

	@Test
	public void editAmmunitionTest() {
		Weapon weapon = arsenalManager.findWeaponbyName(WEAPON_NAME_2);
		Ammunition ammunition = weapon.getAmmunitions().get(0);
		ammunition.setName("Change");
		ammunition.setCaliber(999);
		ammunition.setCost(999);
		Long WeaponId = weapon.getId();
		arsenalManager.editAmmunition(ammunition);

		Weapon weapon2 = arsenalManager.findWeaponbyId(WeaponId);

		assertEquals("Change", weapon2.getAmmunitions().get(0).getName());
		assertEquals(999, weapon2.getAmmunitions().get(0).getCaliber());
		assertEquals(999, weapon2.getAmmunitions().get(0).getCost());
	}

	@Test
	public void deleteAmmunitionTest() {
		int WeaponCount = arsenalManager.getAllWeapons().size();
		int AmmunitionCount = arsenalManager.getAllAmmunitions().size();

		Weapon weapon = arsenalManager.findWeaponbyName(WEAPON_NAME_2);
		int AmmunitioninWeapon = weapon.getAmmunitions().size();
		Ammunition ammunition = weapon.getAmmunitions().get(0);
		arsenalManager.deleteAmmunition(ammunition);

		weapon = arsenalManager.findWeaponbyName(WEAPON_NAME_2);

		assertEquals(WeaponCount, arsenalManager.getAllWeapons().size());
		assertEquals(AmmunitionCount - 1, arsenalManager.getAllAmmunitions().size());
		assertEquals(AmmunitioninWeapon - 1, weapon.getAmmunitions().size());
	}

}
