/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.stuff;

import net.alephdev.lab4.alive.Human;

/**
 *
 * @author MixaDev
 */
public class Seed implements Takeable, Observable {
    private final int weight;
    private Location location;
    private Human human;

    public Seed(int weight) {
        this.weight = weight;
    }
    @Override
    public boolean bind(Human human) {
        if(this.human != null) return false;
        this.human = human;
        this.location = human.getLocation();
        return true;
    }
    @Override
    public boolean unbind() {
        if(this.human == null) return false;
        this.human = null;
        this.location = human.getLocation();
        return true;
    }
    @Override
    public Human getHuman() {
        return this.human;
    }
    public Location getLocation() {
        return location;
    }
    public boolean setLocation(Location location) {
        if(human != null) return false;
        this.location = location;
        return true;
    }
    @Override
    public Object observe() {
        return weight > 10 ? this : null;
    }
    @Override
    public int getWeight() {
        return weight;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.weight;
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
        final Seed other = (Seed) obj;
        return this.weight == other.weight;
    }
    @Override
    public String toString() {
        return "семя";
    }
}
