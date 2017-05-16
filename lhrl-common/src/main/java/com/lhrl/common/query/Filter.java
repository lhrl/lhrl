package com.lhrl.common.query;

/**
 *
 * @author 贤志
 */
public class Filter implements java.io.Serializable {

    public static enum Operaion {

        eq, ne, in, notIn, ge, gt, le, lt, like, likeAny, likeStart, likeEnd
    }

    private String property;
    private Object value;
    private Operaion operation;

    public Filter() {

    }

    public Filter(String prop, Object value, Operaion operation) {
        this.setProperty(prop);
        this.setValue(value);
        this.setOperation(operation);
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Operaion getOperation() {
        return operation;
    }

    public void setOperation(Operaion operation) {
        this.operation = operation;
    }
}
