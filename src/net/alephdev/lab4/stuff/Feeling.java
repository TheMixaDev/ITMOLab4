/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.stuff;

import java.util.Objects;
import net.alephdev.lab4.Utils;
import net.alephdev.lab4.alive.Human;

/**
 *
 * @author MixaDev
 */
public class Feeling {
    String name;
    int moodModifier = 0;
    int karmaModifier = 0;

    public Feeling(String name, int moodModifier, int karmaModifier) {
        this.name = name;
        this.moodModifier = moodModifier;
        this.karmaModifier = karmaModifier;
    }
    public Feeling(String name, int moodModifier) {
        this.name = name;
        this.moodModifier = moodModifier;
    }
    public Feeling(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getMoodModifier() {
        return moodModifier;
    }
    public int getKarmaModifier() {
        return karmaModifier;
    }
    public void apply(Human human) {
        if(moodModifier != 0)
            human.addMood(moodModifier);
        if(karmaModifier != 0)
            human.addKarma(karmaModifier);
        Utils.print(human.getName()+" почувствовал "+name);
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + this.moodModifier;
        hash = 37 * hash + this.karmaModifier;
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
        final Feeling other = (Feeling) obj;
        if (this.moodModifier != other.moodModifier) {
            return false;
        }
        if (this.karmaModifier != other.karmaModifier) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }
    @Override
    public String toString() {
        return this.name;
    }
}
