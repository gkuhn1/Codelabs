package com.example.exand_servico_despertador;

import java.io.Serializable;

public class Horario implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String Nome;
	public String Horario;
	public Boolean Ativo = false;
	
	public String getNome() {        
		return Nome;    
	}    
	public void setNome(String psNome) {        
		this.Nome = psNome;    
	}
	
	public String getHorario() {        
		return Horario;    
	}    
	public void setHorario(String psHorario) {        
		this.Nome = psHorario;    
	}
	
	public Boolean getAtivo() {        
		return Ativo;    
	}    
	public void setAtivo(Boolean pbAtivo) {        
		this.Ativo = pbAtivo;    
	}    

}
