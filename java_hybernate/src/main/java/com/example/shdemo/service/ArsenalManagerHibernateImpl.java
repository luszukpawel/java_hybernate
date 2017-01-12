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

	public void setSessionFactory(SessionFactory _sessionFactory)
	{
		this.sessionFactory = _sessionFactory;
	}

	public void addWeapon(Weapon weapon)
	{
		sessionFactory.getCurrentSession().persist(weapon);
	}

	@SuppressWarnings("unchecked")
	public List<Weapon> getAllWeapons()
	{
		return sessionFactory.getCurrentSession().getNamedQuery("weapon.all").list();
	}

	public void deleteWeapon(Weapon weapon)
	{
		weapon = (Weapon) sessionFactory.getCurrentSession().get(Weapon.class, weapon.getId());
		for (Ammunition ammunition : weapon.getAmmunitions())
		{
			sessionFactory.getCurrentSession().delete(ammunition);
		}
		sessionFactory.getCurrentSession().delete(weapon);
	}

	public Weapon findWeaponbyId(Long id)
	{
		return (Weapon) sessionFactory.getCurrentSession().get(Weapon.class, id);
	}

	public Weapon findWeaponbyName(String name)
	{
		List<Weapon> weapons = sessionFactory.getCurrentSession().getNamedQuery("weapon.byName").setString("name", name)
				.list();
		if (weapons.size() == 0)
		{
			return null;
		} else
		{
			return weapons.get(0);
		}
	}

	public boolean editWeapon(Weapon weapon)
	{
		try
		{
			sessionFactory.getCurrentSession().update(weapon);
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

	public Long addNewAmmunition(Ammunition ammunition)
	{
		ammunition.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(ammunition);

	}

	@SuppressWarnings("unchecked")
	public List<Ammunition> getAllAmmunitions()
	{
		return sessionFactory.getCurrentSession().getNamedQuery("ammunition.all").list();
	}

	public void deleteAmmunition(Ammunition ammunition)
	{
		Ammunition _ammunition = (Ammunition) sessionFactory.getCurrentSession().get(Ammunition.class,
				ammunition.getId());

		List<Weapon> weapons = getAllWeapons();
		for (Weapon p : weapons)
		{
			for (Ammunition m : p.getAmmunitions())
			{
				if (m.getId() == _ammunition.getId())
				{
					p.getAmmunitions().remove(m);
					sessionFactory.getCurrentSession().update(p);
					break;
				}
			}
		}
		sessionFactory.getCurrentSession().delete(_ammunition);
	}

	public Ammunition findAmmunitionById(Long id)
	{
		return (Ammunition) sessionFactory.getCurrentSession().get(Ammunition.class, id);
	}

	public List<Ammunition> getAssignedAmmunitions(Weapon weapon)
	{
		weapon = (Weapon) sessionFactory.getCurrentSession().get(Weapon.class, weapon.getId());
		List<Ammunition> ammunitions = new ArrayList<Ammunition>(weapon.getAmmunitions());
		return ammunitions;
	}

	public boolean editAmmunition(Ammunition ammunition)
	{
		try
		{
			sessionFactory.getCurrentSession().update(ammunition);
		} catch (Exception e)
		{
			return false;
		}
		return true;
	}

}
