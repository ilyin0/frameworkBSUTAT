package model;

import java.util.Objects;

public class Pizza {
    private String name;
    private String size;
    private String sideType;

    public Pizza(String name, String size, String sideType) {
        this.name = name;
        this.size = size;
        this.sideType = sideType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSideType() {
        return sideType;
    }

    public void setSideType(String sideType) {
        this.sideType = sideType;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", sideType='" + sideType + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return Objects.equals(name, pizza.name) &&
                Objects.equals(size, pizza.size) &&
                Objects.equals(sideType, pizza.sideType);
    }

    public boolean equalsIgnoreCase(Object o) {
        if(this==o) return true;
        if(o==null || getClass()!=o.getClass()) return false;
        Pizza pizza = (Pizza) o;
        return name.equalsIgnoreCase(pizza.name) &&
                size.equalsIgnoreCase(pizza.size) &&
                sideType.equalsIgnoreCase(pizza.sideType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size, sideType);
    }
}
