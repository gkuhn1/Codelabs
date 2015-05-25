package com.eduardoptorezan.exand_bibliotecamusical;

import android.content.Context;

public abstract class NoticacoesAbs {
	public boolean ExibindoNotificacao;
	
	public abstract void NotificarPub(Context pAppContext, enumTipoNotificacao pTipoNotif, String psTexto);
}
