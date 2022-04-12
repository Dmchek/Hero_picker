package pick.arguments;

public enum Exceptions {
    NULL_PARAMETER,
    TOO_MANY_IDS,
    INVALID_API_KEY,
    INVALID_CALLBACK_URL;

    private Exceptions() {
    }

    public String getException() {

        return this.name().toLowerCase().replaceAll("_", " ");
    }
}

