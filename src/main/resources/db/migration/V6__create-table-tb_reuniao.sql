create table tb_reuniao(

    id bigint not null auto_increment,
    horario datetime not null,
    tipo varchar(20) not null,

    primary key(id)
);