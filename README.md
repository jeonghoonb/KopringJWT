# KopringJWT
Kotlin + Springboot + SpringSecurity + JWT

# DB Setting
```
show databases;

create database kopringjwt;

select user from mysql.user;
create user toy@localhost identified by 'password12#$';
show grants for toy@localhost;

grant all on kopringjwt.* to toy@localhost with grant option;

flush privileges;
```

# 구조
* common: 공통 기능
  * annotation: 사용자 생성 어노테이션
  * dto: 공통 DTO
  * exception: exception 처리
  * status: 공통 Status
* member: 회원 정보 관련 기능
  * controller: Request를 받을 End Point
  * dto: 회원 정보 관련 DTO
  * entity: 회원 정보 관련 Entity
  * repository: 회원 정보 관련 Repository
  * service: 비즈니스 로직

# 기능 명세

# Dependency
```
Spring Web
Spring Data JPA
Stirng Boot DevTools
MySQL Driver
Spring Boot Starter Validation
```