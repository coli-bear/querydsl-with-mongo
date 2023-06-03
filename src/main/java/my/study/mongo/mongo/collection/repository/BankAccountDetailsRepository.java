package my.study.mongo.mongo.collection.repository;

import my.study.mongo.mongo.collection.BankAccountDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Mongo db 의 기본 repository
 * ${@link MongoRepository} 를 상속 받은 interface 로 spring-data-jpa 와 동일하게 사용 가능ㄴ
 * ${@link }
 */
public interface BankAccountDetailsRepository extends MongoRepository<BankAccountDetails, String> {
    List<BankAccountDetails> findByFixedDeposits_TenureAndFixedDeposits_FdAmount(int tenure, int fdAmount);

    /**
     * spring-data-jpa 에서 제공하는 것 처럼 spring-data-mongodb 또한 비동기 적으로 처리가 가능하다.
     * @param balance
     * @return
     */
    @Async
    CompletableFuture<List<BankAccountDetails>> findByBalanceGreaterThan(int balance);

    /**
     * ${@link Query} annotation 을 이용하여 작성하는 경우 key:value 형태 이므로 기존 jpa 에서 변수를 참조하는
     * <p>:value_name</p> 형태는 불가능 하다. 따라서 <p>?0</p> 형태로 매개변수의 위치를 찾아서 참조한다.
     * @param balance
     * @return
     */
    @Query("{balance:  {$lte : ?0}}")
    List<BankAccountDetails> findByCustomQuery(int balance);

}
