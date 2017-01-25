package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.MobiliaDAO;
import DAO.TipoComMobDAO;
import entity.Mobilia;
import entity.Comodo;
import entity.ComodoComposto;
import entity.Cozinha;
import entity.Quarto;
import entity.Sala;

/**
 * Servlet implementation class CtrCriarMobilia
 */
@WebServlet("/CtrCriarMobilia")
public class CtrCriarMobilia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrCriarMobilia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String descricao = request.getParameter("descricao");
		String c = request.getParameter("custo");
		String te = request.getParameter("tempoEntrega");
		String [] comodAssoc = request.getParameterValues("associados");
		
		if(descricao  == null || c  == null || te == null) //?
		{
		
				HttpSession sessao = request.getSession();
			    sessao.setAttribute("voltar", "frontCriarMobilia.jsp");
				//erro
				//parar aqui, pq redireciona para pag de erro
				RequestDispatcher rd = request.getRequestDispatcher("erroCampoInvalido.jsp");
		    	rd.forward(request, response);
			
		}
		
		int tempoEntrega = Integer.parseInt(te);
		float custo = Float.parseFloat(c);
		
		Mobilia mobilia = new Mobilia();
		
		mobilia.setDescricao(descricao);
		mobilia.setCustoProducao(custo);
		mobilia.setTempoEntrega(tempoEntrega);
		
        
		//associando os tipos de comodo
	    for(String tipo : comodAssoc)
	    {
	    	mobilia.associarTipoComodo(tipo);
	    	
	    }
		
		
	    MobiliaDAO mobiliaDAO = new MobiliaDAO();

		long id = mobiliaDAO.inserir(mobilia);
		
	//	System.out.println(id);
		
		mobilia.setId(id);
		
		TipoComMobDAO tcmDAO = new TipoComMobDAO();
		
		for(int i : mobilia.getTiposComodos())
		{
			
			tcmDAO.criarAssociacao(mobilia,i);
		}
		
		
		//redirecionar para algum lugar. Acho que a página inicial
	//	?
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
