package com.example.demo.dto; // atau sesuaikan dengan struktur proyekmu

public class UploadCloudinaryResponse {
    private String message;
    private String url;

    public UploadCloudinaryResponse(String message, String url) {
        this.message = message;
        this.url = url;
    }

    public String getMessage() {
        return message;
    }

    public String getUrl() {
        return url;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
