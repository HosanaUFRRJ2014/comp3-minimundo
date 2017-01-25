package entity;

public class ItemVenda 
{
	private long id;
	private int quantidade;
	private long idAmbiente;
	private Mobilia mobilia;
	
	public int getQuantidade() 
	{
		return quantidade;
	}
	public void setQuantidade(int quantidade) 
	{
		this.quantidade = quantidade;
	}
	public Mobilia getMobilia() 
	{
		return mobilia;
	}
	public void setMobilia(Mobilia mobilia) 
	{
		this.mobilia = mobilia;
	}
	public long getId()
	{
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}
	
	public long getIdAmbiente() 
	{
		return idAmbiente;
	}
	public void setIdAmbiente(long idAmbiente) 
	{
		this.idAmbiente = idAmbiente;
	}
	
	
	
	

}
