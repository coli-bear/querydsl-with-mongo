package my.study.mongo.mongo.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ${@link Document} Annotation : Object를 MongoDB 에 Persistence
 * ${@link Document#collection} : Document 를 저장할 Collection (RDBMS 에서 Table 과 비슷하다)
 * - mongo db connection 정보는 <p>application.yml</p> 에서 확인 가능
 * - mongo db 는 ${@link my.study.mongo.mongo.MongoApplication} 에 ${@link org.springframework.data.mongodb.repository.config.EnableMongoRepositories} 를 활성화 하면 연동 가능
 */

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "bank_accounts")
public class BankAccountDetails {
    @Id
    private String accountId;
    private int balance;
    private LocalDateTime lastTransactionTimestamp;
    private List<FixedDepositDetails> fixedDeposits;

    @Override
    public String toString() {
        return "BankAccountDetails{" +
            "accountId='" + accountId + '\'' +
            ", balance=" + balance +
            ", lastTransactionTimestamp=" + lastTransactionTimestamp +
            ", fixedDeposits=" + fixedDeposits +
            '}';
    }
}
