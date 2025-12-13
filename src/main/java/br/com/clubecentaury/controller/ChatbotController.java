package br.com.clubecentaury.controller;


import org.springframework.web.bind.annotation.*;

import java.util.Map;

import br.com.clubecentaury.service.ChatbotService;

@RestController
@RequestMapping("/chatbot")
@CrossOrigin(origins = "*")
public class ChatbotController {

    private final ChatbotService chatbotService;

    public ChatbotController(ChatbotService chatbotService) {
        this.chatbotService = chatbotService;
    }
    @GetMapping
public String getChatbotStatus() {
    return "Chatbot ativo no endpoint principal 🚀";
}

    @GetMapping("/test")
public String test() {
    return "Chatbot ativo 🚀";
}


    @PostMapping
    public Map<String, String> perguntar(@RequestBody Map<String, String> body) {
        String question = body.get("question");
        String resposta = chatbotService.obterResposta(question);
        return Map.of("answer", resposta);
    }
}


