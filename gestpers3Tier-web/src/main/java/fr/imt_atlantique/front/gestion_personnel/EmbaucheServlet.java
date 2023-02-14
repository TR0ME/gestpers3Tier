package fr.imt_atlantique.front.gestion_personnel;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;


import jakarta.ejb.EJB;
import fr.imt_atlantique.data.dao.IPersonnesDAOLocal;
import fr.imt_atlantique.data.dao.IServicesDAOLocal;
import fr.imt_atlantique.data.model.Personne;
import fr.imt_atlantique.data.model.Service;

/**
 * Servlet implementation class EmbaucheServlet
 */
@WebServlet("/EmbaucheServlet")
public class EmbaucheServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private IPersonnesDAOLocal pDAO;
	@EJB
	private IServicesDAOLocal sDAO;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmbaucheServlet() {
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
			embauche(request.getParameter("nom"),
					request.getParameter("prenom"),
					request.getParameter("telephone"),
					request.getParameter("fonction"),
					request.getParameter("service"), out);
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
		} finally {
			out.close();
		}
	}

	protected void afficheEmbauche(String nom, String prenom, String telephone,
			String fonction, String service, PrintWriter out) {

		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>" + "<h1>Embauche de :</h1>" + "<table border=\"1\">"
				+ "<tr><b>Nom</b> : " + nom + "<br>" + "<tr><b>Prénom</b> : "
				+ prenom + "<br>" + "<tr><b>Téléphone</b> : " + telephone
				+ "<br>" + "<tr><b>Fonction</b> : " + fonction + "<br>"
				+ "<tr><b>Service</b> : " + service + "<br>");
	}

	/**
	 * Méthode pour embaucher une nouvelle personne dans LENTREPRISE
	 */
	protected void embauche(String nom, String prenom, String telephone,
			String fonction, String service, PrintWriter out) {

		afficheEmbauche(nom, prenom, telephone, fonction, service, out);

		// TODO Appel à l'EJB
		// Insère la nouvelle personne dans l'ensemble du personnel
		Personne p = new Personne();
		Service s = sDAO.findById(new Integer(service));
		p.setNom(nom);
		p.setPrenom(prenom);
		p.setTelephone(telephone);
		p.setFonction(fonction);
		p.setService(s);
		pDAO.create(p);

		out.println("</table>");
		out.println("<center><h3> Effectué</h3></center>");
		out.println("<hr>");
		out.println("<a href=\"index.jsp\">Page principale <img src=\"images/return.gif\" alt=\"return\"></a>");
		out.println("</body>");
		out.println("</html>");
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
