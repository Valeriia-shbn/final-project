package data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderEntity {
    Long id;
    LocalDateTime created;
    Long creditId;
    Long paymentId;
}