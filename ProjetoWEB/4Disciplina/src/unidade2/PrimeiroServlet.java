package unidade2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PrimeiroServlet
 */
@WebServlet(description = "Este é o primeiro Servlet", urlPatterns = { "/PrimeiroServlet" })
public class PrimeiroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int acessos = 0;
	
    public PrimeiroServlet() {
    	super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		acessos++;
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<head><title>Servlet</title></head>");
		out.println("<body>");
		out.println("Esta é a ");
		out.println(this.getClass());		
		out.println(" usando o metodo GET, com numero de acessos igual a: " + acessos);
		out.println("</body>");
		out.println("<HTML>");
		out.flush();
		out.close();
	}
}