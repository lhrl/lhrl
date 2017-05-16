package com.lhrl.common.query;

/**
 *
 * @author 贤志
 */
public final class Sort implements java.io.Serializable {

    public static final String ASC = "ASC";
    public static final String DESC = "DESC";

    private String property;
    private String direction;

    public Sort() {

    }

    public Sort(String prop, String direction) {
        this.property = prop;
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
