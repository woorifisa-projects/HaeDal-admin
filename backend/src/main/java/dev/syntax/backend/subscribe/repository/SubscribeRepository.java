package dev.syntax.backend.subscribe.repository;


import dev.syntax.backend.subscribe.model.Subscribe;
import dev.syntax.backend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    void deleteByUser(User user);
}
