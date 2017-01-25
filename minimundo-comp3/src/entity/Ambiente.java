package entity;

import java.util.LinkedList;

public class Ambiente 
{
	private long id;
	private long idContrato;
	private int numParedes;
	private int numPortas;
	private float metragem;
	
	private LinkedList<Mobilia> mobiliasAssociadas; 

	
	
	public Ambiente(int npa, int npo, float m)
	{
		numParedes = npa;
		numPortas = npo;
		metragem = m;
		mobiliasAssociadas = new LinkedList<Mobilia>();
	}
	
	public boolean associarMobilias(Mobilia m, int qtd)
	{
		ItemVenda i = new ItemVenda();
		
		i.setMobilia(m);
		i.setQuantidade(qtd);
		
	//	boolean b = itemsVendaAssociados.add(i);
		
		//acho que faz mais sentido assim!
		m.associarItemVenda(i);
		
		if(m != null && qtd > 0)
			return true;
		
		return false;
		
	}
	
	
	
	public LinkedList<Mobilia> getMobiliasAssociadas() {
		return mobiliasAssociadas;
	}


	public float calcularCusto()
	{
		float soma = 0;
		
		for(Mobilia i : mobiliasAssociadas)
		{
			soma += i.getCustoProducao();
		}
		
		soma += 10*numParedes + 5*numPortas +2.5*metragem;
		
		return soma;
		
	}

	public int calcularTempoIntrega()
	{
		int maior = 0;
		for(Mobilia i : mobiliasAssociadas)
		{
			if(i.getTempoEntrega() > maior)
				maior = i.getTempoEntrega();
		}
		
		return maior + 2*numParedes + numPortas/2;
	}

	public int getNumParedes() {
		return numParedes;
	}

	public void setNumParedes(int numParedes) {
		this.numParedes = numParedes;
	}

	public int getNumPortas() {
		return numPortas;
	}

	public void setNumPortas(int numPortas) {
		this.numPortas = numPortas;
	}

	public float getMetragem() {
		return metragem;
	}

	public void setMetragem(float metragem) {
		this.metragem = metragem;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(long idContrato) {
		this.idContrato = idContrato;
	}
	
	

	
	
}
