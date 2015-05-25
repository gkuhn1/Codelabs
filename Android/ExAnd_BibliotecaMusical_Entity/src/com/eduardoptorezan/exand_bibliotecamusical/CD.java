package com.eduardoptorezan.exand_bibliotecamusical;

import java.io.Serializable;

public class CD implements Serializable {
	public String NomeDoAlbum;
	public String Ano;
	public String Compositor;
	public String Gravadora;
	public String Observacoes;
	
	public String getsNomeAlbum() {        
		return NomeDoAlbum;    
	}    
	public void setsNomeAlbum(String psNome) {        
		this.NomeDoAlbum = psNome;    
	}    
	public String getsAno() {        
		return Ano;    
	}    
	public void setsAno(String psAno) {        
		this.Ano = psAno;    
	}
	public String getsCompositor() {        
		return Compositor;    
	}    
	public void setsCompositor(String psCompositor) {        
		this.Compositor = psCompositor;    
	}    
	
}
