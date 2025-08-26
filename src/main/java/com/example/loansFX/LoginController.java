package com.example.loansFX;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import okhttp3.*;

import java.io.IOException;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    private final OkHttpClient client = new OkHttpClient();

    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        String jsonBody = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
        RequestBody body = RequestBody.create(jsonBody, mediaType);

        Request request = new Request.Builder()
                .url("http://localhost:8080/api/login")
                .post(body)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Platform.runLater(() -> messageLabel.setText("Error de conexión"));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseBody = response.body() != null ? response.body().string() : "";
                Platform.runLater(() -> {
                    if(response.isSuccessful()) {
                        TokenManager.setTokensFromJson(responseBody);
                        messageLabel.setText("Login exitoso!");
                    } else {
                        messageLabel.setText("Usuario o contraseña incorrectos");
                    }
                });
            }
        });
    }
}
