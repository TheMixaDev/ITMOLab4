/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.alive;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.alephdev.lab4.inanimate.Planet;
import net.alephdev.lab4.stuff.Location;
import net.alephdev.lab4.stuff.Origin;

/**
 *
 * @author MixaDev
 */
public abstract class SocialGroup {
    private final String name;
    private Origin origin;
    private Planet planet;
    private int rating = 50;
    private List<Location> bannedLocations = new ArrayList<>();

    public SocialGroup(String name, Planet planet, Origin origin) {
        this.name = name;
        this.origin = origin;
        this.planet = planet;
    }
    public SocialGroup(String name) {
        this.name = name;
        this.origin = Origin.NOT_STATED;
    }
    public Origin getOrigin() {
        return origin;
    }
    public void setOrigin(Origin origin) {
        this.origin = origin;
    }
    public String getName() {
        return name;
    }
    public Planet getPlanet() {
        return planet;
    }
    public void setPlanet(Planet planet) {
        this.planet = planet;
    }
    public int getRating() {
        return rating;
    }
    public void addRating(int rating) {
        this.rating += rating;
    }
    public void reduceRating(int rating) {
        addRating(-rating);
    }
    public List<Location> getBannedLocations() {
        return bannedLocations;
    }
    public void addBannedLocation(Location location) {
        bannedLocations.add(location);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.origin);
        hash = 53 * hash + Objects.hashCode(this.planet);
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
        final SocialGroup other = (SocialGroup) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (this.origin != other.origin) {
            return false;
        }
        return Objects.equals(this.planet, other.planet);
    }
    @Override
    public String toString() {
        return this.name;
    }
    public String toString(Human pov) {
        if(pov.getLocation().getPlanet().equals(this.getPlanet())) {
            if(this.getOrigin() == Origin.RESIDENT)
                return "местные "+this.getName();
            return "не местные "+this.getName();
        }
        return Origin.ALIEN.toString()+" планеты "+this.getPlanet();
    }
}
