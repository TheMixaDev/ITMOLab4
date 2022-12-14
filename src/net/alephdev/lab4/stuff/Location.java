/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.stuff;

import java.util.Objects;
import net.alephdev.lab4.inanimate.Planet;

/**
 *
 * @author MixaDev
 */
public class Location implements Observable {
    private String name;
    private Planet planet;
    private double longitude; // X
    private double latitude; // Z
    private double altitude; // Y

    public Location(String name, Planet planet, double longitude, double latitude, double altitude) {
        this.name = name;
        this.planet = planet;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    public Location(String name, Planet planet, double longitude, double latitude) {
        this.name = name;
        this.planet = planet;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location(String name, Planet planet) {
        this.name = name;
        this.planet = planet;
    }

    public Location(String name) {
        this.name = name;
        this.planet = new Planet("Космос", new AbsoluteLocation(0, 0, 0));
    }

    public Location(Planet planet) {
        this.name = planet.getName();
        this.planet = planet;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
    @Override
    public Object observe() {
        return planet.observe();
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.planet);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.altitude) ^ (Double.doubleToLongBits(this.altitude) >>> 32));
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
        final Location other = (Location) obj;
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.altitude) != Double.doubleToLongBits(other.altitude)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.planet, other.planet);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
