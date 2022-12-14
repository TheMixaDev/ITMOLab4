/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.stuff.shapes;

import net.alephdev.lab4.stuff.Shape;

/**
 *
 * @author MixaDev
 */
public class Cone implements Shape {
    private final double length;
    private final double width;
    private final double radius;

    public Cone(double length, double width, double radius) {
        this.length = length < maxLength ? length : maxLength;
        this.width = width < maxWidth ? width : maxWidth;
        this.radius = radius < maxRadius ? radius : maxRadius;
    }
    @Override
    public double getLength() {
        return this.length;
    }
    @Override
    public double getWidth() {
        return this.width;
    }
    @Override
    public double getRadius() {
        return this.radius;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.length) ^ (Double.doubleToLongBits(this.length) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.width) ^ (Double.doubleToLongBits(this.width) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.radius) ^ (Double.doubleToLongBits(this.radius) >>> 32));
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
        final Cone other = (Cone) obj;
        if (Double.doubleToLongBits(this.length) != Double.doubleToLongBits(other.length)) {
            return false;
        }
        if (Double.doubleToLongBits(this.width) != Double.doubleToLongBits(other.width)) {
            return false;
        }
        return Double.doubleToLongBits(this.radius) == Double.doubleToLongBits(other.radius);
    }
    @Override
    public String toString() {
        return "конус";
    }
}
