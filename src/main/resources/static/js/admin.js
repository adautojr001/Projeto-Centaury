const API_URL = "http://localhost:8081/cursos";
const API_MATRICULA = "http://localhost:8081/matriculas";
const API_MATERIAIS = "http://localhost:8082/materiais";

document.addEventListener("DOMContentLoaded", () => {
    fetchCursosAdmin();
});

// Função principal para buscar cursos e montar o frontend admin
function fetchCursosAdmin() {
    fetch(API_URL)
        .then(response => response.json())
        .then(cursos => {
            const container = document.getElementById("cursos-container");
            container.innerHTML = "";

            cursos.forEach(curso => {
                const card = document.createElement("div");
                card.classList.add("card");

                const alunosSpan = document.createElement("div");
                alunosSpan.classList.add("alunos");
                alunosSpan.innerText = "Carregando...";

                // Botões admin
                const btnContainer = document.createElement("div");
                btnContainer.classList.add("admin-buttons");

                const alterarBtn = document.createElement("button");
                alterarBtn.innerText = "Alterar";
                alterarBtn.classList.add("alterar-btn");

                const deletarBtn = document.createElement("button");
                deletarBtn.innerText = "Deletar";
                deletarBtn.classList.add("deletar-btn");

                btnContainer.appendChild(alterarBtn);
                btnContainer.appendChild(deletarBtn);

                // Lista de materiais
                const materiaisList = document.createElement("ul");
                materiaisList.classList.add("materiais-list");

                // Formulário para adicionar material
                const formMaterial = document.createElement("div");
                formMaterial.classList.add("form-material");
                formMaterial.innerHTML = `
                    <input type="text" placeholder="Título" class="titulo">
                    <input type="text" placeholder="URL" class="url">
                    <select class="tipo">
                        <option value="artigo">Artigo</option>
                        <option value="video">Vídeo</option>
                        <option value="pdf">PDF</option>
                    </select>
                    <button>Adicionar Material</button>
                `;

                const addBtn = formMaterial.querySelector("button");
                addBtn.onclick = () => {
                    const titulo = formMaterial.querySelector(".titulo").value;
                    const url = formMaterial.querySelector(".url").value;
                    const tipo = formMaterial.querySelector(".tipo").value;

                    if (!titulo || !url) {
                        alert("Preencha todos os campos!");
                        return;
                    }

                    fetch(`${API_MATERIAIS}/add`, {
                        method: "POST",
                        headers: { "Content-Type": "application/json" },
                        body: JSON.stringify({ titulo, url, tipo, curso: { id: curso.id } })
                    })
                        .then(res => res.json())
                        .then(() => {
                            alert("Material adicionado!");
                            carregarMateriais(curso.id, materiaisList);
                        })
                        .catch(err => console.error("Erro ao adicionar material:", err));
                };

                card.innerHTML = `
                    <h3>${curso.nome}</h3>
                    <p>${curso.descricao}</p>
                `;
                card.appendChild(alunosSpan);
                card.appendChild(btnContainer);
                card.appendChild(materiaisList);
                card.appendChild(formMaterial);

                container.appendChild(card);

                // Buscar quantidade de alunos matriculados
                fetch(`${API_MATRICULA}/curso/${curso.id}`)
                    .then(resp => resp.json())
                    .then(matriculas => {
                        alunosSpan.innerText = `${matriculas.length} aluno(s) matriculado(s)`;
                    })
                    .catch(err => {
                        console.error("Erro ao buscar matrículas: ", err);
                        alunosSpan.innerText = "Erro ao carregar alunos";
                    });

                // Buscar materiais do curso
                carregarMateriais(curso.id, materiaisList);

                // Eventos dos botões
                deletarBtn.onclick = () => deletarCurso(curso.id);
                alterarBtn.onclick = () => alterarCurso(curso);
            });
        })
        .catch(error => {
            console.error("Erro ao carregar cursos:", error);
            alert("Erro ao carregar os cursos. Veja o console.");
        });
}

// Função para carregar materiais
function carregarMateriais(cursoId, container) {
    fetch(`${API_MATERIAIS}/curso/${cursoId}`)
        .then(res => res.json())
        .then(materiais => {
            container.innerHTML = "";
            materiais.forEach(mat => {
                const li = document.createElement("li");
                li.innerHTML = `<strong>${mat.titulo}</strong> (${mat.tipo}) - <a href="${mat.url}" target="_blank">Abrir</a>`;
                container.appendChild(li);
            });
        })
        .catch(err => {
            console.error("Erro ao carregar materiais: ", err);
            container.innerHTML = "<li>Erro ao carregar materiais</li>";
        });
}

// Deletar curso
function deletarCurso(cursoId) {
    if (!confirm("Deseja realmente deletar este curso?")) return;

    fetch(`${API_URL}/${cursoId}`, { method: "DELETE" })
        .then(response => {
            if (response.ok) {
                alert("Curso deletado com sucesso!");
                fetchCursosAdmin();
            } else {
                alert("Erro ao deletar curso.");
            }
        })
        .catch(err => {
            console.error("Erro ao deletar curso:", err);
            alert("Erro de conexão com o servidor.");
        });
}

// Alterar curso
function alterarCurso(curso) {
    const novoNome = prompt("Novo nome do curso:", curso.nome);
    const novaDescricao = prompt("Nova descrição do curso:", curso.descricao);

    if (!novoNome || !novaDescricao) return;

    fetch(`${API_URL}/${curso.id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ nome: novoNome, descricao: novaDescricao })
    })
        .then(response => {
            if (response.ok) {
                alert("Curso atualizado com sucesso!");
                fetchCursosAdmin();
            } else {
                alert("Erro ao atualizar curso.");
            }
        })
        .catch(err => {
            console.error("Erro ao atualizar curso:", err);
            alert("Erro de conexão com o servidor.");
        });
}








