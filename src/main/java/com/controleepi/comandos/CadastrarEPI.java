package com.controleepi.comandos;

import java.util.HashMap;
import java.util.Map;

public class CadastrarEPI {

    private final Map<String, String> estadoUsuario = new HashMap<>();
    private final Map<String, EPI> dadosTemporarios = new HashMap<>();
    private final EPIDao dao = new EPIDao();

    public String processarMensagem(String chatId, String texto) {
        if (texto.equalsIgnoreCase("/cadastrar")) {
            estadoUsuario.put(chatId, "esperando_nome");
            dadosTemporarios.put(chatId, new EPI(null, null));
            return "📦 Qual é o nome da EPI?";
        }

        if (estadoUsuario.containsKey(chatId)) {
            String estado = estadoUsuario.get(chatId);

            switch (estado) {
                case "esperando_nome":
                    dadosTemporarios.get(chatId).setNome(texto);
                    estadoUsuario.put(chatId, "esperando_validade");
                    return "📅 Agora informe a validade da EPI (formato: AAAA-MM-DD):";

                case "esperando_validade":
                    dadosTemporarios.get(chatId).setValidade(texto);
                    dao.inserirEPI(dadosTemporarios.get(chatId));
                    estadoUsuario.remove(chatId);
                    dadosTemporarios.remove(chatId);
                    return "✅ EPI cadastrado com sucesso!";

                default:
                    return "❓ Estado inválido. Digite /cadastrar para começar de novo.";
            }
        }

        return null; // Mensagem que não faz parte do fluxo
    }
}
