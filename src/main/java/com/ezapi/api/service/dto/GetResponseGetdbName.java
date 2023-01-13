
package com.ezapi;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;


/**
 * An address similar to http://microformats.org/wiki/h-card
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "xdttm_last_ins_upd",
    "high",
    "phyname",
    "xserver_name",
    "low",
    "name",
    "xfallback_low",
    "xdttm_ins",
    "xfallback_drive",
    "status"
})
public class GetResponseGetdbName {

    @JsonProperty("xdttm_last_ins_upd")
    private Date xdttmLastInsUpd;
    @JsonProperty("high")
    private Integer high;
    @JsonProperty("phyname")
    private String phyname;
    @JsonProperty("xserver_name")
    private String xserverName;
    @JsonProperty("low")
    private Integer low;
    @JsonProperty("name")
    private String name;
    @JsonProperty("xfallback_low")
    private Integer xfallbackLow;
    @JsonProperty("xdttm_ins")
    private Date xdttmIns;
    @JsonProperty("xfallback_drive")
    private String xfallbackDrive;
    @JsonProperty("status")
    private Integer status;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("xdttm_last_ins_upd")
    public Date getXdttmLastInsUpd() {
        return xdttmLastInsUpd;
    }

    @JsonProperty("xdttm_last_ins_upd")
    public void setXdttmLastInsUpd(Date xdttmLastInsUpd) {
        this.xdttmLastInsUpd = xdttmLastInsUpd;
    }

    public GetResponseGetdbName withXdttmLastInsUpd(Date xdttmLastInsUpd) {
        this.xdttmLastInsUpd = xdttmLastInsUpd;
        return this;
    }

    @JsonProperty("high")
    public Integer getHigh() {
        return high;
    }

    @JsonProperty("high")
    public void setHigh(Integer high) {
        this.high = high;
    }

    public GetResponseGetdbName withHigh(Integer high) {
        this.high = high;
        return this;
    }

    @JsonProperty("phyname")
    public String getPhyname() {
        return phyname;
    }

    @JsonProperty("phyname")
    public void setPhyname(String phyname) {
        this.phyname = phyname;
    }

    public GetResponseGetdbName withPhyname(String phyname) {
        this.phyname = phyname;
        return this;
    }

    @JsonProperty("xserver_name")
    public String getXserverName() {
        return xserverName;
    }

    @JsonProperty("xserver_name")
    public void setXserverName(String xserverName) {
        this.xserverName = xserverName;
    }

    public GetResponseGetdbName withXserverName(String xserverName) {
        this.xserverName = xserverName;
        return this;
    }

    @JsonProperty("low")
    public Integer getLow() {
        return low;
    }

    @JsonProperty("low")
    public void setLow(Integer low) {
        this.low = low;
    }

    public GetResponseGetdbName withLow(Integer low) {
        this.low = low;
        return this;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public GetResponseGetdbName withName(String name) {
        this.name = name;
        return this;
    }

    @JsonProperty("xfallback_low")
    public Integer getXfallbackLow() {
        return xfallbackLow;
    }

    @JsonProperty("xfallback_low")
    public void setXfallbackLow(Integer xfallbackLow) {
        this.xfallbackLow = xfallbackLow;
    }

    public GetResponseGetdbName withXfallbackLow(Integer xfallbackLow) {
        this.xfallbackLow = xfallbackLow;
        return this;
    }

    @JsonProperty("xdttm_ins")
    public Date getXdttmIns() {
        return xdttmIns;
    }

    @JsonProperty("xdttm_ins")
    public void setXdttmIns(Date xdttmIns) {
        this.xdttmIns = xdttmIns;
    }

    public GetResponseGetdbName withXdttmIns(Date xdttmIns) {
        this.xdttmIns = xdttmIns;
        return this;
    }

    @JsonProperty("xfallback_drive")
    public String getXfallbackDrive() {
        return xfallbackDrive;
    }

    @JsonProperty("xfallback_drive")
    public void setXfallbackDrive(String xfallbackDrive) {
        this.xfallbackDrive = xfallbackDrive;
    }

    public GetResponseGetdbName withXfallbackDrive(String xfallbackDrive) {
        this.xfallbackDrive = xfallbackDrive;
        return this;
    }

    @JsonProperty("status")
    public Integer getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(Integer status) {
        this.status = status;
    }

    public GetResponseGetdbName withStatus(Integer status) {
        this.status = status;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public GetResponseGetdbName withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(xdttmLastInsUpd).append(high).append(phyname).append(xserverName).append(low).append(name).append(xfallbackLow).append(xdttmIns).append(xfallbackDrive).append(status).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof GetResponseGetdbName) == false) {
            return false;
        }
        GetResponseGetdbName rhs = ((GetResponseGetdbName) other);
        return new EqualsBuilder().append(xdttmLastInsUpd, rhs.xdttmLastInsUpd).append(high, rhs.high).append(phyname, rhs.phyname).append(xserverName, rhs.xserverName).append(low, rhs.low).append(name, rhs.name).append(xfallbackLow, rhs.xfallbackLow).append(xdttmIns, rhs.xdttmIns).append(xfallbackDrive, rhs.xfallbackDrive).append(status, rhs.status).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
