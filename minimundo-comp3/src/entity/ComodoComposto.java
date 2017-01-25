package entity;

import java.util.LinkedList;



public class ComodoComposto extends Comodo 
{
	private LinkedList<Comodo> comodos = new LinkedList<Comodo>(); 
	
	@Override
	public LinkedList<Mobilia> listaMobiliaDisponivel()
	{
		LinkedList<Mobilia> todasMobilias = new LinkedList<Mobilia>(); 
		
		for(Comodo c : comodos)
		{
		
			todasMobilias.addAll(c.listaMobiliaDisponivel());
			
		}
		
		return todasMobilias;	
	}
	
	/*@Override
	public boolean isRemovivel()
	{
		if(listaMobiliaDisponivel().size() == 0 && comodos.size() == 0)
			return true;
		
		return false;
	}*/
	

}
