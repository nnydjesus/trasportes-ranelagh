alter table Sancion drop foreign key FKD267FC85B0DD2B95
alter table Unidad drop foreign key FK974B44B7324151DA
alter table Unidad_remito drop foreign key FK29F22E52BEDDBAD5
alter table Unidad_remito drop foreign key FK29F22E52A96B3BF5
alter table adelanto drop foreign key FKB8BA4A726673BDC0
alter table adelanto drop foreign key FKB8BA4A72AB3C2C8E
alter table costo_empleado drop foreign key FKE85E5228B0DD2B95
alter table empleado drop foreign key FK471E00CBDE4689DF
alter table empleado_Ausencia drop foreign key FKEDA97527B0DD2B95
alter table empleado_Ausencia drop foreign key FKEDA97527C42140C8
alter table empleado_Sancion drop foreign key FK3A59A911B0DD2B95
alter table empleado_Sancion drop foreign key FK3A59A911CBD10851
alter table empleado_Vacacion drop foreign key FK359AB1A2B0DD2B95
alter table empleado_Vacacion drop foreign key FK359AB1A2AAD598C7
alter table empleado_adelanto drop foreign key FK4EFE2F66B0DD2B95
alter table empleado_adelanto drop foreign key FK4EFE2F66AFB3606
alter table remito drop foreign key FKC84AE1CAB0DD2B95
alter table remito drop foreign key FKC84AE1CAB77155AF
drop table if exists Ausencia
drop table if exists FormaDePago
drop table if exists IdGenerator
drop table if exists Sancion
drop table if exists Unidad
drop table if exists Unidad_remito
drop table if exists Vacacion
drop table if exists adelanto
drop table if exists costo_empleado
drop table if exists empleado
drop table if exists empleado_Ausencia
drop table if exists empleado_Sancion
drop table if exists empleado_Vacacion
drop table if exists empleado_adelanto
drop table if exists remito
drop table if exists usuario
create table Ausencia (id varchar(255) not null, stateVersion bigint, apellido varchar(255), aviso bit not null, costo double precision, fecha date, fechaReinicio date, legajo varchar(255), motivo varchar(255), nombre varchar(255), primary key (id))
create table FormaDePago (DTYPE varchar(31) not null, id varchar(255) not null, stateVersion bigint, banco integer, monto double precision, numeroCheque varchar(255), primary key (id))
create table IdGenerator (id varchar(255) not null, stateVersion bigint, current double precision, primary key (id))
create table Sancion (id varchar(255) not null, stateVersion bigint, cantDeDias integer not null, fecha date, empleado_id varchar(255), primary key (id))
create table Unidad (id varchar(255) not null, stateVersion bigint, anio integer, clase integer, fechaAlta date, fechaBaja date, marca varchar(255), modelo varchar(255), numeroChaziz varchar(255), numeroMotor varchar(255), tipo integer, unidadAsociada_id varchar(255), primary key (id))
create table Unidad_remito (Unidad_id varchar(255) not null, viajes_id varchar(255) not null, unique (viajes_id))
create table Vacacion (id varchar(255) not null, stateVersion bigint, apellido varchar(255), desde date, hasta date, legajo varchar(255), nombre varchar(255), primary key (id))
create table adelanto (id varchar(255) not null, stateVersion bigint, comentario varchar(255), fecha datetime, adelanto_id varchar(255), fornaDePago_id varchar(255), primary key (id))
create table costo_empleado (id varchar(255) not null, stateVersion bigint, costoTotal double precision, empleado_id varchar(255), primary key (id))
create table empleado (id varchar(255) not null, stateVersion bigint, apellido varchar(255), categoria integer, cuil varchar(255), calle varchar(255), codPostal integer, entreX varchar(255), entreY varchar(255), localidad varchar(255), numero integer, telefono integer, dni integer, fechaDeNacimiento date, art date, cnrt date, libretaSanitaria date, registro date, nombre varchar(255), numberCnrt varchar(255), propio bit not null, registro_conducir varchar(255), costoEmpleado_id varchar(255), primary key (id))
create table empleado_Ausencia (empleado_id varchar(255) not null, ausencias_id varchar(255) not null, unique (ausencias_id))
create table empleado_Sancion (empleado_id varchar(255) not null, sanciones_id varchar(255) not null, unique (sanciones_id))
create table empleado_Vacacion (empleado_id varchar(255) not null, vacaciones_id varchar(255) not null, unique (vacaciones_id))
create table empleado_adelanto (empleado_id varchar(255) not null, adelantos_id varchar(255) not null, unique (adelantos_id))
create table remito (id varchar(255) not null, stateVersion bigint, combustible double precision, costo double precision, destino varchar(255), fecha datetime, km integer, litros double precision, origen varchar(255), pago bit, peaje double precision, porcentage integer, empleado_id varchar(255), patente_id varchar(255), primary key (id))
create table usuario (id varchar(255) not null, stateVersion bigint, conectado bit not null, pass varchar(255), primary key (id))
alter table Sancion add index FKD267FC85B0DD2B95 (empleado_id), add constraint FKD267FC85B0DD2B95 foreign key (empleado_id) references empleado (id)
alter table Unidad add index FK974B44B7324151DA (unidadAsociada_id), add constraint FK974B44B7324151DA foreign key (unidadAsociada_id) references Unidad (id)
alter table Unidad_remito add index FK29F22E52BEDDBAD5 (Unidad_id), add constraint FK29F22E52BEDDBAD5 foreign key (Unidad_id) references Unidad (id)
alter table Unidad_remito add index FK29F22E52A96B3BF5 (viajes_id), add constraint FK29F22E52A96B3BF5 foreign key (viajes_id) references remito (id)
alter table adelanto add index FKB8BA4A726673BDC0 (fornaDePago_id), add constraint FKB8BA4A726673BDC0 foreign key (fornaDePago_id) references FormaDePago (id)
alter table adelanto add index FKB8BA4A72AB3C2C8E (adelanto_id), add constraint FKB8BA4A72AB3C2C8E foreign key (adelanto_id) references empleado (id)
alter table costo_empleado add index FKE85E5228B0DD2B95 (empleado_id), add constraint FKE85E5228B0DD2B95 foreign key (empleado_id) references empleado (id)
alter table empleado add index FK471E00CBDE4689DF (costoEmpleado_id), add constraint FK471E00CBDE4689DF foreign key (costoEmpleado_id) references costo_empleado (id)
alter table empleado_Ausencia add index FKEDA97527B0DD2B95 (empleado_id), add constraint FKEDA97527B0DD2B95 foreign key (empleado_id) references empleado (id)
alter table empleado_Ausencia add index FKEDA97527C42140C8 (ausencias_id), add constraint FKEDA97527C42140C8 foreign key (ausencias_id) references Ausencia (id)
alter table empleado_Sancion add index FK3A59A911B0DD2B95 (empleado_id), add constraint FK3A59A911B0DD2B95 foreign key (empleado_id) references empleado (id)
alter table empleado_Sancion add index FK3A59A911CBD10851 (sanciones_id), add constraint FK3A59A911CBD10851 foreign key (sanciones_id) references Sancion (id)
alter table empleado_Vacacion add index FK359AB1A2B0DD2B95 (empleado_id), add constraint FK359AB1A2B0DD2B95 foreign key (empleado_id) references empleado (id)
alter table empleado_Vacacion add index FK359AB1A2AAD598C7 (vacaciones_id), add constraint FK359AB1A2AAD598C7 foreign key (vacaciones_id) references Vacacion (id)
alter table empleado_adelanto add index FK4EFE2F66B0DD2B95 (empleado_id), add constraint FK4EFE2F66B0DD2B95 foreign key (empleado_id) references empleado (id)
alter table empleado_adelanto add index FK4EFE2F66AFB3606 (adelantos_id), add constraint FK4EFE2F66AFB3606 foreign key (adelantos_id) references adelanto (id)
alter table remito add index FKC84AE1CAB0DD2B95 (empleado_id), add constraint FKC84AE1CAB0DD2B95 foreign key (empleado_id) references empleado (id)
alter table remito add index FKC84AE1CAB77155AF (patente_id), add constraint FKC84AE1CAB77155AF foreign key (patente_id) references Unidad (id)
