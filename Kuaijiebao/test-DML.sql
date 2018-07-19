#INSERT INTO kuaijiebao.account (user_id, username, password) VALUES (1,"aikawa",  "password");
#INSERT INTO kuaijiebao.bankcard (card,user_id) VALUES ("12345678999999"," 1");
#INSERT INTO kuaijiebao.question (title,answer,content,question_id) VALUES ("loan"," depends on income and your job.","Imcontent",1);
#INSERT INTO kuaijiebao.question (title,answer,content,question_id) VALUES ("how to lend money"," depends on income and your job.","can anybody tell me how i can lend money iusing this app??",2);
#select * from kuaijiebao.account;
#select * from kuaijiebao.user;
#select * from kuaijiebao.bankcard;
#DELETE FROM kuaijiebao.question WHERE title = "loan";
#select * from kuaijiebao.user;

#INSERT INTO kuaijiebao.user(user_id,nickname,identity)value(1,"john","student");
#INSERT INTO kuaijiebao.financial_product(product_id,sum,product_name,price,productor) VALUES (1,1,1,100,"john");
#INSERT INTO kuaijiebao. financial_product_deal_record(dealrecord_id,product_id,user_id,deal_id,num,time,price,type) VALUES (1,1,1,1,1,"2018/7/7",200,1);
#select * from kuaijiebao.financial_product_deal_record;
#INSERT INTO kuaijiebao.debt(debt_id,user_id,owner_id,sum,expect_discharge_time,content,whether_succeed,whether_discharge,type,rate,state) 
#VALUES (1,1,1,100,"2018/7/7","imcontent",true,"2018/3/3",true,1,1);
#select *from kuaijiebao.debt;
#select * from kuaijiebao.financial_product_deal_record;

#delete from kuaijiebao. financial_product_deal_record where product_id=1;