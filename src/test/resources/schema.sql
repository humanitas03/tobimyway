-- TestInstance가 PER_CLASS로 잡힌 경우 H2DB에 테이블이 생성이 되어 있음에도 불구하고
-- 다시 테이블을 만들어 주기 때문에, DDL 에러 방지를 위한  DROP 스크립트 추가.
DROP TABLE IF EXISTS users;
CREATE TABLE users (
                       id varchar(100) primary key,
                       name varchar(20) not null,
                       password varchar(10) not null,
                       level tinyint not null,
                       login int not null,
                       recommend int not null,
                       email varchar(255) not null
)