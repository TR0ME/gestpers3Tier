package fr.atlantique.imt.ejbremoteclient;

import javax.naming.*;
import java.util.List;
import java.util.Hashtable;
import java.util.Properties;
/*import eu.telecom_bretagne.data.model.Personne;
import eu.telecom_bretagne.services.IPersonnelBeanRemote;
*/
import fr.imt_atlantique.data.model.Service;
import fr.imt_atlantique.services.IServicesBeanRemote;

import org.wildfly.security.auth.client.*;
import java.util.concurrent.*;

public class MainTXT
{
	private static final long serialVersionUID = 1L;



	public MainTXT() {
     try{
          final Hashtable jndiProperties = new Hashtable();
          jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
          InitialContext context = new InitialContext(jndiProperties);
          //Configuration de la connexion distante dans le fichier wildfly-config.xml

          String jndiName = "ejb:gestpers3Tier-ear/fr.imt_atlantique-gestpers3Tier-web-1.0-SNAPSHOT/ServicesBean!fr.imt_atlantique.services.IServicesBeanRemote";
          IServicesBeanRemote isbr =  (IServicesBeanRemote) context.lookup(jndiName);
          List<Service> s = isbr.listeServices("nom","asc");
          for(Service si:  s){
              System.out.println("Service "+si);
          }
         System.out.println("IServucesBeanRemote Obtained"+isbr);
         
			} catch (Exception ex) {
                    System.err.println(ex);
                    ex.printStackTrace();
			}
		
	} 

	public static void main(String args[]) {
		new MainTXT();
	}
}
