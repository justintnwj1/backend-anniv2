package com.example.demo.dto; // atau dto, sesuaikan

public class FlagfotoResponse {
    private String message;
    private Long id;
    private String value;

    public FlagfotoResponse(String message, Long id, String value) {
        this.message = message;
        this.id = id;
        this.value = value;
    }

    public String getMessage() {
        return message;
    }
    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
