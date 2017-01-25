package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ComodoDAO;
import entity.Comodo;

/**
 * Servlet implementation class CtrRemComodo
 */
@WebServlet("/CtrRemComodo")
public class CtrRemComodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrRemComodo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id_c = request.getParameter("comodoAcao");
		
		long id = Long.parseLong(id_c);
		
		ComodoDAO cDAO = new ComodoDAO();
		
		Comodo comodo = cDAO.buscarPorId(id_c).get(0);
		
		if(comodo.getMobiliaDisponivel().size() == 0 && comodo.getTipoComodo() != 4)
			cDAO.remover(comodo);
		
		else
		{
			HttpSession sessao = request.getSession();
		    sessao.setAttribute("voltar", "frontListarComodo.jsp");
			//erro
			//parar aqui, pq redireciona para pag de erro
			RequestDispatcher rd = request.getRequestDispatcher("erroComodoNaoPodeSerRemovido.jsp");
	    	rd.forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
