/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.stuff;

import net.alephdev.lab4.Utils;
import net.alephdev.lab4.alive.Human;
import net.alephdev.lab4.exceptions.CalculationException;
import net.alephdev.lab4.inanimate.SpaceObject;

/**
 *
 * @author MixaDev
 */
public class GravitonLocator implements Takeable, Observable {
    private final String name;
    private final int weight;
    private Location location;
    private Human human;

    public GravitonLocator(String name, int weight) {
        this.name = name;
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
    public boolean canMeasureDistance(Object object, boolean output) {
        if(object instanceof SpaceObject) {
            if(output)
                Utils.print(name+" может измерить расстояние до "+object);
            return true;
        }
        return false;
    }
    public double measureDistance(Object object, boolean output) throws CalculationException {
        if(canMeasureDistance(object, false)) {
            Object observed = ((Observable)object).observe();
            if(!(observed instanceof SpaceObject))
                throw new CalculationException(name+" не удалось пронаблюдать за "+object, object);
            SpaceObject spaceObject = (SpaceObject) observed;
            if(!spaceObject.isInSpace())
                throw new CalculationException(name+" не может обнаружить "+object+" в космосе, поскольку он находится на планете, потому измерение дистанции невозможно", object);
            AbsoluteLocation objectPlace = spaceObject.getAbsoluteLocation();
            AbsoluteLocation place = location.getPlanet().getAbsoluteLocation();
            double[] difference = AbsoluteLocation.calculateDifference(place, objectPlace);
            double distance = Math.sqrt(Math.pow(difference[0],2)+Math.pow(difference[1],2)+Math.pow(difference[2],2));
            if(output)
                Utils.print(name+" установило, что расстояние до "+object+" равно "+distance+" космических единиц");
            return distance;
        }
        return -1;
    }
    public boolean canMeasureSpeed(Object object, boolean output) {
        if(object instanceof SpaceObject) {
            if(output)
                Utils.print(name+" может измерить скорость "+object);
            return true;
        }
        return false;
    }
    public double measureSpeed(Object object, boolean output) throws CalculationException {
        if(canMeasureSpeed(object, false)) {
            double[] vector = measureVector(object, false);
            double speed = Math.sqrt(Math.pow(vector[0],2)+Math.pow(vector[1],2)+Math.pow(vector[2],2));
            if(output)
                Utils.print(name+" установило, что скорость "+object+" равна "+speed+" космических единиц");
            return speed;
        }
        return -1;
    }
    public boolean canMeasureVector(Object object, boolean output){
        if(object instanceof SpaceObject) {
            if(output)
                Utils.print(name+" может измерить направление "+object);
            return true;
        }
        return false;
    }
    public double[] measureVector(Object object, boolean output) throws CalculationException {
        if(canMeasureVector(object, false)) {
            Object observed = ((Observable)object).observe();
            if(!(observed instanceof SpaceObject))
                throw new CalculationException(name+" не удалось пронаблюдать за "+object, object);
            SpaceObject spaceObject = (SpaceObject) observed;
            if(!spaceObject.isInSpace())
                throw new CalculationException(name+" не может пронаблюдать "+object+" в космосе, поскольку он находится на планете, потому вектор равен нулю", object);
            AbsoluteLocation startPlace = spaceObject.getAbsoluteLocation();
            spaceObject.observe();
            AbsoluteLocation endPlace = spaceObject.getAbsoluteLocation();
            double[] difference = AbsoluteLocation.calculateDifference(startPlace, endPlace);
            if(output)
                Utils.print(name+" установило, что вектор "+object+" равен по координатам "+difference[0]+", "+difference[1]+" и "+difference[2]+" космических единиц в осях X, Y и Z соответственно");
            return difference;
        }
        return null;
    }
    public boolean canPredict(Object object, boolean output) {
        if(canMeasureDistance(object, true) &&
                canMeasureSpeed(object, true) &&
                canMeasureVector(object, true)) {
            if(output)
                Utils.print(name+" может предсказать движение, место и время приземления "+object+" на "+location.getPlanet().getName());
            return true;
        }
        return false;
    }
    public boolean predict(Object object, boolean output) {
        if(canPredict(object, false)) {
            try {
                measureDistance(object, true);
                measureVector(object, true);
                measureSpeed(object, true);
                return true;
            } catch(CalculationException ex) {
                if(output)
                    Utils.print(ex.getMessage());
            }
        }
        return false;
    }
    @Override
    public String toString() {
        return name;
    }
}
