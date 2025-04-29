package com.controleepi.bot;

import com.controleepi.comandos.CadastrarEPI;
import com.controleepi.comandos.ListarEPI;
import com.controleepi.model.EPI;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe principal do bot Telegram para controle de EPI.
 * Ele cadastra um EPI interagindo passo a passo com o usuário.
 */
@Component // Diz ao Spring que esta classe é um componente gerenciado
public class MeuBot extends TelegramLongPollingBot {

    private final CadastrarEPI cadastrarEPI = new CadastrarEPI();


    // Armazena o estado atual de cada usuário (chatId -> estado)
    private final Map<String, String> estadoUsuario = new HashMap<>();

    // Armazena temporariamente os dados do EPI enquanto o usuário responde
    private final Map<String, EPI> dadosTemporarios = new HashMap<>();

    /**
     * Retorna o nome de usuário do bot registrado no BotFather
     */
    @Override
    public String getBotUsername() {
        return ConexaoBot.getUsernameBot(); // Substitua pelo seu username real
    }

    /**
     * Retorna o token do bot fornecido pelo BotFather
     */
    @Override
    public String getBotToken() {
        return ConexaoBot.getTokenBot(); // Coloque o token real aqui
    }

    /**
     * Método chamado automaticamente sempre que o bot receber uma nova mensagem
     */
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            String texto = update.getMessage().getText();

            switch (texto) {
                case "/listar" -> {
                    ListarEPI listarEPI = new ListarEPI();
                    String lista = listarEPI.listarEPIs().toString();
                    enviarMensagem(chatId, lista);
                }
                case "/cadastrar" -> {
                    String resposta = cadastrarEPI.processarMensagem(chatId, texto);
                    if (resposta != null) {
                        enviarMensagem(chatId, resposta);
                    }
                }
                case "/editar" -> {
                    enviarMensagem(chatId, "função em Desenvolvimento");
                }
                case "/remover" -> {
                    enviarMensagem(chatId, "Função em Desenvolvimento");
                }
                default -> {
                    String resposta = cadastrarEPI.processarMensagem(chatId, texto);
                    if (resposta != null) {
                        enviarMensagem(chatId, resposta);
                    } else {
                        enviarMensagem(chatId, "👋 Olá! Digite /cadastrar para registrar uma nova EPI ou /listar para listar.");
                    }
                }
            }
        }
    }

    /**
     * Método auxiliar para enviar mensagens para o Telegram
     */
    private void enviarMensagem(String chatId, String texto) {
        SendMessage msg = new SendMessage(chatId, texto); // Cria mensagem
        try {
            execute(msg); // Envia
        } catch (TelegramApiException e) {
            e.printStackTrace(); // Mostra erro se não conseguir enviar
        }
    }
}
