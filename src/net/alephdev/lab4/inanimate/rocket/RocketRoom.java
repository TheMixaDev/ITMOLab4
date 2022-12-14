/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.inanimate.rocket;

import java.util.Objects;
import net.alephdev.lab4.stuff.Observable;

/**
 *
 * @author MixaDev
 */
public class RocketRoom implements Observable {
    private String name;
    private double width;
    private double length;
    private double height;

    public RocketRoom(String name, double width, double length, double height) {
        this.name = name;
        this.width = width;
        this.length = length;
        this.height = height;
    }
    public RocketRoom(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getLength() {
        return length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
    @Override
    public Object observe() {
        return name != null && !name.isEmpty() ? this : null;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.width) ^ (Double.doubleToLongBits(this.width) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.length) ^ (Double.doubleToLongBits(this.length) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.height) ^ (Double.doubleToLongBits(this.height) >>> 32));
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RocketRoom other = (RocketRoom) obj;
        if (Double.doubleToLongBits(this.width) != Double.doubleToLongBits(other.width)) {
            return false;
        }
        if (Double.doubleToLongBits(this.length) != Double.doubleToLongBits(other.length)) {
            return false;
        }
        if (Double.doubleToLongBits(this.height) != Double.doubleToLongBits(other.height)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    @Override
    public String toString() {
        return this.name;
    }
}
