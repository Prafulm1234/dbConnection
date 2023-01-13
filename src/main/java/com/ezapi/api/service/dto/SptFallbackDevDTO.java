package com.ezapi.api.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the {@link com.ezapi.api.domain.SptFallbackDev} entity.
 */
public class SptFallbackDevDTO implements Serializable {

    private Long id;

    private String name;

    private String xserverName;

    private String xfallbackDrive;

    private String xfallbackLow;

    private ZonedDateTime xdttmLastInsUpd;

    private String low;

    private String high;

    private String status;

    private ZonedDateTime xdttmIns;

    private String phyname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXserverName() {
        return xserverName;
    }

    public void setXserverName(String xserverName) {
        this.xserverName = xserverName;
    }

    public String getXfallbackDrive() {
        return xfallbackDrive;
    }

    public void setXfallbackDrive(String xfallbackDrive) {
        this.xfallbackDrive = xfallbackDrive;
    }

    public String getXfallbackLow() {
        return xfallbackLow;
    }

    public void setXfallbackLow(String xfallbackLow) {
        this.xfallbackLow = xfallbackLow;
    }

    public ZonedDateTime getXdttmLastInsUpd() {
        return xdttmLastInsUpd;
    }

    public void setXdttmLastInsUpd(ZonedDateTime xdttmLastInsUpd) {
        this.xdttmLastInsUpd = xdttmLastInsUpd;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getXdttmIns() {
        return xdttmIns;
    }

    public void setXdttmIns(ZonedDateTime xdttmIns) {
        this.xdttmIns = xdttmIns;
    }

    public String getPhyname() {
        return phyname;
    }

    public void setPhyname(String phyname) {
        this.phyname = phyname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SptFallbackDevDTO)) {
            return false;
        }

        SptFallbackDevDTO sptFallbackDevDTO = (SptFallbackDevDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sptFallbackDevDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SptFallbackDevDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", xserverName='" + getXserverName() + "'" +
            ", xfallbackDrive='" + getXfallbackDrive() + "'" +
            ", xfallbackLow='" + getXfallbackLow() + "'" +
            ", xdttmLastInsUpd='" + getXdttmLastInsUpd() + "'" +
            ", low='" + getLow() + "'" +
            ", high='" + getHigh() + "'" +
            ", status='" + getStatus() + "'" +
            ", xdttmIns='" + getXdttmIns() + "'" +
            ", phyname='" + getPhyname() + "'" +
            "}";
    }
}
