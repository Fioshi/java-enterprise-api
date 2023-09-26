create table tb_email (

    id bigint not null auto_increment,
    ownerRef varchar(30) not null,
    emailFrom varchar(50) not null,
    subject varchar(255) not null,
    text varchar(255) not null,
    sendDateEmail Date not null,
    statusEmail varchar(6) not null,

    primary key (id)

);