create table tb_reuniao(

    id bigint not null auto_increment,
    id_funcionario bigint not null,
    horario datetime not null,

    primary key(id),
    constraint fk_tb_reuniao_id_funcionario foreign key(id_funcionario) references tb_funcionario(id)
);