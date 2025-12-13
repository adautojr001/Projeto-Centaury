const button = document.getElementById('chatbot-button');
const chatWindow = document.getElementById('chatbot-window');
const chatArea = document.getElementById('chat-area');
const chatInput = document.getElementById('chat-input');

// Toggle do chat
button.onclick = () => {
    chatWindow.style.display = chatWindow.style.display === 'flex' ? 'none' : 'flex';
};

// Adiciona mensagens ao chat
function adicionarMensagem(remetente, mensagem) {
    const div = document.createElement('div');
    div.textContent = `${remetente}: ${mensagem}`;
    div.className = remetente === 'Você' ? 'user-msg' : 'bot-msg';
    chatArea.appendChild(div);
    chatArea.scrollTop = chatArea.scrollHeight;
}

// Envia pergunta para o backend
async function enviarPergunta() {
    const pergunta = chatInput.value.trim();
    if (!pergunta) return;

    adicionarMensagem('Você', pergunta);
    chatInput.value = '';

    try {
        const response = await fetch('/chatbot', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ question: pergunta })
        });

        const data = await response.json();
        adicionarMensagem('Bot', data.answer || 'Desculpe, não encontrei uma resposta.');
    } catch (err) {
        adicionarMensagem('Bot', 'Erro ao se conectar com o servidor.');
        console.error(err);
    }
}

// Listener tecla Enter
chatInput.addEventListener('keydown', (e) => {
    if (e.key === 'Enter') enviarPergunta();
});


