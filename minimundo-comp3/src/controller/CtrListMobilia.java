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

import DAO.MobiliaDAO;
import entity.Mobilia;

/**
 * Servlet implementation class CtrListMobilia
 */
@WebServlet("/CtrListMobilia")
public class CtrListMobilia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrListMobilia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("mobiliaAcao");
		MobiliaDAO mobiliaDAO = new MobiliaDAO();
	    LinkedList <Mobilia> mobilias = mobiliaDAO.buscar(id);
	    
	    RequestDispatcher rd = null;
	    
	    HttpSession sessao = request.getSession();
	    sessao.setAttribute("mobilia", mobilias.get(0));
	    
	    if(mobilias.get(0).getIdItemVenda() == 0)
	    {
	    	String botao = request.getParameter("botaoMob");
	    	if(botao.equals("Alterar"))	
	    		rd = request.getRequestDispatcher("frontAltMobilia.jsp");
	    	
	    	if(botao.equals("Excluir"))
	    		rd = request.getRequestDispatcher("CtrRemMobilia");
	    	
	    	rd.forward(request, response);
	    }
	    
	    else
	    {
	    	
				sessao = request.getSession();
			    sessao.setAttribute("voltar", "frontListMobilia.jsp");
				//erro
				//parar aqui, pq redireciona para pag de erro
				rd = request.getRequestDispatcher("erroMobiliaEhItemVenda.jsp");
		    	rd.forward(request, response);
			
	    }	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
