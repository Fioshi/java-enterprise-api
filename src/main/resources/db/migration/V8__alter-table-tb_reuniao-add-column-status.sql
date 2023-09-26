alter table tb_reuniao
add column status tinyint;

update tb_reuniao
set status = 1;