package net.secure.authorization.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import net.secure.authorization.entities.User;

public interface UserRepository extends JpaRepository<User, String> {

}
