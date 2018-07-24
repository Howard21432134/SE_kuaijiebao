package com.kuaijiebao.springrestvue.repository;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import com.kuaijiebao.springrestvue.domain.BankCard;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BankCardRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BankCardRepository repository;

    BankCard austin=new BankCard("1111-2222-3333-4444-5555",11L);

    @Before
    public void initialize() {
        entityManager.persistAndFlush(austin);
    }


    //
    //findByUserId
    @Test
    public void GivenValidBankCard_whenFindByUserId_thenReturnBankCard() {
        List<BankCard> found = repository.findByUserId(austin.getUserId());
        assertThat(found).hasSize(1).extracting(BankCard::getCardNum).contains(austin.getCardNum());
    }

    //
    //findByUserId
    @Test
    public void GivenInvalidBankCard_whenFindByUserId_thenReturnEmpty() {
        List<BankCard> found = repository.findByUserId(-99L);
        assertThat(found).isEmpty();
    }


    //
    //existsByCardNum
    @Test
    public void GivenValidBankCard_whenExistsByCardNum_thenReturnTrue() {
        boolean found = repository.existsByCardNum(austin.getCardNum());
        assertThat(found).isTrue();
    }

    //
    //existsByCardNum
    @Test
    public void GivenInValidBankCard_whenExistsByCardNum_thenReturnFalse() {
        boolean found = repository.existsByCardNum("DoesNotExist");
        assertThat(found).isFalse();
    }

    //
    //save
    @Test
    public void givenValidBankCard_whenSave_thenReturnBankCard() {
        BankCard jenny=new BankCard("1111-2222-3333-4444-6666",12L);
        BankCard fromDb = repository.save(jenny);
        assertThat(fromDb.getCardNum()).isEqualTo(jenny.getCardNum());
    }

    //
    //deleteByCardNum
    @Test
    public void givenValidBankCard_whenDeleteByCardNum_thenSuccess() {
        repository.deleteByCardNum(austin.getCardNum());
    }

}