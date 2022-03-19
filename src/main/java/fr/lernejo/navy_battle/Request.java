package fr.lernejo.navy_battle;

public final class Request {
    private final String id;
    private final String url;
    private final String message;

    public Request(String id, String url, String message) {
        this.id = id;
        this.url = url;
        this.message = message;
    }

    public Request() {
        this.id = "";
        this.url = "";
        this.message = "";
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getMessage() {
        return message;
    }
}
