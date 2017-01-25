package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.AmbienteDAO;
import DAO.ItemVendaDAO;
import DAO.MobiliaDAO;
import entity.Ambiente;
import entity.ItemVenda;
import entity.Mobilia;

/**
 * Servlet implementation class AmbienteServlet
 */
@WebServlet("/CtrCriarAmbiente")
public class CtrCriarAmbiente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrCriarAmbiente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String idCont = request.getParameter("idContrato");
		String paredes = request.getParameter("numParedes");
		String portas = request.getParameter("numPortas");
		String met = request.getParameter("metragem");
		
		String[] mAssoc = request.getParameterValues("mobAssociar");
		String[] qtd = request.getParameterValues("quantidade");
		
		if(paredes  == null || portas  == null || met == null) //?
		{
			//redirecionar para página de erro de campo vazio
			//obs: teria como checar o parseint e float. Ideia: usar try catch
		}
		
		long idContrato = Long.parseLong(idCont);
		int numParedes = Integer.parseInt(paredes);
		int numPortas = Integer.parseInt(portas);
		float metragem = Float.parseFloat(met);
		
		Ambiente ambiente = new Ambiente(numParedes,numPortas,metragem);
		ambiente.setIdContrato(idContrato);
		
		//criar item venda para as mobilias em questao
		MobiliaDAO mobDAO = new MobiliaDAO();
		
		//associando as mobílias que vem da fronteira ao comodo informado
		for(int i = 0; i < mAssoc.length; i++)
		{
			Mobilia mobilia = mobDAO.buscarPorId(String.valueOf(mAssoc[i])).get(0);
			
			if(mobilia != null && qtd != null)
			{
				/*ItemVenda it = new ItemVenda();
				it.setMobilia(mobilia);
				it.setQuantidade(Integer.parseInt(qtd[i]));*/
				
				ambiente.associarMobilias(mobilia, Integer.parseInt(qtd[i]));
			}
		}
		AmbienteDAO ambienteDAO = new AmbienteDAO();
		long idAmbiente = ambienteDAO.inserir(ambiente);
		
		ambiente.setId(idAmbiente);
		
		//criar e alterar item venda
		//criar e alterar mobilias
		ItemVendaDAO itDAO = new ItemVendaDAO();
	//	MobiliaDAO mobDAO = new MObiliaDAO();
		int i = 0;
		for(Mobilia m : ambiente.getMobiliasAssociadas())
		{
			ItemVenda it = new ItemVenda();
			it.setMobilia(m);
			it.setQuantidade(Integer.parseInt(qtd[i]));
			it.setIdAmbiente(idAmbiente);
			long idItemVenda = itDAO.inserir(it);
			
			it.setId(idItemVenda);
			
			m.setIdItemVenda(idItemVenda);
			mobDAO.alterar(m);
			
			i++;
		}
		
		
		
		
	    ambienteDAO.alterar(ambiente);
		
		//redirecionar para algum lugar. Acho que a página inicial
		//?
		RequestDispatcher rd = null;
		rd = request.getRequestDispatcher("/frontAltContrato.jsp");
    	rd.forward(request, response);
		
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
