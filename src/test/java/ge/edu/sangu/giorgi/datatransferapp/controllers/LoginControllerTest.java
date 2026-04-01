package ge.edu.sangu.giorgi.datatransferapp.controllers;

import com.jcraft.jsch.Session;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.control.Label;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @BeforeAll
    static void initJFX() {
        new JFXPanel();
    }

    @Test
    void testSuccessfulLoginUpdatesMessageLabel() throws Exception {
        JschConnectionService mockService = mock(JschConnectionService.class);
        Session mockSession = mock(Session.class);
        when(mockService.connect("user", "localhost", 22, "pass")).thenReturn(mockSession);

        LoginController controller = new LoginController();
        controller.setConnectionService(mockService);

        Label messageLabel = new Label();
        Field messageLabelField = controller.getClass().getDeclaredField("messageLabel");
        messageLabelField.setAccessible(true);
        messageLabelField.set(controller, messageLabel);

        Field addressFieldField = controller.getClass().getDeclaredField("addressField");
        addressFieldField.setAccessible(true);
        javafx.scene.control.TextField addressField = new javafx.scene.control.TextField("localhost");
        addressFieldField.set(controller, addressField);

        Field portFieldField = controller.getClass().getDeclaredField("portField");
        portFieldField.setAccessible(true);
        javafx.scene.control.TextField portField = new javafx.scene.control.TextField("22");
        portFieldField.set(controller, portField);

        Field usernameFieldField = controller.getClass().getDeclaredField("usernameField");
        usernameFieldField.setAccessible(true);
        javafx.scene.control.TextField usernameField = new javafx.scene.control.TextField("user");
        usernameFieldField.set(controller, usernameField);

        Field passwordFieldField = controller.getClass().getDeclaredField("passwordField");
        passwordFieldField.setAccessible(true);
        javafx.scene.control.PasswordField passwordField = new javafx.scene.control.PasswordField();
        passwordField.setText("pass");
        passwordFieldField.set(controller, passwordField);

        controller.onLoginButtonClick();

        Thread.sleep(1000);

        Platform.runLater(() -> {
            assertEquals("Connected to user@localhost:22", messageLabel.getText());
        });
    }
}
