console.log("Portal de cursos carregado com sucesso!");

// Exemplo de animação simples no botão
const buttons = document.querySelectorAll("button");

buttons.forEach(btn => {
    btn.addEventListener("mouseover", () => {
        btn.style.transform = "scale(1.05)";
        btn.style.transition = "0.2s";
    });
    btn.addEventListener("mouseleave", () => {
        btn.style.transform = "scale(1)";
    });
});

