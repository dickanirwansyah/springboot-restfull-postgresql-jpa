package dicka.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "pengguna",
        catalog = "public.pengguna")
public class Pengguna implements Serializable{

    @Id
    @Column(name = "idpengguna")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idpengguna;

    @Column(name = "nama", nullable = false)
    String nama;

    @Column(name = "actives", nullable = false)
    int actives;

    @JoinColumn(name = "idrole", nullable = false)
    @ManyToOne
    Role role;

    @Column(name = "username", nullable = false, unique = true)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "tanggal_lahir", nullable = false)
    Date tanggalLahir;

    @Column(name = "alamat", nullable = false)
    String alamat;

    protected Pengguna(){}

    public Pengguna(String nama, int actives,
                    Role role, String username,
                    String password,
                    Date tanggalLahir,
                    String alamat){
        super();
        this.nama = nama;
        this.actives = actives;
        this.role = role;
        this.username = username;
        this.password = password;
        this.tanggalLahir = tanggalLahir;
        this.alamat = alamat;
    }
}
