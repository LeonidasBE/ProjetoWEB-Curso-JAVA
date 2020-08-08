package unidade2;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServletLeitor
 */
@WebServlet("/ServletLeitor")
public class ServletLeitor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLeitor() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String cpf = session.getAttribute("cpf").toString();
		String senha = session.getAttribute("senha").toString();
		ServletContext context = getServletContext();
		String classe = context.getAttribute("classe").toString();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
		out.println("<head><title>Servlet</title></head>");
		out.println("<body>");
		out.println("CPF: " + cpf);
		out.println("Senha: " + senha);
		out.println("Classe: " + classe);
		out.println("<br/>Servlet Leitor");
		out.println("</body>");
		out.println("<HTML>");
		out.flush();
		out.close();
	}

}
