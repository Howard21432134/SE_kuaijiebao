/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/7/6 14:47:26                            */
/*==============================================================*/
drop database kuaijiebao;
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

drop table if exists question_type;

drop table if exists type;

drop table if exists user;

/*==============================================================*/
/* Table: account                                               */
/*==============================================================*/
create table account
(
   username             char(15) not null,
   user_id              bigint(10),
   password             char(15) not null
);

/*==============================================================*/
/* Table: bankcard                                              */
/*==============================================================*/
create table bankcard
(
   card                 char(19) not null,
   user_id               bigint(10),
   primary key (card)
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
   sum                  int not null,
   expect_discharge_time date not null,
   content              text not null,
   whether_succeed      bool not null,
   succeed_time         datetime,
   whether_discharge    bool not null,
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
   product_id           bigint(10) not null,
   user_id               bigint(10) not null,
   num                  int not null,
   primary key (product_id, user_id)
);

/*==============================================================*/
/* Table: financial_product_deal_record                         */
/*==============================================================*/
create table financial_product_deal_record
(
   product_id           bigint(10) not null,
   user_id               bigint(10) not null,
   num                  int not null,
   time                 datetime not null,
   price                float not null,
   type                 int not null,  #1 buy 2sell 3cancel
   primary key (product_id, user_id)
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
create table user
(
   user_id              bigint(10) NOT NULL  AUTO_INCREMENT,
   nick_name            char(15) not null,
   name                 char(7) not null,
   identity             char(18) not null,
   job                  char(15),
   income               int,
   address             text,
   introduction         text,
   phone                char(11) not null,
   email                char(25),
   primary key (user_id)
);

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

