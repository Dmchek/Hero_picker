package pick.secret.api;

import pick.arguments.Exceptions;

public class SteamApi {
    private final String key;

    public SteamApi(String key) {
        if (key == null) {
            throw new IllegalArgumentException(Exceptions.NULL_PARAMETER.getException());
        } else if (key.isEmpty()) {
            throw new IllegalArgumentException(Exceptions.INVALID_API_KEY.getException());
        } else {
            this.key = key;
        }
    }

    public String getKey() {

        return this.key;
    }
}
