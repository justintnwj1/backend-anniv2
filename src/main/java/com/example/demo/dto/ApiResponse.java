package com.example.demo.dto; // atau dto, sesuaikan

public class ApiResponse {
    private String message;
    private Long id;

    public ApiResponse(String message, Long id) {
        this.message = message;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public Long getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
