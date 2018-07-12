package com.kuaijiebao.springrestvue.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.kuaijiebao.springrestvue.domain.User;

//@RepositoryRestResource(collectionResourceRel = "books", path = "books")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findOneById(Long id);
    public User findOneByName(String name);
    //@Query("select u from User u where u.phone=?1")
    //public User findOneByPhoneUsingQuery(String phone);
    public void deleteById(Long id);
    public User save(User user);

}