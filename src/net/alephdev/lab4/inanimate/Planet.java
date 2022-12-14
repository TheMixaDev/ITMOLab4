/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.inanimate;

import net.alephdev.lab4.stuff.AbsoluteLocation;


/**
 *
 * @author MixaDev
 */
public class Planet extends SpaceObject {
    private double gravity;
    private int radius;

    public Planet(String name, AbsoluteLocation location, double gravity, double weight, int radius) {
        super(name, weight, location);
        this.gravity = gravity;
        this.radius = radius;
    }

    public Planet(String name, AbsoluteLocation location, double gravity, double weight) {
        super(name, weight, location);
        this.gravity = gravity;
    }

    public Planet(String name, AbsoluteLocation location, double gravity) {
        super(name, 0, location);
        this.gravity = gravity;
    }

    public Planet(String name, AbsoluteLocation location) {
        super(name, 0, location);
    }

    public double getGravity() {
        return gravity;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (int) (Double.doubleToLongBits(this.gravity) ^ (Double.doubleToLongBits(this.gravity) >>> 32));
        hash = 83 * hash + this.radius;
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
        final Planet other = (Planet) obj;
        if (Double.doubleToLongBits(this.gravity) != Double.doubleToLongBits(other.gravity)) {
            return false;
        }
        return this.radius == other.radius;
    }
    @Override
    public String toString() {
        return getName();
    }
}
