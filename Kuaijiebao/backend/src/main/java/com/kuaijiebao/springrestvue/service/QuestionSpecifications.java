package com.kuaijiebao.springrestvue.service;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.JoinType;

import java.util.List;

import com.kuaijiebao.springrestvue.domain.Question;

@Service
public class QuestionSpecifications {


    public static Specification<Question> titleContains(String title) {
        return StringUtils.isEmpty(title) ? null : (root, query, cb) -> {
            return cb.like(root.get("title"), "%" + title + "%");
        };
    }

    public static Specification<Question> contentContains(String keyword) {
        return StringUtils.isEmpty(keyword) ? null : (root, query, cb) -> {
            return cb.like(root.get("content"), "%" + keyword + "%");
        };
    }

    public static Specification<Question> answerContains(String keyword) {
        return StringUtils.isEmpty(keyword) ? null : (root, query, cb) -> {
            return cb.like(root.get("answer"), "%" + keyword + "%");
        };
    }

/*
    //not leaving out version
    public static Specification<User> exampleqeury(final List<Long> groupIds){
        return new Specification<User>() {
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder){
                final Path<Group> group = root.<Group> get("group");
                return group.in(groupIds);
            }
        };
    }
*/
    /*

    // 価格（最低価格）以上のもの
    public static Specification<Volume> priceGreaterThanEqual(Float priceFrom) {
        return StringUtils.isEmpty(priceFrom) ? null : (root, query, cb) -> {
            return cb.greaterThanOrEqualTo(root.get("price"), priceFrom);
        };
    }

    // 価格（最高価格）以下のもの
    public static Specification<Volume> priceLessThanEqual(Float priceTo) {
        return StringUtils.isEmpty(priceTo) ? null : (root, query, cb) -> {
            return cb.lessThanOrEqualTo(root.get("price"), priceTo);
        };
    }

    */
}
