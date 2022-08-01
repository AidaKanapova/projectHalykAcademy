package kz.halykacademy.bookstore.dto;

import java.util.List;

public class SaveOrderDTO {

    private long orderId;
    private long userId;
    private String status;

    public  SaveOrderDTO(){super();}

    public SaveOrderDTO(long orderId, long userId, String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.status = status;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getUserId() {
        return userId;
    }

    public String getStatus() {
        return status;
    }
}
