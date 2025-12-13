// Banco fake local (trocar depois pelo backend)
const cursos = {
    "Java Básico": {
        descricao: "Curso introdutório para aprender os fundamentos da linguagem Java.",
        carga: "40h"
    },
    "Spring Boot": {
        descricao: "Aprenda a desenvolver APIs REST usando Spring Boot.",
        carga: "50h"
    },
    "Front-End": {
        descricao: "HTML, CSS, JavaScript e boas práticas modernas.",
        carga: "60h"
    },
    "Python": {
        descricao: "Primeiros passos na linguagem Python.",
        carga: "45h"
    }
};

// Pega parâmetro ?curso=...
const params = new URLSearchParams(window.location.search);
const nome = params.get("curso");

// Preenche os dados
if (nome && cursos[nome]) {
    document.getElementById("tituloCurso").innerText = nome;
    document.getElementById("nomeCurso").innerText = nome;
    document.getElementById("descricaoCurso").innerText = cursos[nome].descricao;
    document.getElementById("cargaCurso").innerText = cursos[nome].carga;
}

// Botão voltar
document.getElementById("btnVoltar").addEventListener("click", () => {
    window.location.href = "cursos.html";
});

// Botão inscrever
document.getElementById("btnInscrever").addEventListener("click", () => {
    alert("Você se inscreveu no curso: " + nome);
});
