# Moodle API - Clube Centaury

Skeleton Spring Boot project that acts as a bridge between a Moodle instance and a Wix website (Clube Centaury).

## What's included
- Spring Boot app (Java 17)
- Simple JWT authentication example
- `MoodleClient` with examples for listing courses and enrolling users
- `application.yml` configured for H2 in-memory DB (development)
- `Dockerfile` + `docker-compose.yml` for local testing
- README with instructions

## Run locally (dev)
1. Build:
   ```bash
   mvn clean package
   ```
2. Run:
   ```bash
   mvn spring-boot:run
   ```
3. API:
   - `POST /api/auth/login` -> `{ "email":"user@example", "password":"..." }` returns `{ "token": "..." }`
   - `GET  /api/courses` -> lista cursos do Moodle (requer configurar `moodle.url` e `moodle.token`)
   - `POST /api/courses/{courseId}/enroll?userId={userId}` -> inscreve usuário

## Configure Moodle integration
1. In Moodle: Admin > Site administration > Plugins > Web services > Manage tokens
2. Create a token for a service user (with capability to enrol users and view courses)
3. Set `moodle.url` and `moodle.token` in `application.yml` or in environment variables.

## Connect with Wix
- Wix frontend can call this backend using fetch/AJAX.
- Ensure CORS allows your Wix domain (example allowed in `application.yml`).
- Use `POST /api/auth/login` to obtain JWT; include `Authorization: Bearer <token>` header when calling protected endpoints.

## Docker (local)
- `Dockerfile` and `docker-compose.yml` included for quick local testing with Postgres.
- Update env vars in `docker-compose.yml` as needed.

## Security notes
- Do not keep secrets in source. Use environment variables in production.
- Use HTTPS in production.
- Replace the demo login with proper Moodle authentication or your user database.

