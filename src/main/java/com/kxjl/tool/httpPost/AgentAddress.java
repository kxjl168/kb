package com.kxjl.tool.httpPost;

import java.io.Serializable;
import java.util.List;

/**
 * ip地址解析类
 */
public class AgentAddress implements Serializable {
    private int port;
    private String country;
    private String type;
    private List<String> exportAddress;
    private double responseTime;
    private String anonymity;
    private String host;
    private String from;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getExportAddress() {
        return exportAddress;
    }

    public void setExportAddress(List<String> exportAddress) {
        this.exportAddress = exportAddress;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public String getAnonymity() {
        return anonymity;
    }

    public void setAnonymity(String anonymity) {
        this.anonymity = anonymity;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AgentAddress that = (AgentAddress) o;

        if (port != that.port) return false;
        if (Double.compare(that.responseTime, responseTime) != 0) return false;
        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (exportAddress != null ? !exportAddress.equals(that.exportAddress) : that.exportAddress != null)
            return false;
        if (anonymity != null ? !anonymity.equals(that.anonymity) : that.anonymity != null) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        return from != null ? from.equals(that.from) : that.from == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = port;
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (exportAddress != null ? exportAddress.hashCode() : 0);
        temp = Double.doubleToLongBits(responseTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (anonymity != null ? anonymity.hashCode() : 0);
        result = 31 * result + (host != null ? host.hashCode() : 0);
        result = 31 * result + (from != null ? from.hashCode() : 0);
        return result;
    }
}
