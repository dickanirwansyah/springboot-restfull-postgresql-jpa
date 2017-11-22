create table kategori(
   idkategori serial primary key,
   nama varchar(255) not null,
   deskripsi varchar(255) not null
);

create table product(
    idproduct serial primary key,
    kodeproduct varchar(255) not null,
    nama varchar(255) not null,
    deskripsi varchar(255) not null,
    actives boolean not null,
    tanggal date not null,
    idkategori integer not null,

    CONSTRAINT fk_product_kategori_idkategori FOREIGN KEY(idkategori)
    REFERENCES kategori (idkategori)
    match simple on update no action on delete no action
);

insert into product(kodeproduct, nama, deskripsi, actives, tanggal, idkategori)
values('p001', 'lifebuoy', 'sabun batang', true, '2020-12-01', 1);
insert into product(kodeproduct, nama, deskripsi, actives, tanggal, idkategori)
values('p002', 'Nuvo', 'sabun batang', true, '2018-12-01', 1);
insert into product(kodeproduct, nama, deskripsi, actives, tanggal, idkategori)
values('p003', 'Bimoli', 'Minyak Goreng', true, '2020-12-01', 3);
insert into product(kodeproduct, nama, deskripsi, actives, tanggal, idkategori)
values('p004', 'Ciki Chuba', 'Chicki', true, '2090-12-01', 1);
insert into product(kodeproduct, nama, deskripsi, actives, tanggal, idkategori)
values('p005', 'lifebuoy', 'sabun batang', true, '2020-01-01', 1);
insert into product(kodeproduct, nama, deskripsi, actives, tanggal, idkategori)
values('p006', 'lifebuoy', 'sabun batang', true, '2020-09-01', 1);
insert into product(kodeproduct, nama, deskripsi, actives, tanggal, idkategori)
values('p007', 'Calpico', 'susu soda', true, '2020-11-12', 5);
insert into product(kodeproduct, nama, deskripsi, actives, tanggal, idkategori)
values('p008', 'Geary Chocolatos', 'sabun batang', true, '2020-03-01', 4);



insert into kategori(nama, deskripsi) values('Sabun', 'Sabun liquid');
insert into kategori(nama, deskripsi) values('Sampo', 'Sampo rambut');
insert into kategori(nama, deskripsi) values('Minyak', 'Minyak Goreng');
insert into kategori(nama, deskripsi) values('Makanan', 'Makanan Ringan');
insert into kategori(nama, deskripsi) values('Minuman', 'Minuman');
insert into kategori(nama, deskripsi) values('Snack', 'Snack Ciki');
insert into kategori(nama, deskripsi) values('Peralatan Sekolah', 'Perlengkapan');