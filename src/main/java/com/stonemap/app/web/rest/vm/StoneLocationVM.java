package com.stonemap.app.web.rest.vm;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * View Model object for storing a user's credentials.
 */
public class StoneLocationVM {

    private Long stoneId;
    private Float latitude;
    private Float longitude;

    public Long getStoneId() {
        return stoneId;
    }

    public void setStoneId(Long stoneId) {
        this.stoneId = stoneId;
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
}
