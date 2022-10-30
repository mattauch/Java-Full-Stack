

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet (urlPatterns = {"/index", "/index.html"},
				initParams = {
						@WebInitParam(name = "empresa", value="Livraria Super TI"),
						@WebInitParam(name= "cidade", value="Ponta Grossa"),
})
public class Principal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Obtém a sessão corrente ou null
				//true= cria a sessão
				HttpSession session = req.getSession(false);
				
				if(session == null || session.getAttribute("login") == null) {
					resp.sendRedirect(req.getContextPath() + "/tela_login.html");
				} else {
					String login = (String) session.getAttribute("login");
					//Obtém os parâmetros da Servlet
					String empresa = getInitParameter("empresa");
					String cidade = getInitParameter("cidade");
					
					resp.setContentType("text/html");
					PrintWriter out = resp.getWriter();
					out.append("<html>" +
								"<head><title>Principal</title></head>" +
								"<body>" +
								"<h1>Página Principal do sistema</h1>" +
								"Bem Vindo: " + login + "<br>" +
								"Empresa: " + empresa + "<br>" +
								"Cidade: " + cidade + "<br><br>" +
								"<a href='" + req.getContextPath() + "/logout'><button>Logout</button></a>" +
								"</body>" +
								"</html>"
							);
					
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
