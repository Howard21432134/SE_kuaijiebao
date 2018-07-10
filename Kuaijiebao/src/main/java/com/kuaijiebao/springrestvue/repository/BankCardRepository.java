package com.kuaijiebao.springrestvue.repository;

import com.kuaijiebao.springrestvue.domain.BankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankCardRepository extends JpaRepository<BankCard, String> {
    public List<BankCard> findOnesById(Long userId);
    public BankCard save(BankCard card);
    public void deleteByCardNum(String cardNum);
}
