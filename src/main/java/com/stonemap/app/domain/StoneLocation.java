package com.stonemap.app.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
//@Table(name = "stoneLocation")
@Table(name = "stone_location")
public class StoneLocation  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Stone stone;

    @Column(name="latitude")
    private Float latitude;

    @Column(name = "longitude")
    private Float longitude;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Stone getStone() {
        return stone;
    }

    public void setStone(Stone stone) {
        this.stone = stone;
    }
}
