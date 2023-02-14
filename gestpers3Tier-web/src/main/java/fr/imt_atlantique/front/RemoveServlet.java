package fr.imt_atlantique.front;

import java.io.*;
import java.util.List;



import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import fr.imt_atlantique.data.model.Service;
import fr.imt_atlantique.services.IServicesBeanLocal;

import fr.imt_atlantique.data.model.Personne;
import fr.imt_atlantique.personnes.IPersonnesBeanLocal;
/**
 * Servlet implementation class RemoveServlet
 */
@WebServlet("/RemoveServlet")
public class RemoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@EJB
	IServicesBeanLocal sBean;
	@EJB
	IPersonnesBeanLocal pBean;
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RemoveServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			// demande de licenciement d'une personne
			if (request.getParameter("type").equals("personne")) {
				// TBD afficheLicenciement(out);
				afficheLicenciement(out);
			} else // demande fermeture d'un service
			{
				afficheCloseService(out);
			}
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
		} finally {
			out.close();
		}
	}

	/**
	 * Affichage du formulaire de licenciement d'une personne
	 */
	protected void afficheLicenciement(PrintWriter out) {
		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>" + "<h1>Licenciement<h1>"
				+ "<form action=\"/gestpers3Tier-web/LicenciementServlet\">"
				+ "<center><table>" 
				+ "<tr><td>Personnes</td><td>" + "<select name=\"personne\">");

		// Partie dynamique de la page
		for (Personne p : personnes("id", "asc")) // Infos sur les services
		{
			out.println("<option value=\"" + p.getId() + "\">" + p.getNom() + " "+ p.getPrenom()
					+ "</option>");
		}

		out.println("</select></table><br>"
				+ "<input type=\"submit\" value=\"OK\">" + "</form></center>"
				+ "</body></html>");
	}

	/**
	 * Affichage du formulaire de fermeture d'un service
	 */
	protected void afficheCloseService(PrintWriter out) {
		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>" + "<h1>Service à fermer<h1>"
				+ "<form action=\"/gestpers3Tier-web/CloseServiceServlet\">"
				+ "<center><table>" + "<tr><td>Nom</td><td>"
				+ "<input type=\"text\" name=\"nom\"></td></tr>");

		out.println("</select></table><br>"
				+ "<input type=\"submit\" value=\"OK\">" + "</form></center>"
				+ "</body></html>");
	}


	/**
	 * Liste des services de LENTREPRISE
	 * 
	 * @param tri
	 *            définit le critère de tri de la liste
	 * @param ordre
	 *            définit l'ordre (ascendant,descendent)
	 */
	protected List<Service> services(String tri, String ordre) {

		return sBean.listeServices(tri, ordre);
	}


	/**
	 * Liste des personnes de LENTREPRISE
	 * 
	 * @param tri
	 *            définit le critère de tri de la liste
	 * @param ordre
	 *            définit l'ordre (ascendant,descendent)
	 */
	protected List<Personne> personnes(String tri, String ordre) {

		return pBean.listePersonnes(tri, ordre);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}