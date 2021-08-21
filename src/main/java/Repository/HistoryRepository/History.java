package Repository.HistoryRepository;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "city_history")
public class History {

    public History(long cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.date = LocalDateTime.now();
    }

    @Id
    @Column(nullable = false, unique = true)
    private long cityId;

    @Column(nullable = false)
    private String cityName;

    @Column(nullable = false)
    private LocalDateTime date;
}
