/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.inanimate;

import java.util.Objects;
import net.alephdev.lab4.stuff.AbsoluteLocation;
import net.alephdev.lab4.stuff.Observable;

/**
 *
 * @author MixaDev
 */
public class SpaceObject implements Observable {
    private String name;
    private double weight = 0;
    private AbsoluteLocation absoluteLocation;

    public SpaceObject(String name) {
        this.name = name;
    }
    public SpaceObject(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
    public SpaceObject(String name, double weight, AbsoluteLocation location) {
        this.name = name;
        this.weight = weight;
        this.absoluteLocation = location;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getWeight() {
        return weight;
    }
    public void setWeight(double weight) {
        this.weight = weight;
    }
    public boolean isInSpace() {
        return absoluteLocation != null;
    }
    public AbsoluteLocation getAbsoluteLocation() {
        return absoluteLocation;
    }
    public void setAbsoluteLocation(AbsoluteLocation absoluteLocation) {
        this.absoluteLocation = absoluteLocation;
    }
    public void passiveMovement() {
        if(!isInSpace())
            return;
        absoluteLocation.setX(absoluteLocation.getX()+0.1);
        absoluteLocation.setY(absoluteLocation.getY()+0.1);
        absoluteLocation.setZ(absoluteLocation.getZ()+0.1);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.weight) ^ (Double.doubleToLongBits(this.weight) >>> 32));
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
        final SpaceObject other = (SpaceObject) obj;
        if (Double.doubleToLongBits(this.weight) != Double.doubleToLongBits(other.weight)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    @Override
    public String toString() {
        return name;
    }
    @Override
    public Object observe() {
        passiveMovement();
        return weight > 0 ? this : null;
    }
}
