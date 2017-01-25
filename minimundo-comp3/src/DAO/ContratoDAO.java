package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entity.*;
public class ContratoDAO 
{
	
	private Connection connection;
	private LinkedList<Contrato> contratos;

	public ContratoDAO()
	{
		connection = new ConnectionFactory().getConnection();
	}
	
	public long inserir(Contrato contrato)
	{
		long id = -1;
		String sql = "INSERT INTO contratos(comissao, valorcontrato, prazo) VALUES (?, ?, ?) returning id";
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);

			ps.setFloat(1, contrato.getValorComissao());
			ps.setFloat(2, contrato.calcularValorContrato());
			ps.setInt(3, contrato.calcularPrazo());
			
			
		//	ps.execute();
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				id = rs.getLong("id");
		
			ps.close();
			
			return id;
			
		}

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		
	}

	//criar um contrato aux para continuar guardando a descrição antiga, assim, poderá executar essa query pela desc antiga
	public void alterar(Contrato contrato)
	{  
		String sql = "UPDATE contratos SET comissao=" +contrato.getValorComissao() + ", valorcontrato=" +contrato.getValorContrato() + ", prazo=" + contrato.getPrazo() +  " WHERE id=" + contrato.getId();
		//String sql = "UPDATE contratos SET descricao=?, tipo=? WHERE descricao=" + contrato.getDescricao() + "AND tipo=" + contrato.getTipoContrato();

		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);

			ps.execute();
			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

	}

	//para o Lancar Nota(Opcional),na busca por número (em andamento)
	/*	public Contrato buscarPorID(long id)
	{
		Contrato contrato = null;
		String sql = "SELECT * FROM Contratos WHERE id=" + id; //se não funcionar, usar id=?;
		PreparedStatement ps;
		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String nome = rs.getString("nome");
			float nota = rs.getFloat("nota");
			contrato = new Contrato(nome);
			contrato.setId(rs.getLong("id"));
			contrato.setNota(nota);
			//para setar o valor ONDE tem o id informado
			//		ps.setLong(3, contrato.getId());
			ps.execute();
			ps.close();
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		return contrato;
	}*/


	public LinkedList<Contrato> buscar(String valorBuscado)
	{
		LinkedList<Contrato> contratos = new LinkedList<Contrato>();
		String sql;

		//sql = "SELECT * FROM Contratos WHERE descricao = "+ valorBuscado;
		sql = "SELECT id, comissao, valorcontrato, prazo FROM contratos where id=" + valorBuscado;

		System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				float comissao = rs.getFloat("comissao");
				float valorContrato = rs.getFloat("valorContrato");
				int prazo = rs.getInt("prazo");
				Contrato contrato = new Contrato();
				
				contrato.setId(id);
				contrato.setValorComissao(comissao);
				contrato.calcularValorContrato();
				contrato.calcularPrazo();
			//	contrato.setValorContrato(valorContrato);
			//	contrato.setPrazo(prazo);
				

				contratos.add(contrato);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return contratos;

	}

	public LinkedList<Contrato> getListaContratos()
	{
		contratos = new LinkedList<Contrato>();

		String sql = "SELECT * FROM Contratos";

		PreparedStatement ps;


		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				float comissao = rs.getFloat("comissao");
				float valorContrato = rs.getFloat("valorContrato");
				int prazo = rs.getInt("prazo");
				Contrato contrato = new Contrato();
				
				contrato.setId(id);
				contrato.setValorComissao(comissao);
				contrato.calcularValorContrato();
				contrato.calcularPrazo();
			//	contrato.setValorContrato(valorContrato);
		//		contrato.setPrazo(prazo);

				contratos.add(contrato);
				
			}

			rs.close();
			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return contratos;
	}

	/*public void remover(Contrato contrato) 
	{

		try {

			PreparedStatement stmt = connection.prepareStatement("DELETE FROM contratos WHERE id="+contrato.getValorContrato());

			stmt.setFloat(1, contrato.getValorContrato());

			stmt.execute();

			stmt.close();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}
*/

}
