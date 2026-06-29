# JDBC 예제 준비

## Dependencies
```groovy
implementation 'com.mysql:mysql-connector-j:9.7.0'
implementation 'org.postgresql:postgresql:42.7.11'
implementation 'org.slf4j:slf4j-api:2.0.18'
implementation 'ch.qos.logback:logback-classic:1.5.34'
compileOnly 'org.projectlombok:lombok:1.18.46'
annotationProcessor 'org.projectlombok:lombok:1.18.46'
```

## MySQL Database Schema
```sql
CREATE TABLE IF NOT EXISTS member (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    email VARCHAR(256) NOT NULL UNIQUE,
    age INTEGER
);
```

## PostgreSQL Database Schema
```sql
CREATE TABLE IF NOT EXISTS member (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(128) NOT NULL,
    email VARCHAR(256) NOT NULL UNIQUE,
    age INTEGER
);
```

## Initial data
```sql
INSERT INTO member(name, email, age) VALUES('윤서준', 'SeojunYoon@hanbit.co.kr', 10);
INSERT INTO member(name, email, age) VALUES('윤광철', 'KwangcheolYoon@hanbit.co.kr', 43);
INSERT INTO member(name, email, age) VALUES('공미영', 'MiyeongKong@hanbit.co.kr', 23);
INSERT INTO member(name, email, age) VALUES('김도윤', 'DoyunKim@hanbit.co.kr', 10);
```