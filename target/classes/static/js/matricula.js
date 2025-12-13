const userId = 3; // ID do usuário logado (pode ser dinâmico)

function carregarCursos() {
    fetch('http://localhost:8080/cursos') // endpoint GET de cursos
        .then(res => res.json())
        .then(cursos => {
            const container = document.getElementById('cursosContainer');
            container.innerHTML = '';
            cursos.forEach(curso => {
                const card = document.createElement('div');
                card.classList.add('card');
                card.innerHTML = `
                    <h3>${curso.nome || 'Curso sem nome'}</h3>
                    <p>${curso.descricao || 'Sem descrição'}</p>
                    <button onclick="matricular(${curso.id})">Matricular</button>
                `;
                container.appendChild(card);
            });
        })
        .catch(err => console.error(err));
}

function matricular(cursoId) {
    fetch(`http://localhost:8080/matriculas?userId=${userId}&cursoId=${cursoId}`, {
        method: 'POST'
    })
    .then(res => res.text())
    .then(msg => {
        document.getElementById('msg').innerText = msg;
        carregarCursos(); // atualiza os cursos (opcional)
    })
    .catch(err => console.error(err));
}

// Carrega cursos ao abrir a página
carregarCursos();
