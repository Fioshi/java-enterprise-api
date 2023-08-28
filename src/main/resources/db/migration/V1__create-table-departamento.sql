create table tb_departamento (

    id_departamento bigint not null auto_increment,
    nome varchar(25) not null,

    primary key(id_departamento)

);

create table tb_funcionario (

    id_funcionario bigint not null auto_increment,
    id_departamento bigint not null,
    nome varchar(25) not null,
    sobrenome varchar(25) not null,
    cpf varchar(13) not null,

    primary key (id_funcionario),
    constraint fk_tb_funcionario_id_departamento foreign key(id_departamento) references tb_departamento(id_departamento)

);