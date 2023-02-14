package fr.imt_atlantique.services;


import java.util.List;
import jakarta.ejb.Local;

import fr.imt_atlantique.data.model.Service;

@Local
public interface IServicesBeanLocal {

	List<Service> listeServices(String tri, String ordre);
}