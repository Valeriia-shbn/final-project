package data;

import lombok.Data;
import lombok.Value;

import java.time.LocalDateTime;

@Data
public class OrderEntity {
    Long id;
    LocalDateTime created;
    Long creditId;
    Long paymentId;
}