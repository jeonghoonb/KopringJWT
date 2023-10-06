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
