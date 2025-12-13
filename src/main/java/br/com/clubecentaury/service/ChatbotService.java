package br.com.clubecentaury.service;

import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class ChatbotService {

    private Map<String, String> knowledgeBase = new HashMap<>();

    @PostConstruct
    public void init() {
        // Inicializa a base de conhecimento
        knowledgeBase.put("como faço login?", "Para fazer login, clique no botão 'Entrar' no canto superior ou no menu Login e use suas credenciais.");
        knowledgeBase.put("como acessar o sistema?", "Para acessar o sistema, vá até a página principal e selecione o módulo desejado.");
        knowledgeBase.put("esqueci minha senha", "Se você esqueceu sua senha, clique em 'Esqueci minha senha' e siga os passos para redefini-la.");
        knowledgeBase.put("qual o horário de atendimento", "Nosso atendimento funciona de segunda a sexta, das 08:00 às 18:00.");
        knowledgeBase.put("como entrar em contato com suporte?", "Envie um e-mail para suporte@centaury.com.");
        
        
        // Adicione outras perguntas e respostas conforme necessário
    }

    public String obterResposta(String pergunta) {
        if (pergunta == null || pergunta.isBlank()) {
            return "Não entendi sua pergunta. Pode reformular?";
        }

        String normalizada = pergunta.trim().toLowerCase();

        // Retorna a resposta ou mensagem padrão caso não encontre
        return knowledgeBase.getOrDefault(normalizada,
                "Desculpe, não encontrei uma resposta para isso. Tente reformular sua pergunta.");
    }
}



