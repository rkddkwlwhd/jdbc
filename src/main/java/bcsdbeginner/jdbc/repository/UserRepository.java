package bcsdbeginner.jdbc.repository;

import bcsdbeginner.jdbc.DBConnection.DBConnectionManager;
import bcsdbeginner.jdbc.Domain.User;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class UserRepository {
    public Integer save(User user) {
        Connection connection = null;
        PreparedStatement statement = null; //statement 대신 사용
        String sql = "insert into users values(?,?,?,?,?)"; // user table의 컬럼이 5개

        try {
            connection = DBConnectionManager.getConnection(); // connection 얻어옴
            statement = connection.prepareStatement(sql); // statement를 얻어옴
            log.info("statement={}", statement);

            statement.setInt(1, user.getId());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getCreated_at().toString());
            statement.executeUpdate();

            return user.getId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResource(connection, statement, null); //사용한 리소스 반환
        }
    }

    public User findById(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "select * from users where id = ?";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);
            log.info("statement={}", statement);

            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            User findUser = new User();
            while (resultSet.next()) {
                findUser.setId(resultSet.getInt("id"));
                findUser.setUsername((resultSet.getString("username")));
                findUser.setEmail(resultSet.getString("email"));
                findUser.setPassword(resultSet.getString("password"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                findUser.setCreated_at(LocalDateTime.parse(resultSet.getString("created_at"), formatter));
            }
            return findUser;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResource(connection, statement, null); //사용한 리소스 반환
        }


    }

    public void updateUsername(Integer id, String username) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "update users set username = ? where id = ?";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, username);
            statement.setInt(2, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResource(connection, statement, null); //사용한 리소스 반환
        }
    }

    public void deleteUser(Integer id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from users where id = ?";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeResource(connection, statement, null); //사용한 리소스 반환
        }
    }
    private void closeResource(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                log.error("Error closing ResultSet", e);
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                log.error("Error closing PreparedStatement", e);
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                log.error("Error closing Connection", e);
            }
        }
    }

}
