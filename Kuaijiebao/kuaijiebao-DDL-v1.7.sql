/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/6 14:47:26                            */
/*==============================================================*/
drop database if exists kuaijiebao;
create database kuaijiebao;
use kuaijiebao;

drop table if exists account;

drop table if exists bankcard;

drop table if exists credit_record;

drop table if exists debt;

drop table if exists financial_product;

drop table if exists financial_product_deal;

drop table if exists financial_product_deal_record;

drop table if exists question;

drop table if exists personal_credit;

drop table if exists question_type;

drop table if exists user_pending_validation;

drop table if exists type;

drop table if exists user;

/*==============================================================*/
/* Table: user_pending_validation                                  */
/*==============================================================*/
create table user_pending_validation
(
   pending_id                   bigint(10) not null AUTO_INCREMENT,
   username             char(15) not null,#needed when signup
   code              char(10),#needed when signup
   elem char(20), #BANK_CARD PHONE_NUMBER EMAIL_ADDRESS
   item					char(30),
   primary key(pending_id)

);

/*==============================================================*/
/* Table: account                                               */
/*==============================================================*/
create table account
(
   account_id                   bigint(10) not null AUTO_INCREMENT,
   username             char(15) not null,#needed when signup
   user_id              bigint(10),#needed when signup
   password             char(60) binary  not null,#passwordEncoder length 60 binary needed when signup
   roles                char(20),
   primary key(account_id)

);

create table user
(
   user_id              bigint(10) NOT NULL  AUTO_INCREMENT,
   nickname            char(14) not null,#needed when signup
   name                 char(14),
   identity             char(50) not null,#needed when signup
   job                  char(50),
   income               int,
   address             text,
   introduction         text,
   phone                char(50),
   email                char(50),
   primary key (user_id)
);

create table personal_credit
(	
   pc_id bigint(10) NOT NULL  AUTO_INCREMENT,
   user_id              bigint(10),
   credit_limit			float,
   primary key(pc_id)
);

/*==============================================================*/
/* Table: bankcard                                              */
/*==============================================================*/
#One user can have many bankcards
create table bankcard
(
   bankcard_id  bigint(10) NOT NULL  AUTO_INCREMENT,
   card                 char(19) not null,
   user_id               bigint(10),
   primary key (bankcard_id)
);

/*==============================================================*/
/* Table: credit_record                                         */
/*==============================================================*/
create table credit_record
(
   credit_record_id     bigint(10) not null AUTO_INCREMENT,
   user_id              bigint(10),
   time                 datetime not null,
   old_sum              float not null,
   new_sum              float not null,
   type 						bool not null,
   primary key (credit_record_id)
);

/*==============================================================*/
/* Table: debt                                                  */
/*==============================================================*/
create table debt
(
   debt_id              bigint(10) not null AUTO_INCREMENT,
   user_id               bigint(10),
   owner_id             bigint(10),
   sum                  int,
   valid_time			date,
   expect_discharge_time date,
   content              text,
   whether_succeed      bool,
   succeed_time         datetime,
   whether_discharge    bool,
   discharge_time       datetime,
   type                 bool,
   rate                 float,
   state                int,
   primary key (debt_id)
);

/*==============================================================*/
/* Table: financial_product                                     */
/*==============================================================*/
create table financial_product
(
   product_id           bigint(10) not null AUTO_INCREMENT,
   sum                  int not null,
   product_name         char(15) not null,
   price                float not null,
   productor            text not null,
   primary key (product_id)
);

/*==============================================================*/
/* Table: financial_product_deal                                */
/*==============================================================*/
create table financial_product_deal
(
	deal_id        bigint(10) not null AUTO_INCREMENT,
   product_id           bigint(10) not null,
   user_id               bigint(10) not null,
   num                  int not null,
   starttime                 datetime,
   endtime                 datetime,
   type               boolean,
   primary key (deal_id)
);

/*==============================================================*/
/* Table: financial_product_deal_record                         */
/*==============================================================*/
create table financial_product_deal_record
(
   dealrecord_id           bigint(10) not null AUTO_INCREMENT,
   product_id           bigint(10) not null,
   user_id               bigint(10) not null,
   deal_id				  bigint(10)not null,
   time                 datetime not null,
   type                 int not null,  #1 buy 2sell 3cancel
   primary key (dealrecord_id)
);

/*==============================================================*/
/* Table: question                                              */
/*==============================================================*/
create table question
(
   question_id          bigint(10) not null AUTO_INCREMENT,
   title                text not null,
   answer               text not null,
   content              text not null,
   primary key (question_id)
);

/*==============================================================*/
/* Table: question_type                                         */
/*==============================================================*/
create table question_type
(
   question_id          bigint(10) not null,
   type_id              bigint(10) not null,
   primary key (question_id, type_id)
);

/*==============================================================*/
/* Table: type                                                  */
/*==============================================================*/
create table type
(
   type_id              bigint(10) not null AUTO_INCREMENT,
   type_name            char(10) not null,
   primary key (type_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/


alter table account add constraint FK_login foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table bankcard add constraint FK_having_bankcard foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table credit_record add constraint FK_Relationship_5 foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table debt add constraint FK_debt foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table debt add constraint FK_own foreign key (owner_id)
      references user (user_id) on delete restrict on update restrict;

alter table financial_product_deal add constraint FK_financial_product_deal foreign key (product_id)
      references financial_product (product_id) on delete restrict on update restrict;

alter table financial_product_deal add constraint FK_financial_product_deal2 foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table financial_product_deal_record add constraint FK_financial_product_deal_record foreign key (product_id)
      references financial_product (product_id) on delete restrict on update restrict;

alter table financial_product_deal_record add constraint FK_financial_product_deal_record2 foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table question_type add constraint FK_question_type foreign key (question_id)
      references question (question_id) on delete restrict on update restrict;

alter table question_type add constraint FK_question_type2 foreign key (type_id)
      references type (type_id) on delete restrict on update restrict;


#alter table financial_product_deal_record add constraint FK_question_type3 foreign key (deal_id)
 #     references  financial_product_deal (deal_id) on delete restrict on update restrict;
