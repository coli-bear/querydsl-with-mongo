package my.study.mongo.mongo.collection.repository;

import my.study.mongo.mongo.collection.BankAccountDetails;

import java.util.List;

public interface BankAccountDynamicRepository {
    List<BankAccountDetails> findByCondition(Condition condition);
}
