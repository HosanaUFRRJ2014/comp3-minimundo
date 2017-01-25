package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entity.ItemVenda;
import entity.Comodo;

public class ItemVendaDAO 
{
	private Connection connection;
	private LinkedList<Comodo> comodos;

	public ItemVendaDAO() 
	{
		connection = new ConnectionFactory().getConnection();
	}
	
	public long inserir(ItemVenda itemVenda)
	{
		long id = -1;
		
		//Pode ser interessante fazer um join, ao inves de salvara mais uma variável aqui.Ou outro comando sql deve ajudar
		//String sql = "INSERT INTO itensVenda(quantidade) VALUES ("+ itemVenda.getQuantidade() + ")";
		String sql = "INSERT INTO itensVenda(quantidade,id_ambiente) VALUES (?,?)";
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);

			ps.setInt(1, itemVenda.getQuantidade());
			ps.setLong(2, itemVenda.getIdAmbiente());
		
			
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

	//nunca pode ser alterado!!!
	/*public void alterar(ItemVenda itemVenda)
	{  //fazer algo para pegar o id de ambiente
		String sql = "UPDATE itensVenda SET quantidade="+ itemVenda.getQuantidade() + "WHERE id=" + itemVenda.getId();
		//String sql = "UPDATE itemVendas SET descricao=?, tipo=? WHERE descricao=" + itemVenda.getDescricao() + "AND tipo=" + itemVenda.getTipoItemVenda();

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

	}*/

	
	/*	public ItemVenda buscarPorID(long id)
	{
		ItemVenda itemVenda = null;
		String sql = "SELECT * FROM ItemVendas WHERE id=" + id; //se não funcionar, usar id=?;
		PreparedStatement ps;
		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String nome = rs.getString("nome");
			float nota = rs.getFloat("nota");
			itemVenda = new ItemVenda(nome);
			itemVenda.setId(rs.getLong("id"));
			itemVenda.setNota(nota);
			//para setar o valor ONDE tem o id informado
			//		ps.setLong(3, itemVenda.getId());
			ps.execute();
			ps.close();
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		return itemVenda;
	}*/


	public LinkedList<ItemVenda> buscar(String valorBuscado)
	{
		LinkedList<ItemVenda> itemVendas = new LinkedList<ItemVenda>();
		String sql;

		//pode ser interessante buscar mais de um valor.
		sql = "SELECT id, quantidade FROM itensVenda where quantidade=" + valorBuscado;

		System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				int quantidade = rs.getInt("quantidade");
				ItemVenda itemVenda = null;
				
				itemVenda.setId(id);
				itemVenda.setQuantidade(quantidade);
				
				
				itemVendas.add(itemVenda);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return itemVendas;

	}

	public LinkedList<ItemVenda> getListaItemVendas()
	{
		LinkedList<ItemVenda> itemVendas = new LinkedList<ItemVenda>();

		String sql = "SELECT * FROM itensVenda";

		PreparedStatement ps;


		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{

				long id = rs.getLong("id");
				int quantidade = rs.getInt("quantidade");
				
				ItemVenda itemVenda = null;
				
				itemVenda.setId(id);
				itemVenda.setQuantidade(quantidade);
				
			
				
				itemVendas.add(itemVenda);
				
			}

			rs.close();
			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return itemVendas;
	}

	public void remover(ItemVenda itemVenda) 
	{

		try {
			//acho que tem que envolver id_contrato nessa query rsrs. Como não excuir várias coisas iguais, só que de contratos diferentes?
			PreparedStatement stmt = connection.prepareStatement("DELETE FROM itensVenda WHERE id="+itemVenda.getId());

			stmt.setLong(0, itemVenda.getId());
			

			
			stmt.execute();

			stmt.close();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}


}
