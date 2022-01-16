package io.phs.crud.model;


public class ResponseModel {

    private String message;
    private int status;

    public Object getData() {
        return data;
    }

    private Object data;

    public ResponseModel(String message, int status,Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return status;
    }
}
