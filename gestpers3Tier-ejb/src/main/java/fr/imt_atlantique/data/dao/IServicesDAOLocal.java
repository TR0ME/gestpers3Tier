package fr.imt_atlantique.data.dao;

import java.util.List;

import jakarta.ejb.Local;

import fr.imt_atlantique.data.model.Service;

@Local
public interface IServicesDAOLocal {

	void create(Service service);

	void update(Service service);

	Boolean remove(Service s);

	List<Service> findAll(String tri, String ordre);

	Service findById(Integer id);

	Service findByName(String nom);

	List<String> getNames();

	Boolean isEmpty();

}
