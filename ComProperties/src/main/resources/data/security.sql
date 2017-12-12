create table role(
    idrole serial primary key,
    nama varchar not null
);

insert into role(nama) values('ROLE_ADMIN');
insert into role(nama) values('ROLE_USER');

create table pengguna(
    idpengguna serial primary key,
    nama varchar(255) not null,
    idrole int not null,
    actives int not null,
    username varchar(255) not null,
    password varchar(255) not null,
    tanggal_lahir date not null,
    alamat varchar(255) not null,

    CONSTRAINT fk_admin_role_idrole FOREIGN KEY (idrole)
    REFERENCES role(idrole)
    match simple on update no action on delete no action
);

insert into pengguna(nama, idrole, actives, username, password, tanggal_lahir, alamat)
values ('aqsam', 1, 1, 'aqsam', 'root123',
'1996-03-19', 'cilandak');

insert into pengguna(nama, idrole, actives, username, password, tanggal_lahir, alamat)
values ('joan', 1, 1, 'joan', 'root123',
'1996-03-19', 'cilandak');

insert into pengguna(nama, idrole, actives, username, password, tanggal_lahir, alamat)
values ('sabrina', 1, 1, 'sabrina', 'root123',
'1996-03-19', 'cilandak');

insert into pengguna(nama, idrole, actives, username, password, tanggal_lahir, alamat)
values ('Sita', 2, 1, 'sita', 'root123',
'1996-03-19', 'ciputat');

insert into pengguna(nama, idrole, actives, username, password, tanggal_lahir, alamat)
values ('Dena', 1, 1, 'dena', 'root123',
'1996-03-19', 'ciputat');

insert into pengguna(nama, idrole, actives, username, password, tanggal_lahir, alamat)
values ('Alif', 1, 1, 'alif', 'root123',
'1996-03-19', 'ciputat');

create table pengguna_role(

    idrole int not null,
    idpengguna int not null,
    primary key(idrole, idpengguna),

    CONSTRAINT fk_role_idrole FOREIGN KEY(idrole)
    REFERENCES role(idrole)
    match simple on update no action on delete no action,
    CONSTRAINT fk_pengguna_idpengguna FOREIGN KEY(idpengguna)
    REFERENCES pengguna(idpengguna)
    match simple on update no action on delete no action

);

insert into pengguna_role(idrole, idpengguna) values(1, 1);
insert into pengguna_role(idrole, idpengguna) values(2, 2);
insert into pengguna_role(idrole, idpengguna) values(2, 3);