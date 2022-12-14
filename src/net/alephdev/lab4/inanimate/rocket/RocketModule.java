/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.inanimate.rocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.alephdev.lab4.Utils;
import net.alephdev.lab4.stuff.Material;
import net.alephdev.lab4.stuff.Observable;
import net.alephdev.lab4.stuff.Shape;

/**
 *
 * @author MixaDev
 */
public class RocketModule implements Observable {
    private int number;
    private Material material;
    private Shape shape;
    private List<RocketRoom> rooms = new ArrayList<>();

    public RocketModule(int number, Material material, Shape shape, List<RocketRoom> rooms) {
        this.number = number;
        this.material = material;
        this.shape = shape;
        this.rooms = rooms;
    }
    public RocketModule(int number, Material material, Shape shape) {
        this.number = number;
        this.material = material;
        this.shape = shape;
    }
    public Shape getShape() {
        return shape;
    }
    public void setShape(Shape shape) {
        this.shape = shape;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    public List<RocketRoom> getRooms() {
        return rooms;
    }
    public void clearRooms() {
        this.rooms.clear();
    }
    public void addRoom(RocketRoom room) {
        this.rooms.add(room);
    }
    public void removeRoom(int index) {
        this.rooms.remove(index);
    }
    public void removeRoom(RocketRoom room) {
        this.rooms.remove(room);
    }
    public void describe() {
        Utils.print(number+"-я ступень была сделана из материала "+material.getName()+" в форме "+(shape.getLength()>20?"длинного ":"")+shape.toString());
        rooms.forEach((room) -> {
            Utils.print("В "+number+"-ей ступени размещалась "+room.getName());
        });
    }
    @Override
    public Object observe() {
        return material != null ? this : null;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.number;
        hash = 47 * hash + Objects.hashCode(this.material);
        hash = 47 * hash + Objects.hashCode(this.shape);
        hash = 47 * hash + Objects.hashCode(this.rooms);
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
        final RocketModule other = (RocketModule) obj;
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.material, other.material)) {
            return false;
        }
        if (!Objects.equals(this.shape, other.shape)) {
            return false;
        }
        return Objects.equals(this.rooms, other.rooms);
    }
    @Override
    public String toString() {
        return this.number+"-я ступень ракеты";
    }
    
}
