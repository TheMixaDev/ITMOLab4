/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.alive;

import java.util.Objects;
import net.alephdev.lab4.Utils;
import net.alephdev.lab4.stuff.Location;

/**
 *
 * @author MixaDev
 */
public class Community {
    private String name;
    private int population = 1;
    private int knowledge = 0;
    private boolean active = true;
    private Location location;

    public Community(String name, int population, int knowledge) {
        this.name = name;
        this.population = population;
        this.knowledge = knowledge;
    }
    public Community(String name, int population) {
        this.name = name;
        this.population = population;
    }
    public Community(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPopulation() {
        return population;
    }
    public void setPopulation(int population) {
        this.population = population;
    }
    public boolean isActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }
    public int getKnowledge() {
        return knowledge;
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void addKnowledge(int knowledge) {
        this.knowledge += knowledge;
        if(knowledge >= 100) {
            Utils.print(this.toString()+" достигло высокого уровня коллективных знаний ("+knowledge+").");
        }
        if(knowledge < -20) {
            Utils.print(this.toString()+" стремительно деградирует ("+knowledge+").");
        }
    }
    public void destroy() {
        if(!active) {
            Utils.print(this.toString()+" уже является не активным.");
            return;
        }
        population = 0;
        active = false;
        Utils.print(this.toString()+" уничтожено!");
    }
    public void destroy(Human destroyer) {
        if(!active) {
            Utils.print(this.toString()+" уже является не активным.");
            return;
        }
        if(destroyer.getKarma() > 30) {
            Utils.print(destroyer.getName()+" не решился уничтожать "+this.toString()+", он слишком добр для этого, эти мысли его пугают");
            destroyer.reduceMood(10);
            return;
        }
        if(destroyer.isDepressed(false)) return;
        population = 0;
        active = false;
        destroyer.reduceKarma(10);
        Utils.print(destroyer.getName()+" уничтожил "+this.toString());
    }
    public void analyze(Object reviewing) {
        Utils.print(this.toString()+" изучило "+reviewing.toString());
        addKnowledge(10);
    }
    public void analyze(Object reviewing, Object... using) {
        if(knowledge > using.length*10) {
            for(Object use : using)
                Utils.print(this.toString()+" изучило "+reviewing.toString()+" используя "+use.toString());
            conclusion(reviewing, 10*using.length);
            return;
        }
        Utils.print("У "+this.toString()+" недостаточно знания для изучения "+reviewing.toString());
    }
    public void conclusion(Object reviewing, int knowledgeBoost) {
        addKnowledge(knowledgeBoost);
        Utils.print(this.toString()+" сделало выводы по поводу "+reviewing.toString());
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + this.population;
        hash = 79 * hash + (this.active ? 1 : 0);
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
        final Community other = (Community) obj;
        if (this.population != other.population) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        if(!Objects.equals(this.location, other.location)) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    @Override
    public String toString() {
        return "общество "+name;
    }
}
