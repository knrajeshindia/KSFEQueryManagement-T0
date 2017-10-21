package com.ksfe.model;

        import java.io.Serializable;
        import java.util.Date;

/**
 * This is a Java Bean based class,used to hold the DB Details of CBA
 *
 * @author RNarendran
 * @since 1.0,
 */

@SuppressWarnings("serial")
public class JsonData implements Serializable{
    private String message;
    private String status;
    private String data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", status='" + status + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
