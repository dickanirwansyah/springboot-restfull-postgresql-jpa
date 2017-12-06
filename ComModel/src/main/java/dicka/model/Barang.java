package dicka.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "barang", catalog = "public.barang")
public class Barang implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idbarang", nullable = false)
    int idbarang;

    @Column(name = "kodebarang", nullable = false, unique = true)
    String kodebarang;

    @Column(name = "nama", nullable = false)
    String nama;

    @Column(name = "quantity", nullable = false)
    int quantity;

    @ManyToOne
    @JoinColumn(name = "idjenis", nullable = false)
    Jenis jenis;

    @Column(name = "price", nullable = false)
    int price;

    protected Barang(){}

    public Barang(String kodebarang, String nama, int quantity, Jenis jenis, int price){
        super();
        this.kodebarang = kodebarang;
        this.nama = nama;
        this.quantity = quantity;
        this.jenis = jenis;
        this.price = price;
    }
}
