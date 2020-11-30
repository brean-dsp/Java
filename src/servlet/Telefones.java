package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanTelefone;
import beans.Beanportfolio;
import dao.DaoTelefones;
import dao.DaoUsuario;

@WebServlet("/salvarTelefones")
public class Telefones extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	private DaoTelefones daoTelefones = new DaoTelefones();

	public Telefones() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			
			String acao = request.getParameter("acao");
			if(acao.equals("addFone")) {

			String user = request.getParameter("user");

			Beanportfolio usuario = daoUsuario.consultar(user);

			request.getSession().setAttribute("userEscolhido", usuario);
			request.setAttribute("userEscolhido", usuario);

			RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
			request.setAttribute("telefones", daoTelefones.listar(usuario.getId()));
			view.forward(request, response);
			
			}else if(acao.equals("deleteFone")) {
				
				String foneId = request.getParameter("foneId");
				daoTelefones.delete(foneId);
				
				Beanportfolio beanPortfolio = (Beanportfolio) request.getSession().getAttribute("userEscolhido");
				
				RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
				request.setAttribute("telefones", daoTelefones.listar(beanPortfolio.getId()));
				request.setAttribute("msg", "Removido com sucesso!");
				view.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {

		Beanportfolio beanPortfolio = (Beanportfolio) request.getSession().getAttribute("userEscolhido");

		String numero = request.getParameter("numero");
		String tipo = request.getParameter("tipo");

		BeanTelefone beanTelefone = new BeanTelefone();
		beanTelefone.setNumero(numero);
		beanTelefone.setTipo(tipo);
		beanTelefone.setUsuario(beanPortfolio.getId());
		
		
		daoTelefones.salvar(beanTelefone);
		
		request.getSession().setAttribute("userEscolhido", beanPortfolio);
		request.setAttribute("userEscolhido", beanPortfolio);
		
		RequestDispatcher view = request.getRequestDispatcher("telefones.jsp");
		request.setAttribute("telefones", daoTelefones.listar(beanPortfolio.getId()));
		request.setAttribute("msg", "Cadastro salvo com sucesso!");
		view.forward(request, response);
		
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
