package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entity.*;


//http://pgdocptbr.sourceforge.net/pg82/tutorial-fk.html
//testar se posso alterar a chave primaria, já que ela não é auto gerada 
	//se posso, colocar um novo parametro "novaDescricao" no método alterar
	//se não, alterar apenas o tipo , ou gerar a chave automaticamente


public class ComodoDAO 
{
	private Connection connection;
	private LinkedList<Comodo> comodos;

	public ComodoDAO()
	{
		connection = new ConnectionFactory().getConnection();
	}

	public boolean inserir(Comodo comodo)
	{
		boolean sucesso = false;
		//String sql = "INSERT INTO Comodos (descricao,tipoComodo) values(?,?)";
	//	String sql = "INSERT INTO comodos(descricao, tipo, id_comodo) VALUES ("+ comodo.getDescricao() + "," + comodo.getTipoComodo() +"," + comodo.getIdPai() + ")";
		String sql = "INSERT INTO comodos(descricao, tipo, id_comodo) VALUES(?,?,?)";
		
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);

			ps.setString(1, comodo.getDescricao());
			ps.setInt(2, comodo.getTipoComodo());
			//descobrir o id do pai, se houver algum, para adicionar no filho
			ps.setLong(3, comodo.getIdPai());

			ps.execute();
			ps.close();

