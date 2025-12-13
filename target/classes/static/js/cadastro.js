const form = document.getElementById('cadastroForm');
const mensagem = document.getElementById('mensagem');

form.addEventListener('submit', async (e) => {
    e.preventDefault();

    const userData = {
        username: form.username.value,
        password: form.password.value,
        role: form.role.value,
        curso: form.curso.value // novo campo
    };

    try {
        const response = await fetch('http://localhost:8080/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(userData)
        });

        if (response.ok) {
            mensagem.style.color = 'green';
            mensagem.textContent = 'Aluno cadastrado com sucesso!';
            form.reset();
        } else {
            mensagem.style.color = 'red';
            mensagem.textContent = 'Erro ao cadastrar aluno.';
        }
    } catch (error) {
        mensagem.style.color = 'red';
        mensagem.textContent = 'Erro: ' + error.message;
    }
});

document.getElementById('btnVoltar').addEventListener('click', () => {
    window.location.href = 'index.html';
});

document.getElementById('btnCursos').addEventListener('click', () => {
    window.location.href = 'cursos.html';
});

