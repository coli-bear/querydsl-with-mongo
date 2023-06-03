package my.study.mongo.mongo.collection.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import my.study.mongo.mongo.collection.QBankAccountDetails;
import org.springframework.util.ObjectUtils;

public class BankAccountDetailsConditions implements Condition {
    private static final QBankAccountDetails bankAccountDetails = QBankAccountDetails.bankAccountDetails;
    private BooleanBuilder builder = new BooleanBuilder();

    public BankAccountDetailsConditions containsAccountId(String accountId) {

        if (!ObjectUtils.isEmpty(accountId)) {

            builder.and(bankAccountDetails.accountId.contains(accountId));
        }
        return this;
    }

    public Predicate values() {
        return builder.getValue();
    }
}
