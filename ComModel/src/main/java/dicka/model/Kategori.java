package dicka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "kategori",
        catalog = "public.kategori")
public class Kategori implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idkategori", nullable = false)
    int idkategori;

    @Column(name = "nama",  nullable = false, unique = true)
    String nama;

    @Column(name = "deskripsi", nullable = false)
    String deskripsi;

    @JsonIgnore
    @OneToMany(mappedBy = "kategori")
    @Column(nullable = true)
    private List<Product> products = new ArrayList<Product>();

    protected Kategori(){}

    public Kategori(String nama, String deskripsi){
        super();
        this.nama = nama;
        this.deskripsi = deskripsi;
    }

}
