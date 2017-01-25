package entity;

import java.util.LinkedList;

public abstract class Comodo 
{
	private long id;
	private long idPai;
	private String descricao;
	private LinkedList<Mobilia> mobiliaDisponivel = new LinkedList<Mobilia>(); 
	private int tipoComodo;
	
	public LinkedList<Mobilia> listaMobiliaDisponivel()
	{
		
		return mobiliaDisponivel;	
	}


	public String getDescricao() 
	{
		return descricao;
	}


	public void setDescricao(String descricao) 
	{
		this.descricao = descricao;
	}
	
	


	public LinkedList<Mobilia> getMobiliaDisponivel() 
	  {
		return mobiliaDisponivel;
	}


	public int getTipoComodo() 
	{
		return tipoComodo;
	}


	public void setTipoComodo(int tipoComodo) 
	{
		this.tipoComodo = tipoComodo;
	}


	public void setMobiliaDisponivel(LinkedList<Mobilia> mobiliaDisponivel) 
	{
		this.mobiliaDisponivel = mobiliaDisponivel;
	}
	
	
	public boolean isSemMobiliaAssociada() //para completar, checar se há algum comodo composto associado a ele no momento da remocao
	{
		if(mobiliaDisponivel.size() == 0)
			return true;
		
		return false;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getIdPai() {
		return idPai;
	}


	public void setIdPai(long idPai) {
		this.idPai = idPai;
	}
	
	
	
	
	
	
	
	

}
