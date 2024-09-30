package tpi.backend.e_commerce.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tpi.backend.e_commerce.models.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    @Query("select CASE when COUNT(u) > 0 then true else false end from User u where UPPER(u.email) = UPPER(?1)")
    boolean existsByEmail(String email);
}

