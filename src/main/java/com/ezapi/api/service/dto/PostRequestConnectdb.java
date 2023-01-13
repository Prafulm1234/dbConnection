
package com.ezapi;

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
    "update",
    "spt_fallback_dev"
})
public class PostRequestConnectdb {

    @JsonProperty("update")
    private Object update;
    @JsonProperty("spt_fallback_dev")
    private Object sptFallbackDev;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("update")
    public Object getUpdate() {
        return update;
    }

    @JsonProperty("update")
    public void setUpdate(Object update) {
        this.update = update;
    }

    public PostRequestConnectdb withUpdate(Object update) {
        this.update = update;
        return this;
    }

    @JsonProperty("spt_fallback_dev")
    public Object getSptFallbackDev() {
        return sptFallbackDev;
    }

    @JsonProperty("spt_fallback_dev")
    public void setSptFallbackDev(Object sptFallbackDev) {
        this.sptFallbackDev = sptFallbackDev;
    }

    public PostRequestConnectdb withSptFallbackDev(Object sptFallbackDev) {
        this.sptFallbackDev = sptFallbackDev;
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

    public PostRequestConnectdb withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(update).append(sptFallbackDev).append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof PostRequestConnectdb) == false) {
            return false;
        }
        PostRequestConnectdb rhs = ((PostRequestConnectdb) other);
        return new EqualsBuilder().append(update, rhs.update).append(sptFallbackDev, rhs.sptFallbackDev).append(additionalProperties, rhs.additionalProperties).isEquals();
    }

}
