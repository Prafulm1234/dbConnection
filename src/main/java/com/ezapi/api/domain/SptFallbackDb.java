package com.ezapi.api.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A SptFallbackDb.
 */
@Entity
@Table(name = "spt_fallback_db")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class SptFallbackDb implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "version")
    private String version;

    @Column(name = "name")
    private String name;

    @Column(name = "dbid")
    private String dbid;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SptFallbackDb id(Long id) {
        this.id = id;
        return this;
    }

    public String getVersion() {
        return this.version;
    }

    public SptFallbackDb version(String version) {
        this.version = version;
        return this;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return this.name;
    }

    public SptFallbackDb name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbid() {
        return this.dbid;
    }

    public SptFallbackDb dbid(String dbid) {
        this.dbid = dbid;
        return this;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SptFallbackDb)) {
            return false;
        }
        return id != null && id.equals(((SptFallbackDb) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SptFallbackDb{" +
            "id=" + getId() +
            ", version='" + getVersion() + "'" +
            ", name='" + getName() + "'" +
            ", dbid='" + getDbid() + "'" +
            "}";
    }
}
