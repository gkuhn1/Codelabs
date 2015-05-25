package com.eduardoptorezan.exand_bibliotecamusical;

import android.content.Context;
import android.widget.Toast;

public class Notificacoes extends NoticacoesAbs {

	@Override
	public void NotificarPub(Context pAppContext, enumTipoNotificacao pTipoNotif, String psTexto) {
		
		NotificarPriv(pAppContext, pTipoNotif, psTexto);
		
	}
	
	private void NotificarPriv(Context pAppContext, enumTipoNotificacao pTipoNotif, String psTexto)
	{
		
		if ((pTipoNotif == enumTipoNotificacao.Toast)) {
			Toast.makeText(pAppContext, psTexto,Toast.LENGTH_LONG).show();
		}
		else if ((pTipoNotif == enumTipoNotificacao.Dialog)) {
			
		}
		else if ((pTipoNotif == enumTipoNotificacao.ActionBar)) {
			
		}
	}

}
