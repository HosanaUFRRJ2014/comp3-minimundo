package entity;

import java.util.LinkedList;

public class Mobilia 
{
	private long id;
	private long idItemVenda;
	private long idComodo;
	private String descricao;
	private float custoProducao;
	private int tempoEntrega; //num semanas
	
	//private LinkedList<Comodo> comodosAssociados = new LinkedList<Comodo>();
	private LinkedList<Integer> tiposComodos = new LinkedList<Integer>();
	private ItemVenda itemVendaAssociado;
	
	/*public boolean associarComodos(Comodo comodo)
	{
		if(comodo != null)
			return comodosAssociados.add(comodo);
		
		return false;
		
	}*/
	
	public void associarTipoComodo(String tipo) 
	{
		switch(tipo)
		{
		case "Cozinha":
			tiposComodos.add(1);
			break;
			
		case "Sala" :
			tiposComodos.add(2);
			break;
			
		case "Quarto" :
			tiposComodos.add(3);
			break;
			
		case "ComodoComposto" :
			tiposComodos.add(4);
			break;	
		}
		
	}
	
	public boolean associarItemVenda(ItemVenda it)
	{
		if(it != null  && ! isItemVendaAssociado())
		{
		   itemVendaAssociado = it;
		   return true;
		}
		
		return false;
	}
	
	public boolean isItemVendaAssociado()
	{
		if(itemVendaAssociado != null)
			return true;
		
		return false;
		
	}
	
	public boolean isRemovivel()
	{
		if(!isItemVendaAssociado())
			return true;
		
		return false;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getCustoProducao() {
		return custoProducao;
	}

	public void setCustoProducao(float custoProducao) {
		this.custoProducao = custoProducao;
	}

	public int getTempoEntrega() {
		return tempoEntrega;
	}

	public void setTempoEntrega(int tempoEntrega) {
		this.tempoEntrega = tempoEntrega;
	}

	/*public LinkedList<Comodo> getComodosAssociados() {
		return comodosAssociados;
	}

	public void setComodosAssociados(LinkedList<Comodo> comodosAssociados) {
		this.comodosAssociados = comodosAssociados;
	}*/
    
	
	public ItemVenda getItemVendaAssociado() {
		return itemVendaAssociado;
	}

	public LinkedList<Integer> getTiposComodos() {
		return tiposComodos;
	}

	public void setTiposComodos(LinkedList<Integer> tiposComodos) {
		this.tiposComodos = tiposComodos;
	}

	/*public void setItemVendaAssociado(ItemVenda itemVendaAssociado) {
		this.itemVendaAssociado = itemVendaAssociado;
	}*/

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getIdItemVenda() {
		return idItemVenda;
	}

	public void setIdItemVenda(long idItemVenda) {
		this.idItemVenda = idItemVenda;
	}

	public long getIdComodo() {
		return idComodo;
	}

	public void setIdComodo(long idComodo) {
		this.idComodo = idComodo;
	}

	
	
	
	
	
	
	/*O sistema deverá permitir a alteração de atributos e associações entre Cômodos e Mobílias desde que não
existam Itens de Venda associados as Mobílias em questão. Assim como:
 Uma Mobília só poderá ser removida se não estiver associada a instâncias de ItemVenda.
 Um Cômodo só pode ser removido se não apresentar Mobílias e ComodosCompostos associados
a ele no momento da remoção.

*
*Remoção de MObilia de comodo e vice-versa ou remoção de instancias desse objeto do sistema?
*
*/
}
