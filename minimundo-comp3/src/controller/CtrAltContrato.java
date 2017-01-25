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

import DAO.AmbienteDAO;
import DAO.ContratoDAO;
import entity.Ambiente;
import entity.Contrato;

/**
 * Servlet implementation class CtrAltContrato
 */
@WebServlet("/CtrAltContrato")
public class CtrAltContrato extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrAltContrato() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String comissao = request.getParameter("valorComissao");
		//	String vContrato = request.getParameter("valorContrato");
			//String pz = request.getParameter("prazo");
			
			//pegar todos os valores da combobox
		//	String [] ambientesAssociados = request.getParameterValues("ambAssociados");
			
			if(comissao  == null /*|| vContrato  == null || pz == null*/)// ?
			{
				
					HttpSession sessao = request.getSession();
				    sessao.setAttribute("voltar", "frontAltContrato.jsp");
					//erro
					//parar aqui, pq redireciona para pag de erro
					RequestDispatcher rd = request.getRequestDispatcher("erroCampoInvalido.jsp");
			    	rd.forward(request, response);
				
			}
			
			float valorComissao = Float.parseFloat(comissao);
			//float valorContrato = Float.parseFloat(vContrato);
			//int prazo = Integer.parseInt(pz);
			
		//	AmbienteDAO ambienteDAO = new AmbienteDAO();
			//LinkedList<Ambiente> ambientes = new LinkedList<Ambiente>();
			
			Contrato contrato = new Contrato();
			contrato.setValorComissao(valorComissao);
			ContratoDAO contratoDAO = new ContratoDAO();
			
			
		/*	for(String s : ambientesAssociados)
			{
				Ambiente ambiente = ambienteDAO.buscar(s);
				
				if(ambiente != null)
				{
					contrato.adicionarAmbiente(ambiente);
					
				}
				
			} */
			
			//calculado pelo sistema. Pode ser legal mostrar antes de redirecionar para a página inicial
			contrato.calcularValorContrato();
			contrato.calcularPrazo();
		
			long idContrato = contratoDAO.inserir(contrato);
			
			contrato.setId(idContrato);
			
			if(idContrato == -1)
				System.out.println("Erro na insercao do contrato linha 78");
			
			
			RequestDispatcher rd = null;
		    
		    HttpSession sessao = request.getSession();
		    sessao.setAttribute("contrato", contrato);
		    
			    	
		    	rd = request.getRequestDispatcher("frontCriarAmbiente.jsp");
		    	rd.forward(request, response);
	

			
		/*	for(Ambiente a : contrato.getAmbientesAssociados())
			{
				a.setIdContrato(idContrato);
				//falta associar cada mobilia do de cada ambiente a item venda
			//	?
				ambienteDAO.alterar(a);
			}*/
			
			
			
			/*Ambiente ambiente = new Ambiente(valorComissao,valorContrato,prazo);
			
			AmbienteDAO ambienteDAO = new AmbienteDAO();
			
			ambienteDAO.inserir(ambiente);*/
			
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
