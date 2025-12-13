const API_URL = "http://localhost:8082/cursos";
const API_MATRICULA = "http://localhost:8082/matriculas";

document.addEventListener("DOMContentLoaded", () => {
    const userId = 1; // ID do aluno logado (exemplo)
    fetchCursos(userId);

    // Atualiza a lista de cursos a cada 10 segundos
    setInterval(() => fetchCursos(userId), 10000);
});

function fetchCursos(userId) {
    fetch(API_URL)
        .then(response => response.json())
        .then(cursos => {
            const container = document.getElementById("cursos-container");
            container.innerHTML = "";

            cursos.forEach(curso => {
                const card = document.createElement("div");
                card.classList.add("card");

                const matriculaBtn = document.createElement("button");
                matriculaBtn.classList.add("matricular-btn");
                matriculaBtn.innerText = "Matricular";

                card.innerHTML = `
                    <h3>${curso.nome}</h3>
                    <p>${curso.descricao}</p>
                `;

                card.appendChild(matriculaBtn);
                container.appendChild(card);

                // Evento do botão de matrícula
                matriculaBtn.onclick = () =>
                    matricularCurso(userId, curso.id, matriculaBtn);
            });
        })
        .catch(error => {
            console.error("Erro ao carregar cursos:", error);
            alert("Erro ao carregar os cursos. Veja o console.");
        });
}

function matricularCurso(userId, cursoId, btn) {
    fetch(`${API_MATRICULA}/matricular`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ userId: userId, cursoId: cursoId })
    })
        .then(response => {
            if (response.ok) {
                alert("Matrícula realizada com sucesso!");
                btn.innerText = "Matriculado";
                btn.disabled = true;
            } else {
                alert("Não foi possível realizar a matrícula.");
            }
        })
        .catch(error => {
            console.error("Erro:", error);
            alert("Erro de conexão com o servidor.");
        });
}












