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



create table jenis(
    idjenis serial primary key,
    kodejenis varchar(255) not null,
    deskripsi varchar(255) not null
);

insert into jenis(kodejenis, deskripsi) values('j001', 'Furniture');
insert into jenis(kodejenis, deskripsi) values('j002', 'Pakaian');
insert into jenis(kodejenis, deskripsi) values('j003', 'Parfum');
insert into jenis(kodejenis, deskripsi) values('j004', 'Celana');

create table barang(
    idbarang serial primary key,
    kodebarang varchar(255) not null,
    nama varchar(255) not null,
    quantity int not null,
    idjenis int not null,
    price int not null,

    CONSTRAINT fk_barang_jenis_idjenis FOREIGN KEY(idjenis)
    REFERENCES jenis(idjenis)
    match simple on update no action on delete no action
);


insert into barang(kodebarang, nama, quantity, idjenis, price)
values('b001', 'Beleza', 80, 3, 100000);
insert into barang(kodebarang, nama, quantity, idjenis, price)
values('b003', 'Lemari', 4, 1, 5000000);
insert into barang(kodebarang, nama, quantity, idjenis, price)
values('b004', 'Jeans', 100, 4, 25000);
insert into barang(kodebarang, nama, quantity, idjenis, price)
values('b005', 'Kaos', 80, 3, 80000);

create table transaksi(
    idtransaksi serial primary key,
    tanggal_transaksi date not null,
    idpengguna int not null,

    CONSTRAINT fk_transaksi_idpengguna FOREIGN KEY(idpengguna)
    REFERENCES pengguna(idpengguna)
    match simple on update no action on delete no action
);


create table transaksi_detil(
    iddetil serial primary key,
    idtransaksi int not null,
    idbarang int not null,
    quantity int not null,
    price int not null,

    CONSTRAINT fk_transaksi_detil_idtransaksi FOREIGN KEY(idtransaksi)
    REFERENCES transaksi(idtransaksi)
    match simple on update no action on delete no action,
    CONSTRAINT fk_transaksi_detil_idbarang FOREIGN KEY(idbarang)
    REFERENCES barang(idbarang)
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