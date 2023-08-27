-- 创建存储过程
drop procedure if exists mypro;$$$
create procedure mypro(in a int,in b int,out sum int)
begin
    set sum = a+b;
end;$$$

call mypro(1,2,@s);$$$-- 调用存储过程
select @s;$$$-- 显示过程输出结果

drop procedure if exists myLee1;$$$
create procedure myLee1(IN one INT, in two INT ,out sum int)
begin
    set sum = 2+1;
end;$$$

drop procedure if exists myLee2;$$$
create procedure myLee2()
BEGIN
    DECLARE  num  INT  DEFAULT 10 ;
    DECLARE two INT DEFAULT 100;
    CALL myLee1(num, two, @b);
    SELECT @b;
END;$$$

CALL myLee2();$$$

drop procedure if exists myLee3;$$$
create procedure myLee3()
begin
    SELECT id FROM user_info WHERE name1 = 2;
end;$$$

CALL myLee3();$$$

drop procedure if exists myLee4;$$$
create procedure myLee4(IN num INT)
begin
    IF num < 0 THEN
        SELECT '< 0--';
    ELSEIF num > 0 THEN
        SELECT '> 0--';
    ELSE
        SELECT '0';
    END IF;
end;$$$

CALL myLee4(0);$$$

# 进阶条件查询

drop procedure if exists myLee5;$$$
drop procedure if exists myLee5;$$$
create procedure myLee5()
begin
    IF(SELECT EXISTS(SELECT * FROM user_info WHERE id = 2)) THEN
        SELECT 'true';
    ELSE
        SELECT 'FALSE';
    END IF;
end;$$$

CALL myLee5();$$$


drop procedure if exists myLee6;$$$
create procedure myLee6()
begin
    IF(select 1 from information_schema.columns  where table_schema='white_jotter' and table_name='user_info' and column_name ='name3') THEN
        SELECT 'save';
    ELSE
        alter table user_info add column name3 varchar(20) not null;
    END IF;
end;$$$

CALL myLee6();$$$

drop procedure if exists myLee7;$$$
create procedure myLee7()
begin
    IF (NOT EXISTS (SELECT * from user_info WHERE id < 2)) THEN
        SELECT '1';
    ELSE
        SELECT '2';
    END IF;
end;$$$

CALL myLee7();$$$

drop procedure if exists myLee8;$$$
create procedure myLee8()
begin
    IF( EXISTS (SELECT * FROM information_schema.`TABLES` as s WHERE table_schema="white_jotter" AND table_name = "user_info" )) THEN
        SELECT 'you';
    else
        SELECT 'no';
    END IF;
end;$$$

CALL myLee8();$$$

drop procedure if exists myLee9;$$$
create procedure myLee9()
begin
    IF(select 1 from information_schema.columns  where table_schema='white_jotter' and table_name='article' and column_name ='user_id') THEN
        SELECT 'save';
    ELSE
        ALTER TABLE article ADD COLUMN user_id int(11) NOT NULL COMMENT "user";
        ALTER TABLE article ADD COLUMN text_html text NOT NULL COMMENT "内容携带html";
        ALTER TABLE article ADD COLUMN banner varchar(300) DEFAULT NULL COMMENT "文章封面";
        ALTER  TABLE article MODIFY title varchar(300) NOT NULL COMMENT "文章标题";
        ALTER  TABLE article MODIFY introduction varchar(600) NOT NULL COMMENT "文章简介";
    END IF;
end;$$$
CALL myLee9();$$$

drop procedure if exists myLee10;$$$
create procedure myLee10()
begin
    IF(select 1 from information_schema.columns  where table_schema='white_jotter' and table_name='user' and column_name ='firebase_token') THEN
        SELECT 'save';
    ELSE
        ALTER TABLE user ADD COLUMN firebase_token varchar(255) NOT NULL COMMENT "firebase_token";
    END IF;
end;$$$
CALL myLee10();$$$
