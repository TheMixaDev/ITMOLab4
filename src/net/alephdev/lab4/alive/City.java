/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.alive;

import java.util.Objects;
import net.alephdev.lab4.stuff.Location;
import net.alephdev.lab4.stuff.Observable;

/**
 *
 * @author MixaDev
 */
public class City implements Observable {
    private String name;
    private Location location;
    private int population;

    public City(String name, Location location, int population) {
        this.name = name;
        this.location = location;
        this.population = population;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
    public int getPopulation() {
        return population;
    }
    @Override
    public Object observe() {
        return population > 0 ? this : null;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.name);
        hash = 83 * hash + Objects.hashCode(this.location);
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
        final City other = (City) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if(this.population != other.population)
            return false;
        return Objects.equals(this.location, other.location);
    }
    @Override
    public String toString() {
        return this.name;
    }
}
