package pick.login;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.openid4java.association.AssociationException;
import org.openid4java.consumer.ConsumerException;
import org.openid4java.discovery.DiscoveryException;
import org.openid4java.message.MessageException;
import pick.secret.api.SteamApi;
import pick.steam.method.SteamMethod;



import java.io.*;

import java.net.URL;


public class Login {
    CallBack callBack = new CallBack("http://localhost:8080");
    SteamAuthenticator login = new SteamAuthenticator(callBack);
    String test = "";
    private final SteamApi key = new SteamApi("DD009638F04CD0FDFEC255AC9C02EF79");
    String code;
    SteamMethod methodS = new SteamMethod();




    @FXML
    private Button join;

    public Login() throws DiscoveryException {
    }


    @FXML
    void initialize() {
        join.setOnAction(event -> {
            Stage stage = new Stage();
            final WebView view = new WebView();
            final WebEngine engine = view.getEngine();
            try {
                engine.load(login.getLoginUrl());

                System.out.println(login.getLoginUrl());
                stage.setScene(new Scene(view));
                stage.show();


                engine.locationProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue.startsWith(test)) {
                            code = newValue;
                    }
                    if(!code.equals("https://steamcommunity.com/openid/login")) {
                        try {
                            URL url = new URL(code);
                            String id = login.getVerifiedSteamId(login.splitQuery(url));
                            System.out.println(id);
                            JSONObject jsObject = methodS.getLogin(key,id);
                            FileWriter file = new FileWriter("user.json");
                            file.write(JSONValue.toJSONString(jsObject));
                            file.flush();
                            stage.close();
                            Parent profile = FXMLLoader.load(getClass().getResource("../pick.fxml"));
                            Scene profil = new Scene(profile);
                            Stage prof = (Stage) ((Node) event.getSource()).getScene().getWindow();
                            prof.setScene(profil);
                        } catch (MessageException | DiscoveryException | AssociationException | NullPointerException | IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (MessageException | ConsumerException e) {
                e.printStackTrace();
            }


        });

    }


}












