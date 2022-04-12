package pick.login;
import pick.arguments.*;

public class CallBack {
    private final String url;

    public CallBack(String url) {
        if (url == null) {
            throw new IllegalArgumentException(Exceptions.NULL_PARAMETER.getException());
        } else if (url.isEmpty()) {
            throw new IllegalArgumentException(Exceptions.INVALID_CALLBACK_URL.getException());
        } else {
            this.url = url;
        }
    }

    public String getUrl() {

        return this.url;
    }
}