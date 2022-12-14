/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.stuff;

import net.alephdev.lab4.Utils;
import net.alephdev.lab4.alive.Human;

/**
 *
 * @author MixaDev
 */
public class GravitonTelescope implements Takeable, Observable {
    private final String name;
    private final int weight;
    private Location location;
    private Human human;

    public GravitonTelescope(String name, int weight) {
        this.weight = weight;
        this.name = name;
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
    public boolean isObservable(Object object, boolean output) {
        if(object instanceof Observable) {
            if(output)
                Utils.print(object.toString()+" может быть обнаружено с помощью "+name);
            return true;
        }
        Utils.print(object+" невозможно обнаружить с помощью "+name);
        return false;
    }
    public boolean observeObject(Object object) {
        if(isObservable(object, false)) {
            Object result = ((Observable)object).observe();
            if(result != null) {
                Utils.print(name+" успешно обнаружил "+result);
                return true;
            } else {
                Utils.print(name+" не удалось обнаружить "+object);
            }
        }
        return false;
    }
    @Override
    public int getWeight() {
        return weight;
    }
    @Override
    public Object observe() {
        return weight > 1 ? this : null;
    }
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}
