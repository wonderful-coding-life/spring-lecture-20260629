import lombok.extern.slf4j.Slf4j;

import java.sql.*;

@Slf4j
public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://15.165.236.181:3306/tutor",
                "tutor",
                "tutorp");

        Long id = 2L;

//        Statement statement = connection.createStatement();
//        ResultSet resultSet = statement.executeQuery("SELECT * FROM member WHERE id = " + id);

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM member WHERE id = ?");
        statement.setLong(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Member member = Member.builder()
                    .id(resultSet.getLong("id"))
                    .name(resultSet.getString("name"))
                    .email(resultSet.getString("email"))
                    .age(resultSet.getInt("age"))
                    .build();
            log.info("회원 = {}", member);
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
