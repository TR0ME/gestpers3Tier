package fr.imt_atlantique.front.gestion_services;

import java.io.IOException;
import java.io.PrintWriter;

//(GS) 14-12-22
import fr.imt_atlantique.data.dao.IServicesDAOLocal;
import fr.imt_atlantique.data.model.Service;
import jakarta.ejb.EJB;
//(GS)

import javax.naming.NamingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertServiceServlet
 */
@WebServlet("/InsertServiceServlet")
public class InsertServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private IServicesDAOLocal sDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertServiceServlet() {
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
			insertService(request.getParameter("nom"), out);
		} catch (Exception e) {
			out.println("<html><body><h3><u>Problème</u></h3><br>"
					+ e.toString() + "</body></html>");
		} finally {
			out.close();
		}
	}

	/**
	 * Méthode pour insérer un nouveau service dans LENTREPRISE
	 */
	protected void insertService(String nom, PrintWriter out)
			throws NamingException {

		afficheInsertService(nom, out);

		// TODO Insère le nouveau service
		// Appel l'EJB correspondant pour le faire
		//(GS) - 14/12/22
		Service s = new Service();
		s.setNom(nom);
		sDAO.create(s);
		
		out.println("</table>");
		out.println("<center><h3> Effectué</h3></center>");
		out.println("<hr>");
		out.println("<a href=\"index.jsp\">Page principale <img src=\"images/return.gif\" alt=\"return\"></a>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void afficheInsertService(String nom, PrintWriter out) {

		out.println("<html>"
				+ "<head><title>Application de gestion du personnel de LENTREPRISE</title></head>"
				+ "<body>" + "<h1>Insertion du service :</h1>"
				+ "<table border=\"1\">" + "<tr><b>Nom</b> : " + nom + "<br>");
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