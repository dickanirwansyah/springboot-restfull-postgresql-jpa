package dicka.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "role",
        catalog = "public.role")
public class Role implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrole", nullable = false)
    int idrole;

    @Column(name = "nama", nullable = false)
    String nama;

    @JsonIgnore
    @OneToMany(mappedBy = "role")
    @Column(nullable = true)
    private List<Pengguna> penggunas;

    protected Role(){}

    public Role(int idrole, String nama){
        super();
        this.idrole = idrole;
        this.nama = nama;
    }
}
