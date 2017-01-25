package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entity.Mobilia;

public class TipoComMobDAO 
{

	private Connection connection;

	public TipoComMobDAO() 
	{
		connection = new ConnectionFactory().getConnection();
	}
	
	public void criarAssociacao(Mobilia mobilia, long tipoComodo)
	{
		//String sql = "INSERT INTO mobilias(descricao, custoproducao, tempoentrega, id_itemvenda) VALUES (" + mobilia.getDescricao() +"," + mobilia.getCustoProducao() + ","+ mobilia.getTempoEntrega() +","+ mobilia.getIdItemVenda()+")";
		String sql = "INSERT INTO tipocommob(id_mobilia, tipo_comodo) VALUES (?,?)";

		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);

			ps.setLong(1, mobilia.getId());
			ps.setLong(2, tipoComodo);
			
			
			ps.execute();
			ps.close();

			
		}

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	public LinkedList<Long> buscarPorTipoComodo(String valorBuscado)
	{
		LinkedList<Long> idsMobilias = new LinkedList<Long>();
		String sql;

		//pode ser interessante buscar mais de um valor. Como numPortas, numParedes e metragem
		sql = "SELECT * FROM tipocommob where tipo_comodo=" + valorBuscado;

		//System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long idMobilia = rs.getLong("id_mobilia");
				
				idsMobilias.add(idMobilia);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return idsMobilias;

	}
	
	//falta um alterar e um remover associacao

}
