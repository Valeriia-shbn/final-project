package data;

import lombok.Data;

@Data
public class TransactionRecordCredit {
    String id;
    String bankId;
    String created;
    String status;
}
