/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.inanimate.rocket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.alephdev.lab4.Utils;
import net.alephdev.lab4.alive.City;
import net.alephdev.lab4.alive.Human;
import net.alephdev.lab4.exceptions.RocketExplosionException;
import net.alephdev.lab4.inanimate.Planet;
import net.alephdev.lab4.inanimate.SpaceObject;
import net.alephdev.lab4.stuff.Location;
import net.alephdev.lab4.stuff.Observable;
import net.alephdev.lab4.stuff.Owned;

/**
 *
 * @author MixaDev
 */
public class Rocket extends SpaceObject implements Owned {
    private City city;
    private List<RocketModule> modules = new ArrayList<>();
    private boolean destroyed = false;
    private Parts parts;
    private boolean equipped = false;
    private List<Human> owners = new ArrayList<>();
    private final Observable rocketInsides = () -> {
        getModules().forEach((module) -> {
            module.describe();
        });
        return getModules().isEmpty() ? null : getModules();
    };

    public Rocket(String name, Parts parts, int weight, City city, List<RocketModule> modules) {
        super(name, weight);
        this.city = city;
        this.modules = modules;
        this.parts = parts;
    }
    public Rocket(String name, Parts parts,int weight, City city) {
        super(name, weight);
        this.city = city;
        this.parts = parts;
    }
    public Rocket(String name, Parts parts,int weight, Location location) {
        super(name, weight);
        this.city = new City(location.getName(), location, 1);
        this.parts = parts;
    }
    public Rocket(String name, Parts parts, int weight, Planet planet) {
        super(name, weight);
        this.city = new City(planet.getName(), new Location(planet), 1);
        this.parts = parts;
    }
    public Rocket(String name, Parts parts, int weight) {
        super(name, weight);
        this.parts = parts;
    }
    public City getCity() {
        return city;
    }
    public void setCity(City city) {
        this.city = city;
    }
    public List<RocketModule> getModules() {
        return modules;
    }
    public void clearModules() {
        this.modules.clear();
    }
    public void addModule(RocketModule module) {
        this.modules.add(module);
    }
    public void removeModule(RocketModule module) {
        this.modules.remove(module);
    }
    public void removeModule(int index) {
        this.modules.remove(index);
    }
    public Observable getRocketInsides() {
        return rocketInsides;
    }
    public Parts getParts() {
        return parts;
    }
    public void setParts(Parts parts) {
        this.parts = parts;
    }
    @Override
    public List<Human> getOwners() {
        return owners;
    }
    @Override
    public void addOwner(Human human) {
        owners.add(human);
    }
    @Override
    public void removeOwner(Human human) {
        owners.remove(human);
    }
    @Override
    public void removeOwner(int index) {
        owners.remove(index);
    }
    public void equip() {
        if(equipped) {
            Utils.print("Ракета уже оборудованна");
            return;
        }
        equipped = true;
        Utils.print("Ракета оборудованна");
    }
    public boolean checkEquipment(boolean output) {
        if(output) {
            if(equipped)
                Utils.print("Оборудование ракеты проверено");
            else
                Utils.print("Оборудование ракеты отсутствует и не прошло проверку");
        }
        return equipped;
    }
    public boolean isDestroyed() {
        return destroyed;
    }
    public void describe() {
        Utils.print(getName()+" находилась в "+city.getName());
        Object insides = rocketInsides.observe();
        if(insides == null)
            Utils.print(getName()+" на данный момент не имеет модулей");
    }
    public boolean beforeLaunchCheck(final boolean output) {
        class MessageManager {
            final String IN_SPACE = "Ракета уже в космосе и не требует запуска";
            final String DESTROYED = "Разрушенная ракета не может улететь";
            final String PARTS_ISSUE = "Ракета без готовых деталей еще не готова к взлету";
            final String EQUIPMENT_ISSUE = "Ракета без оборудования еще не готова к взлету";
            final String HEAVY = "К сожалению, слишком тяжелая, что бы покинуть планету "+city.getLocation().getPlanet().getName();
            final String SUCCESS = "Проектировка ракеты позволяет ей улететь с планеты "+city.getLocation().getPlanet().getName()+" в открытый космос";
            public boolean print(String message, boolean result) {
                if(output)
                    Utils.print(message);
                return result;
            }
        }
        MessageManager result = new MessageManager();
        if(isInSpace())
            return result.print(result.IN_SPACE, false);
        if(destroyed)
            return result.print(result.DESTROYED, false);
        if(!parts.isReady())
            return result.print(result.PARTS_ISSUE, false);
        if(!checkEquipment(false))
            return result.print(result.EQUIPMENT_ISSUE, false);
        if(getWeight() * city.getLocation().getPlanet().getGravity() < 1000000) {
            return result.print(result.SUCCESS, true);
        }
        return result.print(result.HEAVY, false);
    }
    public boolean launch(Location target) {
        if(beforeLaunchCheck(true)) {
            try {
                checkExplosion();
            } catch(RocketExplosionException ex) {
                Utils.print(ex.getMessage());
                return false;
            }
            setAbsoluteLocation(city.getLocation().getPlanet().getAbsoluteLocation());
            city = new City("Космос", new Location("Космическое пространство"), 0);
            Utils.print("Ракета готова к запуску, и улетает в открытый космос!");
        } else {
            Utils.print("Запуск ракеты отменен");
        }
        return true;
    }
    public void checkExplosion() {
        if(Math.random() < 0.01)
            explode();
    }
    public void explode() {
        destroyed = true;
        setWeight(0);
        throw new RocketExplosionException("Ракета взорвалась!", this);
    }
    @Override
    public Object observe() {
        return beforeLaunchCheck(false) ? this : null;
    }
    public void manage() {
        parts.autoCompletion();
        equip();
        checkEquipment(true);
        checkExplosion();
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.city);
        hash = 67 * hash + Objects.hashCode(this.modules);
        hash = 67 * hash + Objects.hashCode(this.destroyed);
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
        final Rocket other = (Rocket) obj;
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.destroyed, other.destroyed)) {
            return false;
        }
        return Objects.equals(this.modules, other.modules);
    }
    @Override
    public String toString() {
        return Objects.isNull(getName()) ? "ракета" : getName();
    }
    public static class Parts {
        private final String name;
        private Location location;
        private boolean compiled = false;
        private boolean fitted = false;
        private boolean screwed = false;
        private boolean soldered = false;
        private boolean ready = false;

        public Parts(String name) {
            this.name = name;
        }
        public void move(City city) {
            location = city.getLocation();
            Utils.print(name+" поступили в "+city.getName());
        }
        public void compile() {
            if(location == null) {
                Utils.print("Невозможно собрать "+name+", пока они не поступили в город");
                return;
            }
            compiled = true;
            Utils.print(name+" собраны");
        }
        public void fit() {
            if(!compiled) {
                Utils.print("Невозможно подогнать "+name+", пока они не собраны");
                return;
            }
            fitted = true;
            Utils.print(name+" подогнаны друг к другу");
        }
        public void screw() {
            if(!fitted) {
                Utils.print("Невозможно свинтить "+name+", пока они не подогнаны");
                return;
            }
            screwed = true;
            Utils.print(name+" свинчены");
        }
        public void solder() {
            if(!screwed) {
                Utils.print("Невозможно спаять "+name+", пока они не свинчены");
                return;
            }
            soldered = true;
            Utils.print(name+" спаяны");
        }
        public void welder() {
            if(!soldered) {
                Utils.print("Невозможно сварить "+name+", пока они не спаяны");
                return;
            }
            ready = true;
            Utils.print(name+" сварены");
        }
        public void autoCompletion() {
            compile();
            fit();
            screw();
            solder();
            welder();
        }
        public boolean isReady() {
            return ready;
        }
        @Override
        public String toString() {
            return name;
        }
    }
}
