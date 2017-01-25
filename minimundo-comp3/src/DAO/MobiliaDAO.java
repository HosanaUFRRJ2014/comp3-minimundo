package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entity.*;
public class MobiliaDAO 
{
	private Connection connection;

	public MobiliaDAO()
	{
		connection = new ConnectionFactory().getConnection();
	}

	public long inserir(Mobilia mobilia)
	{
		long id = -1;
		//String sql = "INSERT INTO mobilias(descricao, custoproducao, tempoentrega, id_itemvenda) VALUES (" + mobilia.getDescricao() +"," + mobilia.getCustoProducao() + ","+ mobilia.getTempoEntrega() +","+ mobilia.getIdItemVenda()+")";
		String sql = "INSERT INTO mobilias(descricao, custoproducao, tempoentrega, id_itemvenda) VALUES (?,?,?,?) returning id";

		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);

			ps.setString(1, mobilia.getDescricao());
			ps.setFloat(2, mobilia.getCustoProducao());
			ps.setInt(3, mobilia.getTempoEntrega());
			ps.setLong(4, mobilia.getIdItemVenda());

            ResultSet rs = ps.executeQuery();
            
          //  System.out.println(sql);
			
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

	//criar um mobilia aux para continuar guardando a descrição antiga, assim, poderá executar essa query pela desc antiga
	public void alterar(Mobilia mobilia)
	{  
		String sql = "UPDATE mobilias SET descricao= \'" + mobilia.getDescricao() +"\' , custoproducao="+ mobilia.getCustoProducao() +", tempoentrega="+ mobilia.getTempoEntrega() +", id_itemvenda=" + mobilia.getIdItemVenda() +", id_comodo="+mobilia.getIdComodo()+ " WHERE id=" + mobilia.getId();
		//String sql = "UPDATE mobilias SET descricao=?, tipo=? WHERE descricao=" + mobilia.getDescricao() + "AND tipo=" + mobilia.getTipoMobilia();

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

	/*public void associarComodos(Mobilia mobilia, int tipoComodo)
	{
		//String sql = "INSERT INTO mobilias(descricao, custoproducao, tempoentrega, id_itemvenda) VALUES (" + mobilia.getDescricao() +"," + mobilia.getCustoProducao() + ","+ mobilia.getTempoEntrega() +","+ mobilia.getIdItemVenda()+")";
		String sql = "INSERT INTO tipocommob(id_mobilia, tipo_comodo) VALUES (?,?)";

		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);

			ps.setLong(0, mobilia.getId());
			ps.setFloat(1, tipoComodo);
			

			ps.execute();
			ps.close();

			
		}

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
	}*/

	//para o Lancar Nota(Opcional),na busca por número (em andamento)
	/*	public Mobilia buscarPorID(long id)
	{
		Mobilia mobilia = null;
		String sql = "SELECT * FROM Mobilias WHERE id=" + id; //se não funcionar, usar id=?;
		PreparedStatement ps;
		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String nome = rs.getString("nome");
			float nota = rs.getFloat("nota");
			mobilia = new Mobilia(nome);
			mobilia.setId(rs.getLong("id"));
			mobilia.setNota(nota);
			//para setar o valor ONDE tem o id informado
			//		ps.setLong(3, mobilia.getId());
			ps.execute();
			ps.close();
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		return mobilia;
	}*/

	public LinkedList<Mobilia> getMobiliasnaoItemVenda()
	{
		LinkedList<Mobilia> mobilias = new LinkedList<Mobilia>();
		String sql;

		//pode ser interessante buscar mais de um valor. Como numPortas, numParedes e metragem
		sql = "SELECT id, descricao, custoproducao, tempoentrega, id_itemvenda FROM mobilias where id_itemVenda > 0";

		//System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				String descricao = rs.getString("descricao");
				float custoproducao = rs.getFloat("custoproducao");
				int tempoentrega = rs.getInt("tempoentrega");
				long idItemVenda = rs.getLong("id_itemVenda");
				long idComodo = rs.getLong("id_comodo");
				Mobilia mobilia = new Mobilia();

				mobilia.setId(id);
				mobilia.setDescricao(descricao);
				mobilia.setCustoProducao(custoproducao);
				mobilia.setTempoEntrega(tempoentrega);
				mobilia.setIdItemVenda(idItemVenda);
				mobilia.setIdComodo(idComodo);

				mobilias.add(mobilia);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return mobilias;

	}

	public LinkedList<Mobilia> buscar(String valorBuscado)
	{
		LinkedList<Mobilia> mobilias = new LinkedList<Mobilia>();
		String sql;

		//pode ser interessante buscar mais de um valor. Como numPortas, numParedes e metragem
		sql = "SELECT id, descricao, custoproducao, tempoentrega, id_itemvenda FROM mobilias where descricao=" + "\'" +valorBuscado + "\'";

		//System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				String descricao = rs.getString("descricao");
				float custoproducao = rs.getFloat("custoproducao");
				int tempoentrega = rs.getInt("tempoentrega");
				long idItemVenda = rs.getLong("id_itemVenda");
				long idComodo = rs.getLong("id_comodo");
				Mobilia mobilia = new Mobilia();

				mobilia.setId(id);
				mobilia.setDescricao(descricao);
				mobilia.setCustoProducao(custoproducao);
				mobilia.setTempoEntrega(tempoentrega);
				mobilia.setIdItemVenda(idItemVenda);
				mobilia.setIdComodo(idComodo);

				mobilias.add(mobilia);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return mobilias;

	}
	
	public LinkedList<Mobilia> buscarPorId(String valorBuscado)
	{
		LinkedList<Mobilia> mobilias = new LinkedList<Mobilia>();
		String sql;

		//pode ser interessante buscar mais de um valor. Como numPortas, numParedes e metragem
		sql = "SELECT id, descricao, custoproducao, tempoentrega, id_itemvenda FROM mobilias where id=" +valorBuscado;

		//System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				String descricao = rs.getString("descricao");
				float custoproducao = rs.getFloat("custoproducao");
				int tempoentrega = rs.getInt("tempoentrega");
				long idItemVenda = rs.getLong("id_itemVenda");
				//long idComodo = rs.getLong("id_comodo");
				Mobilia mobilia = new Mobilia();

				mobilia.setId(id);
				mobilia.setDescricao(descricao);
				mobilia.setCustoProducao(custoproducao);
				mobilia.setTempoEntrega(tempoentrega);
				mobilia.setIdItemVenda(idItemVenda);
			//	mobilia.setIdComodo(idComodo);

				mobilias.add(mobilia);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return mobilias;

	}

	public LinkedList<Comodo> buscarRetonaComodos(String valorBuscado)
	{
		LinkedList<Mobilia> mobilias = new LinkedList<Mobilia>();
		String sql;

		LinkedList<Long> idsComodo = new LinkedList<Long>();
		LinkedList<Integer> tiposComodo = new LinkedList<Integer>();
		LinkedList<Comodo> comodos = new LinkedList<Comodo>();

		sql = "SELECT *  FROM commob where id_mobilia=" + valorBuscado ;


		//System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long idComodo = rs.getLong("id_comodo");
				long idMobilia = rs.getLong("id_mobilia");



				idsComodo.add(idComodo);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		int i = 0;
		while(i < idsComodo.size())
		{
			ComodoDAO cDAO = new ComodoDAO();

			comodos.addAll(cDAO.buscarPorId(Long.toString(idsComodo.get(i))));


			i++;
		}


		return comodos;

	}


	public LinkedList<Mobilia> getListaMobilias()
	{
		LinkedList<Mobilia> mobilias = new LinkedList<Mobilia>();

		String sql = "SELECT * FROM Mobilias";

		PreparedStatement ps;


		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				String descricao = rs.getString("descricao");
				float custoproducao = rs.getFloat("custoproducao");
				int tempoentrega = rs.getInt("tempoentrega");
				long idItemVenda = rs.getLong("id_itemVenda");
				long idComodo = rs.getLong("id_comodo");
				Mobilia mobilia = new Mobilia();

				mobilia.setId(id);
				mobilia.setDescricao(descricao);
				mobilia.setCustoProducao(custoproducao);
				mobilia.setTempoEntrega(tempoentrega);
				mobilia.setIdItemVenda(idItemVenda);
				mobilia.setIdComodo(idComodo);

				mobilias.add(mobilia);

			}

			rs.close();
			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return mobilias;
	}

	public void remover(Mobilia mobilia) 
	{

		try {

			PreparedStatement stmt = connection.prepareStatement("DELETE FROM mobilias WHERE descricao="+mobilia.getDescricao() + "AND custoproducao=" +mobilia.getCustoProducao() +"AND tempoEntrega=" + mobilia.getTempoEntrega() + "AND id_itemVenda=" + mobilia.getIdItemVenda());

			stmt.setString(1, mobilia.getDescricao());

			stmt.setFloat(2, mobilia.getCustoProducao());

			stmt.setInt(3, mobilia.getTempoEntrega());

			stmt.setLong(4, mobilia.getIdItemVenda());

			stmt.execute();

			stmt.close();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}


}
