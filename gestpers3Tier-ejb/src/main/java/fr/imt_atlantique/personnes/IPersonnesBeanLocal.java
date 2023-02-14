package fr.imt_atlantique.personnes;


import java.util.List;
import jakarta.ejb.Local;

import fr.imt_atlantique.data.model.Personne;

@Local
public interface IPersonnesBeanLocal {

	List<Personne> listePersonnes(String tri, String ordre);
}