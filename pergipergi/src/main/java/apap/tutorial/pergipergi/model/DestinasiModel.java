package apap.tutorial.pergipergi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "destinasi")
@JsonIgnoreProperties(value={"listTravelAgensi"}, allowSetters=true)
public class DestinasiModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noDestinasi;

    @NotNull
    @Size(max=30)
    @Column(name="negara_destinasi", nullable = false)
    private String negaraDestinasi;

    @Column(name="is_bebas_visa", nullable = false)
    private Boolean isBebasVisa;

    //Relasi dengan AgensiModel
    @ManyToMany(mappedBy = "listDestinasi")
    List<TravelAgensiModel> listTravelAgensi;
}

