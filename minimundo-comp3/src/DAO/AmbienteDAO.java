package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entity.*;




public class AmbienteDAO 
{
	
	private Connection connection;

	public AmbienteDAO() 
	{
		connection = new ConnectionFactory().getConnection();
	}
	
	public long inserir(Ambiente ambiente)
	{
		boolean sucesso = false;
		//String sql = "INSERT INTO ambientes(numparedes, numportas, metragem, id_contrato) VALUES (" + ambiente.getNumParedes() +"," + ambiente.getNumPortas() + ","+ ambiente.getMetragem() +","+ ambiente.getIdContrato()+")";
		String sql = "INSERT INTO ambientes (numparedes, numportas, metragem, id_contrato) VALUES(?,?,?,?) returning id";
		
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);

			ps.setInt(1, ambiente.getNumParedes());
			ps.setInt(2, ambiente.getNumPortas());
			ps.setFloat(3, ambiente.getMetragem());
			ps.setLong(4, ambiente.getIdContrato());
			
			ResultSet rs = ps.executeQuery();
			
			long id = -1;
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

	//não pode ser alterado!!
	//criar um ambiente aux para continuar guardando a descrição antiga, assim, poderá executar essa query pela desc antiga
	public void alterar(Ambiente ambiente)
	{  
		String sql = "UPDATE ambientes SET numparedes=" + ambiente.getNumParedes() +", numportas="+ ambiente.getNumPortas() +", metragem="+ ambiente.getMetragem() +", id_contrato=" + ambiente.getIdContrato() + "WHERE id=" + ambiente.getId();
		//String sql = "UPDATE ambientes SET descricao=?, tipo=? WHERE descricao=" + ambiente.getDescricao() + "AND tipo=" + ambiente.getTipoAmbiente();

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
	/*	public Ambiente buscarPorID(long id)
	{
		Ambiente ambiente = null;
		String sql = "SELECT * FROM Ambientes WHERE id=" + id; //se não funcionar, usar id=?;
		PreparedStatement ps;
		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String nome = rs.getString("nome");
			float nota = rs.getFloat("nota");
			ambiente = new Ambiente(nome);
			ambiente.setId(rs.getLong("id"));
			ambiente.setNota(nota);
			//para setar o valor ONDE tem o id informado
			//		ps.setLong(3, ambiente.getId());
			ps.execute();
			ps.close();
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		return ambiente;
	}*/


	/*public LinkedList<Ambiente> buscar(String valorBuscado)
	{
		LinkedList<Ambiente> ambientes = new LinkedList<Ambiente>();
		String sql;

		//pode ser interessante buscar mais de um valor. Como numPortas, numParedes e metragem
		sql = "SELECT id, numparedes, numportas, metragem, id_contrato FROM ambientes where id=" + valorBuscado;

		System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				int numParedes = rs.getInt("numParedes");
				int numPortas = rs.getInt("numPortas");
				float metragem = rs.getFloat("metragem");
				long idContrato = rs.getLong("id_contrato");
				Ambiente ambiente = null;
				
				ambiente.setId(id);
				ambiente.setNumParedes(numParedes);
				ambiente.setNumPortas(numPortas);
				ambiente.setMetragem(metragem);
				ambiente.setIdContrato(idContrato);
				
				ambientes.add(ambiente);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return ambientes;

	}*/
	
	public Ambiente buscar(String valorBuscado)
	{
		Ambiente ambiente = new Ambiente(0,0,0);
		String sql;

		//pode ser interessante buscar mais de um valor. Como numPortas, numParedes e metragem
		sql = "SELECT id, numparedes, numportas, metragem, id_contrato FROM ambientes where id=" + valorBuscado;

		System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				int numParedes = rs.getInt("numParedes");
				int numPortas = rs.getInt("numPortas");
				float metragem = rs.getFloat("metragem");
				long idContrato = rs.getLong("id_contrato");
				
				
				ambiente.setId(id);
				ambiente.setNumParedes(numParedes);
				ambiente.setNumPortas(numPortas);
				ambiente.setMetragem(metragem);
				ambiente.setIdContrato(idContrato);
				
				return ambiente;
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return ambiente;

	}
	
	public LinkedList <Ambiente> buscarPorIdContrato(String valorBuscado)
	{
		LinkedList <Ambiente> ambientes = new LinkedList<Ambiente>();
		String sql;

		//pode ser interessante buscar mais de um valor. Como numPortas, numParedes e metragem
		sql = "SELECT id, numparedes, numportas, metragem, id_contrato FROM ambientes where id_contrato=" + valorBuscado;

		System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				int numParedes = rs.getInt("numParedes");
				int numPortas = rs.getInt("numPortas");
				float metragem = rs.getFloat("metragem");
				long idContrato = rs.getLong("id_contrato");
				Ambiente ambiente = new Ambiente(0,0,0);
				
				ambiente.setId(id);
				ambiente.setNumParedes(numParedes);
				ambiente.setNumPortas(numPortas);
				ambiente.setMetragem(metragem);
				ambiente.setIdContrato(idContrato);
				
				ambientes.add(ambiente);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return ambientes;

	}

	public LinkedList<Ambiente> getListaAmbientes()
	{
		LinkedList<Ambiente> ambientes = new LinkedList<Ambiente>();

		String sql = "SELECT * FROM Ambientes";

		PreparedStatement ps;


		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				int numParedes = rs.getInt("numParedes");
				int numPortas = rs.getInt("numPortas");
				float metragem = rs.getFloat("metragem");
				long idContrato = rs.getLong("id_contrato");
				Ambiente ambiente = new Ambiente(0,0,0);
				
				ambiente.setId(id);
				ambiente.setNumParedes(numParedes);
				ambiente.setNumPortas(numPortas);
				ambiente.setMetragem(metragem);
				ambiente.setIdContrato(idContrato);
				
				ambientes.add(ambiente);
				
			}

			rs.close();
			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return ambientes;
	}

	public void remover(Ambiente ambiente) 
	{

		try {
			//acho que tem que envolver id_contrato nessa query rsrs. Como não excuir várias coisas iguais, só que de contratos diferentes?
			PreparedStatement stmt;
			//stmt = connection.prepareStatement("DELETE FROM ambientes WHERE numParedes="+ambiente.getNumParedes() + "AND numPortas=" +ambiente.getNumPortas() +"AND metragem=" + ambiente.getMetragem());

			stmt = connection.prepareStatement("DELETE FROM ambientes WHERE id="  + ambiente.getId());
			stmt.setInt(1, ambiente.getNumParedes());
			
			stmt.setInt(2, ambiente.getNumPortas());
			
			stmt.setFloat(3, ambiente.getMetragem());

			stmt.execute();

			stmt.close();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}




}
