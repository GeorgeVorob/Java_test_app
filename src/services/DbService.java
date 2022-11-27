package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import data.models.User;

public class DbService {
    private Connection GetConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/java?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
                    "root",
                    "root");
        } catch (Exception e) {
            return null;
        }
    }

    public List<User> GetUsers() {
        String command = "SELECT u.id, u.name, u.Job_id, j.Name, u.BirthDate FROM users as u JOIN jobs as j;";

        try (Connection conn = GetConn()) {
            Statement statement = conn.createStatement();

            ResultSet usersData = statement.executeQuery(command);

            List<User> results = new ArrayList<>();
            while (usersData.next()) {
                User user = new User();
                user.id = usersData.getInt(1);
                user.name = usersData.getString(2);
                user.job_id = usersData.getInt(3);
                user.job = usersData.getString(4);
                user.BirthDate = usersData.getDate(5);

                results.add(user);
            }

            return results;
        } catch (Exception x) {
            return null;
        }
    }
}
