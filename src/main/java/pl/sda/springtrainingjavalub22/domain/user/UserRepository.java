package pl.sda.springtrainingjavalub22.domain.user;

import java.util.Optional;

public interface UserRepository {
    void create(User user);

    Optional<User> findByUsername(String username);
}
