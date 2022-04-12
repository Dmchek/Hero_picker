package pick.profile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import pick.JsonParse;
import pick.secret.api.SteamApi;
import pick.steam.method.SteamMethod;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Profile {
    JSONObject user;
    SteamMethod steamMethod = new SteamMethod();
    private final SteamApi key = new SteamApi("DD009638F04CD0FDFEC255AC9C02EF79");

    @FXML
    private WebView avatar;

    @FXML
    private Label nick;

    @FXML
    private WebView hero;

    @FXML
    private Label hero_name;

    @FXML
    private Label skill;

    @FXML
    private Label result;

    @FXML
    private Label data;

    @FXML
    private Label mode_game;

    @FXML
    private Label match_time;

    @FXML
    private Label kda;

    @FXML
    void initialize() throws IOException, ParseException, JSONException {
        FileReader reader = new FileReader("user.json");
        JSONTokener jsonTokener = new JSONTokener(reader);
        user = new JSONObject(jsonTokener);
        String image = user.getJSONObject("response").getJSONArray("players").getJSONObject(0).getString("avatarfull");
        String name = user.getJSONObject("response").getJSONArray("players").getJSONObject(0).getString("personaname");
        nick.setText(name);
        String steamid = user.getJSONObject("response").getJSONArray("players").getJSONObject(0).getString("steamid");
        avatar.getEngine().load(image);
        System.out.println(steamid);
       // System.out.println(steamMethod.getHeroes(key));
        //System.out.println(JsonParse.readJsonFromUrl("https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key="+ key.getKey() + "&account_id=" + steamMethod.getAccountId(steamid)));
        JSONObject matchHistory = steamMethod.getMatchHistory(key.getKey(),steamid);
        System.out.println(matchHistory);
        System.out.println(matchHistory.getJSONObject("result").getJSONArray("matches").getJSONObject(0).getJSONArray("players").getJSONObject(8).get("hero_id"));
        int i = 128;
        //System.out.println((byte) i);
    }
}