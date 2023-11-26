package bcsdbeginner.jdbc.DBConnection;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.assertj.core.api.Assertions.*;

class DBConnectionManagerTest {

    @Test
    void getConnection() {
        Connection connection = DBConnectionManager.getConnection();
        assertThat(connection).isNotNull();
    }
}