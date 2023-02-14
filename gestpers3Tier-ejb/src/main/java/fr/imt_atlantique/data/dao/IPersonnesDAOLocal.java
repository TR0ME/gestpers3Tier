package fr.imt_atlantique.data.dao;

import java.util.List;

import jakarta.ejb.Local;

import fr.imt_atlantique.data.model.Personne;

@Local
public interface IPersonnesDAOLocal {

	void create(Personne pers);

	void update(Personne pers);

	Boolean remove(Personne pers);

	List<Personne> findAll(String tri, String ordre);

	Personne findById(Integer id);

	Personne findByName(String nom);

	Boolean isEmpty();
}
