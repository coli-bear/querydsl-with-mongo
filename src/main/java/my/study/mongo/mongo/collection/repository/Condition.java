package my.study.mongo.mongo.collection.repository;

import com.querydsl.core.types.Predicate;

public interface Condition {
    Predicate values();
}
