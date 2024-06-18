create table tb_cliente (
	id bigint,
	nome varchar(50) not null,
	codigo varchar(50) not null,
	constraint pk_id_cliente primary key(id)
);

create sequence sq_cliente
start 1
increment 1
owned by tb_cliente.id;

select * from tb_cliente;

delete from tb_cliente;

create table tb_produto (
	id bigint,
	nome varchar(50) not null,
	codigo varchar(50) not null,
	constraint pk_id_produto primary key(id)
);

create sequence sq_produto
start 1
increment 1
owned by sq_produto.id;



