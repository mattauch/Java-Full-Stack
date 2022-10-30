

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/listar")
public class Listar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.append("<html>" + "<head><title>Cookies</title></head>");

		// Recupera cookies recebidos no objeto request
		Cookie[] cookies = req.getCookies();

		// Quantos cookies tenho nessa aplicação?
		int quant = cookies != null ? cookies.length : 0;

		// Pega cada cookie do vetor e adiciona nome e valor html
		for (int i = 0; i < quant; i++) {
			Cookie cookie = cookies[i];
			out.append("Cookie nome: " + cookie.getName() + "<br>");
			out.append("Cookie valor: " + cookie.getValue() + "<br>");
			// por padrão retorna -1
			out.append("Cookie tempo: " + cookie.getMaxAge() + "<br><br>");
		}

			out.append("<a href='/projeto_cookies/'><button>Adicionar Cookies</button></a><br>" + "</body>" + "</html>");
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}

}
