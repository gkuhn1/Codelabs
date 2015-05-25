package com.eduardoptorezan.exand_bibliotecamusical;

import java.io.Serializable;

public class Musico implements Serializable {
	public String Nome;
	
	public String getsNome() {        
		return Nome;    
	}    
	public void setsNome(String psNome) {        
		this.Nome = psNome;    
	}    
 
	
}
