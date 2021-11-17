package apap.tutorial.pergipergi.rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DestinasiDetail {
    private String status;

    @JsonProperty("destinasi-license")
    private Integer destinasiLicense;

    @JsonProperty("valid-until")
    private Date validUntil;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getDestinasiLicense() {
        return destinasiLicense;
    }

    public void setDestinasiLicense(Integer destinasiLicense) {
        this.destinasiLicense = destinasiLicense;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }
}
