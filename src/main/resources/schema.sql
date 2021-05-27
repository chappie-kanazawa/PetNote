CREATE TABLE account (
  user_id int(5) NOT NULL AUTO_INCREMENT,
  user_name varchar(20) NOT NULL,
  pass varchar(20) NOT NULL,
  icon varchar(255) DEFAULT NULL,
  intro varchar(140) DEFAULT NULL,
  PRIMARY KEY (user_id)
);

CREATE TABLE pet (
  pet_id int(5) NOT NULL AUTO_INCREMENT,
  pet_name varchar(20) NOT NULL,
  kind varchar(20) DEFAULT NULL,
  gender int(1) DEFAULT NULL,
  pet_icon varchar(255) DEFAULT NULL,
  user_id int(5) NOT NULL,
  PRIMARY KEY (pet_id)
);

CREATE TABLE record (
  rec_id int(5) NOT NULL AUTO_INCREMENT,
  comment varchar(140) NOT NULL,
  rec_pic varchar(255) DEFAULT NULL,
  rec_date datetime NOT NULL,
  pet_id int(5) NOT NULL,
  PRIMARY KEY (rec_id)
) ;

CREATE TABLE players (
  id int(5) NOT NULL,
  name varchar(140),
  number int(5), 
  position varchar(140),
  PRIMARY KEY (id)
);

/*
CREATE TABLE picture (
  pic_id SERIAL PRIMARY KEY,
  pic_name VARCHAR,
  file_type VARCHAR,
  pic_data BYTEA -- ポイント1: 保存したい写真のデータ型をBYTEA(byte array)にする
);


CREATE TABLE r_p_relation (
  rec_id int(5) NOT NULL,
  pet_id int(5) NOT NULL,
  PRIMARY KEY (rec_id)
);

CREATE TABLE p_a_relation (
  pet_id int(5) NOT NULL,
  user_id int(5) NOT NULL,
  PRIMARY KEY (pet_id)
);
*/



