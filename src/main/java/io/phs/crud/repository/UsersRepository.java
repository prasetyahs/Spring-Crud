package io.phs.crud.repository;

import io.phs.crud.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {



}
