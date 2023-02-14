package fr.imt_atlantique.services;

import java.util.List;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.Remote;
import fr.imt_atlantique.data.dao.IServicesDAOLocal;
import fr.imt_atlantique.data.model.Service;

/**
 * Session Bean implementation class ServicesBean
 */
@Stateless(mappedName="ServicesBean")
@Remote(IServicesBeanRemote.class)
public class ServicesBean implements IServicesBeanRemote, IServicesBeanLocal {

	@EJB
	private IServicesDAOLocal sDAO;
	
    /**
     * Default constructor. 
     */
    public ServicesBean() {
    }

	@Override
	public List<Service> listeServices(String tri, String ordre) {
		// TODO Auto-generated method stub
		return sDAO.findAll(tri, ordre);
	}
    
	public List<String> listeNomsServices(){
		return sDAO.getNames();
	}

	

}
