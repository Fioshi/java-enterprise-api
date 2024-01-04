create table tb_endereco (

    id_endereco bigint not null auto_increment,
    logradouro varchar(50) not null,
    numero int not null,
    bairro varchar(80) not null,
    complemento varchar(25) not null,
    localidade varchar(50) not null,
    uf varchar(2) not null,
    cep varchar(13) not null,

    primary key (id_endereco)

);

ALTER TABLE tb_funcionario
ADD CONSTRAINT fk_tb_funcionario_endereco
FOREIGN KEY (id_endereco)
REFERENCES tb_endereco (id_endereco);
