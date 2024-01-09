create table tb_tarefa(
    id_tarefa bigint not null auto_increment,
    nome varchar(50) not null,
    prioridade varchar(5) not null,
    estado varchar(9) not null,
    orcamento double not null,
    descricao varchar(255),

    primary key (id_tarefa)
);


CREATE TABLE tb_funcionario_tarefa (
    id_funcionario BIGINT,
    id_tarefa BIGINT,
    PRIMARY KEY (id_funcionario, id_tarefa),
    FOREIGN KEY (id_funcionario) REFERENCES tb_funcionario (id_funcionario),
    FOREIGN KEY (id_tarefa) REFERENCES tb_tarefa (id_tarefa)
);