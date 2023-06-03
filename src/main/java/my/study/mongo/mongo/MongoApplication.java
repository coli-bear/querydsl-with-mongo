package my.study.mongo.mongo;

import my.study.mongo.mongo.collection.BankAccountDetails;
import my.study.mongo.mongo.collection.FixedDepositDetails;
import my.study.mongo.mongo.collection.repository.BankAccountDetailsConditions;
import my.study.mongo.mongo.collection.repository.BankAccountDetailsRepository;
import my.study.mongo.mongo.collection.repository.BankAccountDynamicRepository;
import my.study.mongo.mongo.collection.repository.Condition;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static my.study.mongo.mongo.collection.Active.N;
import static my.study.mongo.mongo.collection.Active.Y;
import static my.study.mongo.mongo.collection.QFixedDepositDetails.fixedDepositDetails;
import static my.study.mongo.mongo.entity.QBank.bank;

@SpringBootApplication
public class MongoApplication {
    final BankAccountDetailsRepository repository;
    final BankAccountDynamicRepository dynamicRepository;

    public MongoApplication(BankAccountDetailsRepository repository, BankAccountDynamicRepository dynamicRepository) {
        this.repository = repository;
        this.dynamicRepository = dynamicRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MongoApplication.class, args);
    }

    @Bean
    ApplicationRunner dynamicRepositoryRunner() {
        return arguments -> {
            Condition condition = new BankAccountDetailsConditions().containsAccountId("11c2c");
            List<BankAccountDetails> byCondition = this.dynamicRepository.findByCondition(condition);
            System.out.println("byCondition = " + byCondition);
            System.out.println("QFixedDepositDetails.fixedDepositDetails = " + fixedDepositDetails);
            System.out.println("QBank.bank = " + bank);
        };
    }
//    @Bean
    ApplicationRunner mongoRepositoryRunner() {
        return (arguments) -> {
            System.out.println("[Start basic mongo repository]");
            repository.save(BankAccountDetails.builder()
                .accountId(UUID.randomUUID().toString())
                .lastTransactionTimestamp(LocalDateTime.now())
                .balance(10002)
                .fixedDeposits(Arrays.asList(
                    FixedDepositDetails.builder().active(Y).tenure(10).fdAmount(20).build(),
                    FixedDepositDetails.builder().active(Y).tenure(11).fdAmount(21).build(),
                    FixedDepositDetails.builder().active(Y).tenure(12).fdAmount(22).build(),
                    FixedDepositDetails.builder().active(N).tenure(13).fdAmount(23).build()
                ))
                .build());

            List<BankAccountDetails> byCustomQuery = repository.findByCustomQuery(10001);
            System.out.println("byCustomQuery = " + byCustomQuery);
            CompletableFuture<List<BankAccountDetails>> byBalanceGreaterThan = repository.findByBalanceGreaterThan(10001);
            List<String> accounts = byBalanceGreaterThan
                .thenApply(bankAccountDetails -> bankAccountDetails.stream()
                    .map(BankAccountDetails::getAccountId).collect(Collectors.toList()))
                .get();
            System.out.println("accounts = " + accounts);
            List<BankAccountDetails> byFixedDepositsTenureAndFixedDepositsFdAmount = repository.findByFixedDeposits_TenureAndFixedDeposits_FdAmount(10, 20);
            System.out.println("byFixedDepositsTenureAndFixedDepositsFdAmount = " + byFixedDepositsTenureAndFixedDepositsFdAmount);
            System.out.println("[End basic mongo repository]");
        };
    }
}

