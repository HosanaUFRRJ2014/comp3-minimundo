package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import entity.*;

/** Descricao será chave primária. Cuidado com o tratamento dessa string!!!
 * CREATE table Comodos
 * (
 * 		id SERIAL NOT NULL,
		descricao VARCHAR(255),
	--	tipoComodo INT NOT NULL, 
		primary key(descricao)
 * );
 * 
 * */

public class ComodoCompostoDAO 
{
	
	private Connection connection;

	public ComodoCompostoDAO()
	{
		connection = new ConnectionFactory().getConnection();
	}

}
