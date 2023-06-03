package my.study.mongo.mongo.collection.repository;

import my.study.mongo.mongo.collection.BankAccountDetails;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static my.study.mongo.mongo.collection.QBankAccountDetails.bankAccountDetails;

@Repository
public class BankAccountDynamicRepositoryImpl extends QuerydslRepositorySupport implements BankAccountDynamicRepository {
    /**
     * Creates a new {@link QuerydslRepositorySupport} for the given {@link MongoOperations}.
     *
     * @param operations must not be {@literal null}.
     */
    public BankAccountDynamicRepositoryImpl(MongoOperations operations) {
        super(operations);
    }

    @Override
    public List<BankAccountDetails> findByCondition(Condition condition) {
        return from(bankAccountDetails).where(condition.values()).fetch();
    }
}
