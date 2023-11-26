package bcsdbeginner.jdbc.DBConnection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static bcsdbeginner.jdbc.DBConnection.DBConnectionConst.*;
@Slf4j
public class DBConnectionManager {

    //connection 가져오기
    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("connection={}", connection); // mySql jdbc 드라이버가 들어갔는지 확인
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
