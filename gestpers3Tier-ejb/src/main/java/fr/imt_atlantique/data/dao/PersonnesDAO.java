package fr.imt_atlantique.data.dao;

import java.util.List;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import fr.imt_atlantique.data.model.Personne;

/**
 * Session Bean implementation class PersonnesDAO
 */
@Stateless
@LocalBean
public class PersonnesDAO implements IPersonnesDAOLocal {
	@PersistenceContext(unitName = "gestpers3Tier-ejbPU")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public PersonnesDAO() {
	}

	@Override
	public void create(Personne pers) {
		if (pers == null) {
			return;
		}
		em.persist(pers);
	}

	@Override
	public void update(Personne pers) {
		if (pers == null) {
			return;
		}

		em.merge(pers);
	}

	@Override
	public Boolean remove(Personne s) {
		Personne persToRemove = findById(s.getId());
		try {
			em.remove(persToRemove);
		} catch (Exception pe) {
			System.err.println("Problem when deleting an entity ");
			return false;
		}
		return true;
	}

	@Override
	public List<Personne> findAll(String tri, String ordre) {
		String r = "select s from Personne s order by s." + tri;
		if (ordre.equals("asc")) {
			r += " ASC";
		} else {
			r += " DESC";
		}
		Query q = em.createQuery(r);
		return (List<Personne>) q.getResultList();
	}

	@Override
	public Personne findById(Integer id) {
		Query q = em.createNamedQuery("Personne.findById");
		q.setParameter("id", id);
		return (Personne) q.getSingleResult();
	}

	   @Override
	    public Personne findByName(String n) {
	        String req = "select s from Personne s where s.nom=:nom";
	        Query q = em.createQuery(req);
	        q.setParameter("nom", n);

	        try {
	            Personne res = (Personne) q.getSingleResult();
	            return res;
	        } catch (NoResultException ex) {
	            return null;
	        }
	    }
	   
	@Override
	public Boolean isEmpty() {
		if (em.createNamedQuery("Personne.findAll").getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
}