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
import DAO.MobiliaDAO;
import entity.Ambiente;
import entity.Comodo;
import entity.ComodoComposto;
import entity.Cozinha;
import entity.Mobilia;
import entity.Quarto;
import entity.Sala;

/**
 * Servlet implementation class CtrAltComodo
 */
@WebServlet("/CtrAltComodo")
public class CtrAltComodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrAltComodo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String descricao = request.getParameter("descricao");
		String id_string = request.getParameter("id");
		String idPai_string = request.getParameter("id_comodo");
		String [] mobAssociadas = request.getParameterValues("mobiliaAssociadas");
		
		if(descricao == "" || mobAssociadas.length == 0)
		{
			HttpSession sessao = request.getSession();
		    sessao.setAttribute("voltar", "frontAltComodo.jsp");
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
			
			comodo.setId(Long.parseLong(id_string));
			comodo.setIdPai(Long.parseLong(idPai_string));
			
			cDAO.alterar(comodo); 
			
			MobiliaDAO mobDAO = new MobiliaDAO();
			
			//associando as mobílias que vem da fronteira ao comodo informado
			for(String ma : mobAssociadas)
			{
				Mobilia mobilia = mobDAO.buscarPorId(ma).get(0);
				
				if(mobilia != null)
				{
					comodo.getMobiliaDisponivel().add(mobilia);
				}
			}
			
			long idComodo = cDAO.alterar(comodo);
			
			if(idComodo == -1)
				System.out.println("Erro na insercao do comodo linha 106");
			
			for(Mobilia m : comodo.getMobiliaDisponivel())
			{
				m.setIdComodo(idComodo);
	
				mobDAO.alterar(m);
			}
			
		
			//redirecionar para o menu inicial
		//	?
		}
		
		
		else
		{
			//redirecionar para página de erro
		//	?
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
