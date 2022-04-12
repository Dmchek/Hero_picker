package pick.steam.method;

import org.json.JSONException;
import org.json.JSONObject;
import pick.JsonParse;
import pick.secret.api.SteamApi;

import java.io.IOException;

public class SteamMethod {

    public JSONObject getLogin(SteamApi steamApi, String id) throws IOException, JSONException { //Возвращает данные пользователя
        return JsonParse.readJsonFromUrl("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=" + steamApi.getKey() + "&steamids=" + id);
    }

    public JSONObject getHeroes(SteamApi steamApi) throws IOException, JSONException {
        return JsonParse.readJsonFromUrl("https://api.steampowered.com/IEconDOTA2_570/GetHeroes/v0001/?key=" + steamApi.getKey());
    }

    public JSONObject getMatchHistory(String key) throws IOException, JSONException {
        return JsonParse.readJsonFromUrl("https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key="+ key);
    }

    public JSONObject getMatchHistory(String key,String string) throws IOException, JSONException {
        return JsonParse.readJsonFromUrl("https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key="+ key + "&account_id=" + getAccountId(string) + "&matches_requested=10");
    }

    public int getAccountId(String string){
        long bufferId = Long.parseLong(string);
        return (int) bufferId;
    }

}
