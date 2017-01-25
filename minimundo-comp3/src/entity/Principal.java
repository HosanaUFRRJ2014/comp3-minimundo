package entity;

import java.sql.Connection;

import DAO.ConnectionFactory;

public class Principal {

	public Principal() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = new ConnectionFactory().getConnection();

	}

}
