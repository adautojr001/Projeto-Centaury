package br.com.clubecentaury.repository;

import br.com.clubecentaury.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}



