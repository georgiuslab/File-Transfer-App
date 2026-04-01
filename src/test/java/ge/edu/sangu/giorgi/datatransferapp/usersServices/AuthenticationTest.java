package ge.edu.sangu.giorgi.datatransferapp.usersServices;

import ge.edu.sangu.giorgi.datatransferapp.DBConnect;
import org.h2.jdbcx.JdbcDataSource;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthenticationTest {

    @BeforeAll
    void setupDatabase() throws Exception {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setURL("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUser("sa");
        dataSource.setPassword("sa");

        DBConnect.setCustomDataSource(dataSource);

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE USERS (EMAIL VARCHAR(255), PASSWORD VARCHAR(255))");
            stmt.executeUpdate("INSERT INTO USERS (EMAIL, PASSWORD) VALUES ('test@example.com', '1234')");
        }
    }

    @Test
    void testLoginSuccess() {
        Map<String, String> result = Authentication.login("test@example.com", "1234");
        assertEquals("200", result.get("statusCode"));
        assertEquals("test@example.com", result.get("username"));
    }

    @Test
    void testLoginFailure() {
        Map<String, String> result = Authentication.login("wrong@example.com", "wrongpass");
        assertEquals("404", result.get("statusCode"));
        assertNull(result.get("username"));
    }
}
