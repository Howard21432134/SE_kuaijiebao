package com.kuaijiebao.springrestvue;


import org.springframework.beans.factory.annotation.Autowired;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import com.kuaijiebao.springrestvue.repository.UserRepository;
import com.kuaijiebao.springrestvue.domain.User;

@Component
public class DatabaseLoader implements CommandLineRunner {
    protected final Log logger = LogFactory.getLog(getClass());



    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("KuaiJieBao System Started.");

        //userRepository.save(new User("sato","satoshi","sophomore","student",0,"SJTU", "Hello","11122223333","example@qq.com"));




    }



}


//http://books.google.com/books/content?id=oqmBpk1EzvAC&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE711QBNtaIUSwWq78rKS255V9UdZGGvCLPHFe71mqh1MU9KhM2TmFFpSYInEoAmDyH43DB7HlgMn2RQD5Ui6zQNPIyNcyEAdDJmGcaBifZjypTM97qrD1I0Mcvq_U2_QW8VW_23J&source=gbs_api