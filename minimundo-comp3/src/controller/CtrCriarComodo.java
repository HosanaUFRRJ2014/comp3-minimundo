package controller;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ComodoDAO;
import entity.*;

/**
 * Servlet implementation class CtrComodo
 */
@WebServlet("/CtrCriarComodo")
public class CtrCriarComodo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CtrCriarComodo() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());

		String descricao = request.getParameter("descricao");

		if(descricao == "" )
		{
			HttpSession sessao = request.getSession();
		    sessao.setAttribute("voltar", "frontCriarComodo.jsp");
			//erro
			//parar aqui, pq redireciona para pag de erro
			RequestDispatcher rd = request.getRequestDispatcher("erroCampoInvalido.jsp");
	    	rd.forward(request, response);
		}


		String tipoComodo = request.getParameter("comAssociados");
		Comodo comodo;

		comodo= null;

		switch(tipoComodo)
		{
			case "Cozinha":
				comodo = new Cozinha();
				comodo.setTipoComodo(1);
				break;

			case "Sala":
				comodo = new Sala();
				comodo.setTipoComodo(2);
				break;
				
			case "Quarto":
				comodo = new Quarto();
				comodo.setTipoComodo(3);
				break;	

			case "ComodoComposto":
				comodo = new ComodoComposto();
				comodo.setTipoComodo(4);
			//acho que tem que dizer qual tipo de comodo o compõe
				break;
		}

		ComodoDAO cDAO = new ComodoDAO();

		if(comodo != null)
		{
			comodo.setDescricao(descricao);
			cDAO.inserir(comodo); 
		


			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
	    	rd.forward(request, response);
		}
		
		
		else
		{
			HttpSession sessao = request.getSession();
		    sessao.setAttribute("voltar", "frontCriarComodo.jsp");
			//erro
			//parar aqui, pq redireciona para pag de erro
			RequestDispatcher rd = request.getRequestDispatcher("erroCampoInvalido.jsp");
	    	rd.forward(request, response);
		}







		//		else
		//		{
		//erro > Fazer página de redirecionamento
		//criar minha própria exception
		//		}


	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
