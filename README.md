# Moodle API - Clube Centaury

Projeto Spring Boot básico que serve como ponte entre uma instância do Moodle e um site Wix (Clube Centaury).

## O que está incluído
- Aplicativo Spring Boot (Java 17)
- Exemplo simples de autenticação JWT
- `MoodleClient` com exemplos para listar cursos e matricular usuários
- `application.yml` configurado para banco de dados H2 em memória (desenvolvimento)
- `Dockerfile` + `docker-compose.yml` para testes locais
- README com instruções

## Executar localmente (desenvolvimento)
1. Compilar:


mvn clean package

2. Executar:

mvn spring-boot:run

3. Defina `moodle.url` e `moodle.token` em `application.yml` ou em variáveis ​​de ambiente.

## Conectar com o Wix
- O frontend do Wix pode chamar este backend usando fetch/AJAX.

- Certifique-se de que o CORS permita o seu domínio Wix (exemplo de permissão em `application.yml`).

- Use `POST /api/auth/login` para obter o JWT; inclua o cabeçalho `Authorization: Bearer <token>` ao chamar endpoints protegidos.

## Docker (local)
- `Dockerfile` e `docker-compose.yml` incluídos para testes locais rápidos com Postgres.

- Atualize as variáveis ​​de ambiente em `docker-compose.yml` conforme necessário.

## Notas de segurança
- Não mantenha segredos no código-fonte. Use variáveis ​​de ambiente em produção.

- Use HTTPS em produção.

- Substitua o login de demonstração pela autenticação adequada do Moodle ou pelo seu banco de dados de usuários.
   ```
3. API:
Matricular usuário em um curso:
POST http://localhost:8080/matricula?userId=1&cursoId=2 *matricula aluno:1 no curso:2

Listar cursos de um usuário:
GET http://localhost:8080/matricula/user/1 *verifica em quais cursos o aluno está matriculado

Listar usuários de um curso:
GET http://localhost:8080/matricula/course/2 *verifica quais são os alunos matriculados no curso


Cancelar matrícula:
DELETE http://localhost:8080/matricula/5 *cancela uma respectiva matricula

## Configurar integração com o Moodle
1. No Moodle: Admin > Administração do site > Plugins > Serviços Web > Gerenciar tokens
2. Crie um token para um usuário de serviço (com permissão para matricular usuários e visualizar cursos)
3. Defina `moodle.url` e `moodle.token` em `application.yml` ou em variáveis ​​de ambiente.

## Conectar com o Wix
- O frontend do Wix pode chamar este backend usando fetch/AJAX.
- Certifique-se de que o CORS permita o seu domínio Wix (exemplo de permissão em `application.yml`).

- Use `POST /api/auth/login` para obter o JWT; inclua o cabeçalho `Authorization: Bearer <token>` ao chamar endpoints protegidos.

## Docker (local)
- `Dockerfile` e `docker-compose.yml` incluídos para testes locais rápidos com Postgres.

- Atualize as variáveis ​​de ambiente em `docker-compose.yml` conforme necessário.

## Notas de segurança
- Não mantenha segredos no código-fonte. Use variáveis ​​de ambiente em produção.

- Use HTTPS em produção.

- Substitua o login de demonstração pela autenticação adequada do Moodle ou pelo seu banco de dados de usuários.