			sucesso = true;
		}

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return sucesso;
	}

	//criar um comodo aux para continuar guardando a descrição antiga, assim, poderá executar essa query pela desc antiga
	public long alterar(Comodo comodo)
	{
		long id = -1;
		//String sql = "UPDATE Comodos SET nota="+ comodo.getNota()+ "WHERE id="+ comodo.getId();
		String sql = "UPDATE comodos SET descricao= \'" + comodo.getDescricao() +"\' , tipo=" + comodo.getTipoComodo() +", id_comodo=" + comodo.getIdPai() + " WHERE id=" + comodo.getId() + "returning id";

		//System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);

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

	//para o Lancar Nota(Opcional),na busca por número (em andamento)
	/*	public Comodo buscarPorID(long id)
	{
		Comodo comodo = null;
		String sql = "SELECT * FROM Comodos WHERE id=" + id; //se não funcionar, usar id=?;
		PreparedStatement ps;
		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String nome = rs.getString("nome");
			float nota = rs.getFloat("nota");
			comodo = new Comodo(nome);
			comodo.setId(rs.getLong("id"));
			comodo.setNota(nota);
			//para setar o valor ONDE tem o id informado
			//		ps.setLong(3, comodo.getId());
			ps.execute();
			ps.close();
		} 
		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		return comodo;
	}*/


	public LinkedList<Comodo> buscar(String valorBuscado)
	{
		LinkedList<Comodo> comodos = new LinkedList<Comodo>();
		String sql;

		sql = "SELECT * FROM Comodos WHERE descricao = "+ "\'" +valorBuscado + "\'";


	//	System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				String descricao = rs.getString("descricao");
				int tipoComodo = rs.getInt("tipo");
				long id_pai = rs.getLong("id_comodo");
				Comodo comodo = null;
				
				switch(tipoComodo)
				{
					case 1:
						comodo = new Cozinha();
						break;
						
					case 2:
						comodo = new Sala();
						break;
						
					case 3:
						comodo = new Quarto();
						break;
					
					case 4:
						comodo = new ComodoComposto();
						break;	
						
					default:
						System.out.println("Tipo inválido");
						//throw alguma exception
						break;
				}
				
				comodo.setId(id);
				comodo.setDescricao(descricao);
				comodo.setTipoComodo(tipoComodo);
				comodo.setIdPai(id_pai);
				

				comodos.add(comodo);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return comodos;

	}
	
	public LinkedList<Comodo> buscarPorId(String valorBuscado)
	{
		LinkedList<Comodo> comodos = new LinkedList<Comodo>();
		String sql;

		sql = "SELECT * FROM Comodos WHERE id = "+ valorBuscado;


	//	System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				String descricao = rs.getString("descricao");
				int tipoComodo = rs.getInt("tipo");
				long id_pai = rs.getLong("id_comodo");
				Comodo comodo = null;
				
				switch(tipoComodo)
				{
					case 1:
						comodo = new Cozinha();
						break;
						
					case 2:
						comodo = new Sala();
						break;
						
					case 3:
						comodo = new Quarto();
						break;
					
					case 4:
						comodo = new ComodoComposto();
						break;	
						
					default:
						System.out.println("Tipo inválido");
						//throw alguma exception
						break;
				}
				
				comodo.setId(id);
				comodo.setDescricao(descricao);
				comodo.setTipoComodo(tipoComodo);
				comodo.setIdPai(id_pai);
				

				comodos.add(comodo);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return comodos;

	}

	public LinkedList<Comodo> getListaComodos()
	{
		comodos = new LinkedList<Comodo>();

		String sql = "SELECT * FROM Comodos";

		PreparedStatement ps;


		try 
		{
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				String descricao = rs.getString("descricao");
				int tipoComodo = rs.getInt("tipo");
				long id_pai = rs.getLong("id_comodo");
				Comodo comodo = null;
				
				switch(tipoComodo)
				{
					case 1:
						comodo = new Cozinha();
						break;
						
					case 2:
						comodo = new Sala();
						break;
						
					case 3:
						comodo = new Quarto();
						break;
					
					case 4:
						comodo = new ComodoComposto();
						break;	
						
					default:
						System.out.println("Tipo inválido");
						//throw alguma exception
						break;
				}
				
				comodo.setId(id);
				comodo.setDescricao(descricao);
				comodo.setTipoComodo(tipoComodo);
				comodo.setIdPai(id_pai);

				comodos.add(comodo);
				
			}

			rs.close();
			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}

		return comodos;
	}
	
	public boolean isRemovivel(Comodo comodoARemover)
	{
		LinkedList<Comodo> comodos = new LinkedList<Comodo>();
		LinkedList<Mobilia> mobilias = new LinkedList<Mobilia>();
		String sql1, sql2;

		sql1 = "SELECT * FROM Comodos WHERE id_comodo = "+ comodoARemover.getId();
		sql2 = "SELECT * FROM Mobilias WHERE id_comodo = "+ comodoARemover.getId();

	//	System.out.println(sql);
		PreparedStatement ps;

		try 
		{
			ps = connection.prepareStatement(sql1);
			ResultSet rs = ps.executeQuery();

			while(rs.next() == true)
			{
				long id = rs.getLong("id");
				String descricao = rs.getString("descricao");
				int tipoComodo = rs.getInt("tipo");
				long id_pai = rs.getLong("id_comodo");
				Comodo comodo = null;
				
				switch(tipoComodo)
				{
					case 1:
						comodo = new Cozinha();
						break;
						
					case 2:
						comodo = new Sala();
						break;
						
					case 3:
						comodo = new Quarto();
						break;
					
					case 4:
						comodo = new ComodoComposto();
						break;	
						
					default:
						System.out.println("Tipo inválido");
						//throw alguma exception
						break;
				}
				
				comodo.setId(id);
				comodo.setDescricao(descricao);
				comodo.setTipoComodo(tipoComodo);
				comodo.setIdPai(id_pai);
				

				comodos.add(comodo);
			}

			ps.close();
		} 

		catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		
		try 
		{
			ps = connection.prepareStatement(sql2);
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
		
		if(comodos.size() == 0 && mobilias.size() == 0)
			return true;
		
		return false;
		
		

	}

	public void remover(Comodo comodo) 
	{

		try {

			PreparedStatement stmt = connection.prepareStatement("DELETE FROM comodos WHERE id="+comodo.getId());

			stmt.setLong(0, comodo.getId());

			stmt.execute();

			stmt.close();

		} catch (SQLException e) {

			throw new RuntimeException(e);

		}

	}

}
