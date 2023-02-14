package fr.imt_atlantique.services;

import java.util.List;

import jakarta.ejb.Remote;

import fr.imt_atlantique.data.model.Service;

@Remote
public interface IServicesBeanRemote {

	List<Service> listeServices(String tri, String ordre);
	List<String> listeNomsServices();

}
