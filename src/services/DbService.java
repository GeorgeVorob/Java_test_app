package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import data.models.Job;
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

    public boolean Autorize(String username) {
        String command = "SELECT COUNT(*) as amount FROM users;";

        try (Connection conn = GetConn()) {
            Statement statement = conn.createStatement();

            ResultSet usersData = statement.executeQuery(command);
            if (usersData.next() && usersData.getInt(1) > 0) {
                return true;
            }

            return false;
        } catch (Exception x) {
            return false;
        }
    }

    public boolean AddUser(String name, Date birthDate, String jobName) {
        String command = """
                INSERT INTO users (Name, BirthDate, Job_id)
                VALUES (
                     ? ,
                  ? ,
                (SELECT id FROM jobs WHERE Name = ? LIMIT 1)
                );
                    """;

        try (Connection conn = GetConn()) {
            PreparedStatement preparedStatement = conn.prepareStatement(command);
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, birthDate);
            preparedStatement.setString(3, jobName);

            int rows = 0;
            rows = preparedStatement.executeUpdate();

            return rows > 0 ? true : false;
        } catch (Exception x) {
            return false;
        }
    }

    public List<Job> GetJobs() {
        String command = "SELECT * FROM jobs;";

        try (Connection conn = GetConn()) {
            Statement statement = conn.createStatement();

            ResultSet jobsData = statement.executeQuery(command);

            List<Job> results = new ArrayList<>();
            while (jobsData.next()) {
                Job job = new Job();
                job.id = jobsData.getInt(1);
                job.name = jobsData.getString(2);

                results.add(job);
            }

            return results;
        } catch (Exception x) {
            return null;
        }
    }
}
