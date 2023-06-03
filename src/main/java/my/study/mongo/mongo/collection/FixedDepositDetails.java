package my.study.mongo.mongo.collection;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

/**
 * ${@link BankAccountDetails} 의 내장 Document
 * ${@link org.springframework.data.mongodb.core.mapping.Document} 를 설정하지 않는다.
 * <p>
 * 내장 Document 의 특징
 * - ${@link Id} Annotation 을 설정하지만 실제 내장 Document 에는 _id 필드가 설정되지 않음
 * - 그래서 ${@link ObjectId#get} 를 이용하여 명시적으로 설정 할 수 있음
 */
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FixedDepositDetails {
    @Id
    @Builder.Default
    private ObjectId fixedDepositId = ObjectId.get();
    private int fdAmount;
    private int tenure;
    private Active active;

    @Override
    public String toString() {
        return "FixedDepositDetails{" +
            "fixedDepositId=" + fixedDepositId +
            ", fdAmount=" + fdAmount +
            ", tenure=" + tenure +
            ", active=" + active +
            '}';
    }
}
