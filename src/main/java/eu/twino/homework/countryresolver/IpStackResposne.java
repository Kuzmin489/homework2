package eu.twino.homework.countryresolver;

import com.fasterxml.jackson.annotation.JsonProperty;

public class IpStackResposne {
    private String ip;

    @JsonProperty("country_code")
    private String countryCode;

    public String getIp() {
        return ip;
    }

    public IpStackResposne setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public IpStackResposne setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }
}
