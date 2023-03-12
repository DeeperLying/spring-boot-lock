-- 创建存储过程
drop procedure if exists mypro;$$$
create procedure mypro(in a int,in b int,out sum int)
begin
    set sum = a+b;
end;$$$

call mypro(1,2,@s);$$$-- 调用存储过程
select @s;$$$-- 显示过程输出结果