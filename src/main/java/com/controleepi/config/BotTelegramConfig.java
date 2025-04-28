package com.controleepi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * Classe de configuração para registrar o bot no TelegramBotsApi.
 */
@Configuration
public class BotTelegramConfig {

    @Bean
    public TelegramBotsApi telegramBotsApi(MeuBot meuBot) throws Exception {
        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(meuBot);
        return api;
    }
}
