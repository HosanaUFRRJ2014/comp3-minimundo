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

import DAO.ContratoDAO;
import entity.Contrato;
/**
 * Servlet implementation class CtrAltContrato
 */
@WebServlet("/CtrListContrato")
public class CtrListContrato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrListContrato() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String id = request.getParameter("contratoAcao");
		ContratoDAO contratoDAO = new ContratoDAO();
	    LinkedList <Contrato> contratos = contratoDAO.buscar(id);
	    
	    RequestDispatcher rd = null;
	    
	    HttpSession sessao = request.getSession();
	    sessao.setAttribute("contrato", contratos.get(0));
	    
	    if(contratos.size() > 0)
	    {
	    	
	    	rd = request.getRequestDispatcher("/frontAltContrato");
	    	rd.forward(request, response);
	    }
	    
	    else
	    {
	    	
				sessao = request.getSession();
			    sessao.setAttribute("voltar", "index.jsp");
				//erro
				//parar aqui, pq redireciona para pag de erro
				rd = request.getRequestDispatcher("erroCampoInvalido.jsp");
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
