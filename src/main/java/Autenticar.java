
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet ("/login")
public class Autenticar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// Obtém a sessão corrente ou null
				HttpSession session = req.getSession(false);

				// recupera login e senha enviados pela tela de login
				String nome = req.getParameter("nome");
				String senha = req.getParameter("senha");

				// se já estiver logado
				if (session != null && session.getAttribute("login") != null) {
					// vai para a página principal
					req.getRequestDispatcher("/index").forward(req, resp);
					// se não está logado, verifica se o nome e senha estão corretos
				} else if (nome != null && senha != null && nome.equalsIgnoreCase("Amanda") && senha.equals("1234")) {

					// se a senha está correta então cria a sessão e salva o usuário
					session = req.getSession(true);

					// salva o usuário na senha
					session.setAttribute("login", nome);

					resp.setContentType("text/html");
					PrintWriter out = resp.getWriter();
					out.append("<html>" +
								"<head><title>Logado</title></head>" +
								"<body>" +
								"<h1>Nova sessão criada</h1>" +
								"<h3>Session ID: " + session.getId() + "</h3>" +
								"<h3>Session Creation Time: " + new Date(session.getCreationTime()) + "</h3>" +
								"<h3>Session Last Accessed Time: " + new Date(session.getLastAccessedTime()) + "</h3>" +
								"<h3>Session Max Inactive Interval: " + new Date(session.getMaxInactiveInterval()) + "</h3>" +
								"<a href='" + req.getContextPath() + "/index.html'><button>Continuar</button></a>" +
								"</body>" +
								"</html>"
							);
				} else {
					resp.sendRedirect(req.getContextPath() + "/tela_login.html");
				}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
