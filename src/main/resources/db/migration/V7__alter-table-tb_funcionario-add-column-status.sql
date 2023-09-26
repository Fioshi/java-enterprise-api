alter table tb_funcionario
add column status tinyint;

update tb_funcionario
set status = 1;