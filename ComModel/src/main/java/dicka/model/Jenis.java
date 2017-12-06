package dicka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "jenis",
        catalog = "public.jenis")
public class Jenis implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idjenis", nullable = false)
    int idjenis;

    @Column(name = "kodejenis", nullable = false, unique = true)
    String kodejenis;

    @Column(name = "deskripsi", nullable = false)
    String deskripsi;

    @JsonIgnore
    @OneToMany(mappedBy = "jenis")
    @Column(nullable = true)
    private List<Barang> listbarang = new ArrayList<>();

    protected Jenis(){}

    public Jenis(String kodejenis, String deskripsi){
        super();
        this.kodejenis = kodejenis;
        this.deskripsi = deskripsi;
    }
}
