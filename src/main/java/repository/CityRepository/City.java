package repository.CityRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cities")
public class City {

    @Id
    @Column(nullable = false, unique = true)
    private int cityId;

    @Column(nullable = false)
    private String cityName;

}
