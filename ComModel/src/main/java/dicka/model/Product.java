package dicka.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "product",
        catalog = "public.product")
public class Product implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idproduct", nullable = false)
    int idproduct;

    @Column(name = "kodeproduct", nullable = false, unique = true)
    String kodeproduct;

    @Column(name = "nama", nullable = false)
    String nama;

    @Column(name = "deskripsi", nullable = false)
    String deskripsi;

    @Column(name = "actives", nullable = false)
    Boolean actives;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal", nullable = false)
    Date tanggal;

    @ManyToOne
    @JoinColumn(name = "idkategori", nullable = false)
    Kategori kategori;

    protected Product(){}

    public Product(String kodeproduct, String nama, String deskripsi, boolean actives, Date tanggal, Kategori kategori){
        super();
        this.kodeproduct = kodeproduct;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.actives = actives;
        this.tanggal = tanggal;
        this.kategori = kategori;
    }

}
