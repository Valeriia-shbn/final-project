package data;

import lombok.Data;


import java.time.LocalDateTime;

@Data
public class TransactionRecordCredit {
    String id;
    String bankId;
    String created;
    String status;
}
