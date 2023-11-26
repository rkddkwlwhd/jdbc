package bcsdbeginner.jdbc.repository;

import bcsdbeginner.jdbc.Domain.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;


class UserRepositoryTest {

    UserRepository userRepository = new UserRepository();
    @Test
    void save() {
        User user = new User(1, "Jerry", "jerry123@koreatech.ac.kr", "1234", LocalDateTime.now());
        Integer newId =userRepository.save(user);
        assertThat(newId).isEqualTo(1);
    }
    @Test
    void  findById() {
        User findUser = userRepository.findById(1);
        assertThat(findUser.getId()).isEqualTo(1);
    }

    @Test
    void updateUsername() {
        userRepository.updateUsername(1,"Tom");
        User updateUser = userRepository.findById(1);
        assertThat(updateUser.getUsername()).isEqualTo("Tom");
    }

    @Test
    void deleteUser() {
        userRepository.deleteUser(1);
        User deleteUser = userRepository.findById(1);
        assertThat(deleteUser.getId()).isNull();
    }
}