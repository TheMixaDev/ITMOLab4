/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.stuff;

import java.util.Objects;

/**
 *
 * @author MixaDev
 */
public class Material {
    private String name;
    private int density;
    private int heatCapacity;

    public Material(String name, int density, int heatCapacity) {
        this.name = name;
        this.density = density;
        this.heatCapacity = heatCapacity;
    }
    public Material(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getDensity() {
        return density;
    }
    public int getHeatCapacity() {
        return heatCapacity;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + this.density;
        hash = 53 * hash + this.heatCapacity;
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
        final Material other = (Material) obj;
        if (this.density != other.density) {
            return false;
        }
        if (this.heatCapacity != other.heatCapacity) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    @Override
    public String toString() {
        return this.name;
    }
}
