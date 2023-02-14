package fr.imt_atlantique.personnes;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import fr.imt_atlantique.data.dao.IPersonnesDAOLocal;
import fr.imt_atlantique.data.model.Personne;

/**
 * Session Bean implementation class PersonnesBean
 */
@Stateless(mappedName="PersonnesBean")
public class PersonnesBean implements IPersonnesBeanLocal{

	@EJB
	private IPersonnesDAOLocal sDAO;
	
    /**
     * Default constructor. 
     */
    public PersonnesBean() {
    }

	@Override
	public List<Personne> listePersonnes(String tri, String ordre) {
		// TODO Auto-generated method stub
		return sDAO.findAll(tri, ordre);
	}
    
    

}
