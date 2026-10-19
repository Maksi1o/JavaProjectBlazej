package pd6.service;

import pd6.model.User;
import pd6.repository.Repository;

import java.util.Optional;

public class UserService {
    private final Repository<Long, User> userRepository;

    public UserService(Repository<Long, User> userRepository) {
        this.userRepository = userRepository;
    }

    public void changeName(Long id, String name) {
        Optional<User> findUser = userRepository.findById(id);
        findUser.ifPresent(user -> user.changeName(name));
    }
}
