package me.moises.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.moises.model.entity.Checklist;

public interface ChecklistRepository extends JpaRepository<Checklist, Long>{
	Optional<Checklist> findByToken(String uuid);
}
