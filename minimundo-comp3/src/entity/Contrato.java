package entity;

import java.util.LinkedList;

public class Contrato 
{
	private long id;
	private float valorComissao; //onde esse valor é setado?
	private float valorContrato = 0;
	private int prazo;
	private LinkedList<Ambiente> ambientesAssociados = new LinkedList<Ambiente>();
	//private LinkedList<ItemVenda> itemsVendaAssociados = new LinkedList<ItemVenda>(); 
	
	public float calcularValorContrato()
	{
		valorContrato = 0;
		for(Ambiente a : ambientesAssociados)
			valorContrato += a.calcularCusto() + (1+valorComissao); 
		return valorContrato;
	}
	
	public int calcularPrazo()
	{
		int prazo = 0;
		
		for(Ambiente a : ambientesAssociados)
		{
			prazo += a.calcularTempoIntrega();
		}
		
		this.prazo = prazo;
		
		return prazo;
	}
	
	public boolean adicionarAmbiente(Ambiente a)
	{
		return ambientesAssociados.add(a);
		
	}

	public float getValorComissao() 
	{
		return valorComissao;
	}

	public void setValorComissao(float valorComissao) 
	{
		this.valorComissao = valorComissao;
	}

	public float getValorContrato()
	{
		return valorContrato;
	}

	public int getPrazo() 
	{
		return prazo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LinkedList<Ambiente> getAmbientesAssociados() {
		return ambientesAssociados;
	}

	/*public void setAmbientesAssociados(LinkedList<Ambiente> ambientesAssociados) {
		this.ambientesAssociados = ambientesAssociados;
	}*/
	
	
	
	
	
	/*public boolean associarMobilias(Mobilia m, int qtd)
	{
		ItemVenda i = new ItemVenda();
		
		i.setMobilia(m);
		i.setQuantidade(qtd);
		
		boolean b = itemsVendaAssociados.add(i);
		
		if(m != null && qtd != 0 && b == true)
			return true;
		
		return false;
		
	}*/
	

}

//controladores para: ambiente, contrato (alterar, remover, criar), cozinha, sala, quarto, comodoComposto
//um controlador para cada uma dessas coisas. Classes servlets