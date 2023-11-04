package ru.otus.hw12booksapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.otus.hw12booksapp.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
