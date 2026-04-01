package ge.edu.sangu.giorgi.datatransferapp.usersServices;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatorAccessTest {

    @AfterEach
    void resetEmail() {
        AuthData.setEMAIL(null);
    }

    @Test
    void testCheckAccess_whenEmailIsNull_shouldReturnTrue() {
        AuthData.setEMAIL(null);
        assertTrue(CreatorAccess.checkAccess());
    }

    @Test
    void testCheckAccess_whenEmailIsGuest_shouldReturnTrue() {
        AuthData.setEMAIL("guestemail@email.com");
        assertTrue(CreatorAccess.checkAccess());
    }

    @Test
    void testCheckAccess_whenEmailIsRegular_shouldReturnFalse() {
        AuthData.setEMAIL("test@example.com");
        assertFalse(CreatorAccess.checkAccess());
    }
}

