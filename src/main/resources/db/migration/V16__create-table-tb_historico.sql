create table tb_historico(

    id_historico bigint auto_increment,
    id_tarefa bigint,
    estado varchar(10) not null,
    descricao varchar(255),
    data Date not null,

    primary key (id_historico),
    FOREIGN KEY (id_tarefa) REFERENCES tb_tarefa (id_tarefa)

);