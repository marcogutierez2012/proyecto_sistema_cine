create database bdcinebuenavista;

use bdcinebuenavista;

create table tb_rol
(
	id_rol int auto_increment primary key,
    des_rol varchar(255) not null
);

create table tb_usuario
(
	id_usuario int auto_increment primary key,
    dni_usuario char(8) not null,
    nom_usuario varchar(255) not null,
    ape_usuario varchar(255) not null,
    tel_usuario varchar(9) not null,
    email_usuario varchar(255) not null,
    contrasenia varchar(255) not null,
    id_rol int,
	foreign key (id_rol) references tb_rol(id_rol)
);

insert into tb_usuario(dni_usuario, nom_usuario, ape_usuario, tel_usuario, email_usuario, contrasenia, id_rol) 
values('74878420','Marco','Gutierrez','992478429','marcogutierrez@gmail.com','123456',1);

create table tb_tipocliente
(
	id_tipo_cliente int auto_increment primary key,
    des_tipo_cliente varchar(255) not null
);

create table tb_cliente
(
	id_cliente int auto_increment primary key,
    nom_cliente varchar(255) not null,
	ape_cliente varchar(255) not null,
	dni_cliente varchar(8) not null,
    tel_cliente varchar(9) not null,
    correo_cliente varchar(255) not null,
    id_tipo_cliente int,
    foreign key (id_tipo_cliente) references tb_tipocliente(id_tipo_cliente)
);

create table tb_genero
(
	id_genero int auto_increment primary key,
    des_genero varchar(255) not null
);

create table tb_pelicula
(
	id_pelicula int auto_increment primary key,
    tit_pelicula varchar(255) not null,
    dir_pelicula varchar(255) not null,
    dur_pelicula int not null,
    sin_pelicula varchar(999) not null,
    fecest_pelicula date not null,
    id_genero int,
    foreign key (id_genero) references tb_genero(id_genero)
);

create table tb_sala
(
	id_sala int auto_increment primary key,
    des_sala varchar(255) not null
);
/*TABLA BUTACA ELIMINADA*/

create table tb_tipofuncion
(
	id_tipo_funcion int auto_increment primary key,
    des_tipo_funcion varchar(20) not null
);

create table tb_funcion
(
	id_funcion int auto_increment primary key,
    id_pelicula int,
    id_sala int,
    pre_asiento decimal(7,2),
    nro_asientos int not null,
    id_tipo_funcion int,
    dia_funcion date not null,
    hora_funcion char(5) not null,
    foreign key (id_pelicula) references tb_pelicula(id_pelicula),
    foreign key (id_sala) references tb_sala(id_sala),
    foreign key (id_tipo_funcion) references tb_tipofuncion(id_tipo_funcion)
);

create table tb_tipodulceria
(
	id_tipo_dulceria int auto_increment primary key,
    des_tipo_dulceria varchar(255) not null
);

create table tb_dulceria
(
	id_dulceria int auto_increment primary key,
    des_dulceria varchar(255) not null,
    cant_dulceria int not null,
    pre_dulceria decimal(7,2) not null,
    id_tipo_dulceria int,
    foreign key (id_tipo_dulceria) references tb_tipodulceria(id_tipo_dulceria)
);

create table tb_boleta
(
	id_boleta int auto_increment primary key,
    id_usuario int,
    id_cliente int,
    id_funcion int,
    cant_asientos int not null,
    fec_boleta datetime not null,
    tot_boleta decimal(7,2) not null,
    foreign key (id_usuario) references tb_usuario(id_usuario),
    foreign key (id_cliente) references tb_cliente(id_cliente),
    foreign key (id_funcion) references tb_funcion(id_funcion)
);

create table tb_detalleboleta
(
	id_detalle int auto_increment primary key,
	id_boleta int,
    id_dulceria int,
    cantidad int not null,
    tot_dulceria decimal(7,2) not null,
    foreign key (id_boleta) references tb_boleta(id_boleta),
    foreign key (id_dulceria) references tb_dulceria(id_dulceria)
);

insert into tb_rol(des_rol) values ('Administrador'),('Empleado'),('Master'),('Usuario');
insert into tb_tipocliente(des_tipo_cliente) values ('Diamante'),('Oro'),('Plata'),('Bronce');
insert into tb_genero(des_genero) values ('Terror'),('Drama'),('Ciencia Ficcion'),('Animacion');
insert into tb_sala(des_sala) values ('Sala 1'),('Sala 2'),('Sala 3'),('Sala 4'),('Sala 5'),('Sala 6');
insert into tb_tipofuncion(des_tipo_funcion) values ('2D'),('3D');
insert into tb_tipodulceria(des_tipo_dulceria) values ('Snack'),('Gaseosa'),('Cancha'),('Combo');

