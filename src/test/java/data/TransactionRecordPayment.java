package data;

import lombok.Data;

@Data
public class TransactionRecordPayment {
    String id;
    int amount;
    String created;
    String status;
    String transaction_id;
}
