package pick.arguments;

public enum SteamUri {
    SERVICE_STUB("http://api.steampowered.com/"),
    STEAM_OPENID("http://steamcommunity.com/openid");

    private final String steamUri;

    private SteamUri(String steamUri) {

        this.steamUri = steamUri;
    }

    public String getSteamUri() {

        return this.steamUri;
    }
}