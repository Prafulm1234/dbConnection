package com.ezapi.api.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.ezapi.api.domain.SptFallbackDb} entity.
 */
public class SptFallbackDbDTO implements Serializable {

    private Long id;

    private String version;

    private String name;

    private String dbid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDbid() {
        return dbid;
    }

    public void setDbid(String dbid) {
        this.dbid = dbid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SptFallbackDbDTO)) {
            return false;
        }

        SptFallbackDbDTO sptFallbackDbDTO = (SptFallbackDbDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, sptFallbackDbDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "SptFallbackDbDTO{" +
            "id=" + getId() +
            ", version='" + getVersion() + "'" +
            ", name='" + getName() + "'" +
            ", dbid='" + getDbid() + "'" +
            "}";
    }
}
