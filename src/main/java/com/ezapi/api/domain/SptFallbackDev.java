package com.ezapi.api.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SptFallbackDev.
 */
@Entity
@Table(name = "spt_fallback_dev")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SptFallbackDev implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "xserver_name")
    private String xserverName;

    @Column(name = "xfallback_drive")
    private String xfallbackDrive;

    @Column(name = "xfallback_low")
    private String xfallbackLow;

    @Column(name = "xdttm_last_ins_upd")
    private ZonedDateTime xdttmLastInsUpd;

    @Column(name = "low")
    private String low;

    @Column(name = "high")
    private String high;

    @Column(name = "status")
    private String status;

    @Column(name = "xdttm_ins")
    private ZonedDateTime xdttmIns;

    @Column(name = "phyname")
    private String phyname;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SptFallbackDev id(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public SptFallbackDev name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXserverName() {
        return this.xserverName;
    }

    public SptFallbackDev xserverName(String xserverName) {
        this.xserverName = xserverName;
        return this;
    }

    public void setXserverName(String xserverName) {
        this.xserverName = xserverName;
    }

    public String getXfallbackDrive() {
        return this.xfallbackDrive;
    }

    public SptFallbackDev xfallbackDrive(String xfallbackDrive) {
        this.xfallbackDrive = xfallbackDrive;
        return this;
    }

    public void setXfallbackDrive(String xfallbackDrive) {
        this.xfallbackDrive = xfallbackDrive;
    }

    public String getXfallbackLow() {
        return this.xfallbackLow;
    }

    public SptFallbackDev xfallbackLow(String xfallbackLow) {
        this.xfallbackLow = xfallbackLow;
        return this;
    }

    public void setXfallbackLow(String xfallbackLow) {
        this.xfallbackLow = xfallbackLow;
    }

    public ZonedDateTime getXdttmLastInsUpd() {
        return this.xdttmLastInsUpd;
    }

    public SptFallbackDev xdttmLastInsUpd(ZonedDateTime xdttmLastInsUpd) {
        this.xdttmLastInsUpd = xdttmLastInsUpd;
        return this;
    }

    public void setXdttmLastInsUpd(ZonedDateTime xdttmLastInsUpd) {
        this.xdttmLastInsUpd = xdttmLastInsUpd;
    }

    public String getLow() {
        return this.low;
    }

    public SptFallbackDev low(String low) {
        this.low = low;
        return this;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getHigh() {
        return this.high;
    }

    public SptFallbackDev high(String high) {
        this.high = high;
        return this;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getStatus() {
        return this.status;
    }

    public SptFallbackDev status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getXdttmIns() {
        return this.xdttmIns;
    }

    public SptFallbackDev xdttmIns(ZonedDateTime xdttmIns) {
        this.xdttmIns = xdttmIns;
        return this;
    }

    public void setXdttmIns(ZonedDateTime xdttmIns) {
        this.xdttmIns = xdttmIns;
    }

    public String getPhyname() {
        return this.phyname;
    }

    public SptFallbackDev phyname(String phyname) {
        this.phyname = phyname;
        return this;
    }

    public void setPhyname(String phyname) {
        this.phyname = phyname;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SptFallbackDev)) {
            return false;
        }
        return id != null && id.equals(((SptFallbackDev) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SptFallbackDev{" +
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
