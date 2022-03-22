package me.moises.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.moises.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	public Optional<User> findByEmail(String email);
	public Optional<User> findByEmailAndPassword(String email, String password);
}
