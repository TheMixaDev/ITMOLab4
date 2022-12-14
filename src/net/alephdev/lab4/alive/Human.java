/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.alive;

import java.util.ArrayList;
import java.util.List;
import net.alephdev.lab4.Utils;
import net.alephdev.lab4.stuff.Location;
import net.alephdev.lab4.stuff.Observable;
import net.alephdev.lab4.stuff.Owned;
import net.alephdev.lab4.stuff.Takeable;

/**
 *
 * @author MixaDev
 */
public class Human {
    private final String name;
    private int karma = 50;
    private int mood = 50;
    private int saturation = 70;
    private int hydration = 70;
    private Location location;
    private int strength = 10;
    private Hand rightArm = new Hand("правая");
    private Hand leftArm = new Hand("левая");
    private List<Hypothesis> hypotheses = new ArrayList<>();

    public Human(String name, Location location) {
        this.name = name;
        this.location = location;
    }
    public String getName() {
        return name;
    }
    public void checkCriticalStats() {
        if(mood < 1)
            Utils.print(this.name+" впал в депрессию");
        if(saturation < 10) {
            Utils.print(this.name+" невероятно голоден");
            eat((int)Math.round(Math.random()*20)+20);
        }
        if(hydration < 10) {
            Utils.print(this.name+" очень хочет попить воды");
            drink((int)Math.round(Math.random()*20)+20);
        }
    }
    public void passiveStatsReduce() {
        if(Math.random() < 0.5)
            reduceMood(1);
        if(Math.random() < 0.5)
            saturation -= 1;
        if(Math.random() < 0.5)
            hydration -= 1;
        checkCriticalStats();
    }
    public void eat(int effectiveness) {
        this.saturation += effectiveness;
        Utils.print(this.name+" поел и сыт теперь на дополнительные +"+effectiveness+"%");
    }
    public void drink(int effectiveness) {
        this.hydration += effectiveness;
        Utils.print(this.name+" попил и насыщен теперь на дополнительные +"+effectiveness+"%");
    }
    public void printStats() {
        Utils.print(this.name+" в хорошем настроении на "+this.mood+"%, сыт на "+saturation+"% и насыщен на "+hydration+"%");
    }
    public int getKarma() {
        return karma;
    }
    public void addKarma(int karma) {
        this.karma += karma;
    }
    public void reduceKarma(int karma) {
        this.addKarma(-karma);
    }
    public int getMood() {
        return mood;
    }
    public void addMood(int mood) {
        this.mood += mood;
        if(this.mood < 0)
            this.mood = 0;
    }
    public void reduceMood(int mood) {
        this.addMood(-mood);
    }
    public int getStrength() {
        return strength;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public Hand getRightArm() {
        return rightArm;
    }
    public Hand getLeftArm() {
        return leftArm;
    }
    public void addHypothesis(Hypothesis hypothesis) {
        hypotheses.add(hypothesis);
    }
    public void removeHypothesis(Hypothesis hypothesis) {
        hypotheses.remove(hypothesis);
    }
    public void removeHypothesis(int index) {
        hypotheses.remove(index);
    }
    public void clearHypothesis() {
        hypotheses.clear();
    }
    public List<Hypothesis> getHypothesis() {
        return hypotheses;
    }
    public boolean isDepressed(boolean isNeedHelp) {
        if(mood < 1) {
            if(isNeedHelp)
                Utils.print(this.name+" нуждается в помощи.");
            else
                Utils.print(this.name+" не имеет мотивации что-либо делать в депрессии.");
            return true;
        }
        return false;
    }
    public void takeItem(Takeable item) {
        if(isDepressed(false)) return;
        if(item.getWeight() > strength) {
            Utils.print(getName()+" не может взять "+item.toString()+" поскольку он слишком тяжелый");
            return;
        }
        boolean success = false;
        if(rightArm.getContent() == null) {
            success = rightArm.setContent(item);
        } else if(leftArm.getContent() == null) {
            success = leftArm.setContent(item);
        } else {
            Utils.print("У "+getName()+" заняты руки, и потому он не может взять "+this.toString());
            return;
        }
        if(success)
            passiveStatsReduce();
    }
    public void dropRightArmContent() {
        if(isDepressed(false)) return;
        if(rightArm.dropContent())
            passiveStatsReduce();
    }
    public void dropLeftArmContent() {
        if(isDepressed(false)) return;
        if(leftArm.dropContent())
            passiveStatsReduce();
    }
    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    public void look(Observable observing) {
        if(isDepressed(false)) return;
        Utils.print(this.name+" смотрит на "+observing+" из "+location.getName());
        Object observeResults = observing.observe();
        if(observeResults != null && observeResults instanceof Observable)
            addMood(5);
        else
            reduceMood(3);
        passiveStatsReduce();
    }
    public void thinkProcess(int time) {
        if(isDepressed(false)) return;
        addMood(time);
        addKarma((int)(time/2));
        Utils.print(this.name+" поразмылшлял в течении "+time+" минут");
        passiveStatsReduce();
    }
    public void notPermitTo(Location location, SocialGroup group) {
        notPermitTo(location, group, null);
    }
    public void notPermitTo(Location location, SocialGroup group, Object additional) {
        if(isDepressed(false)) return;
        group.reduceRating(2);
        group.addBannedLocation(location);
        reduceMood(2);
        Utils.print(this.name+" не пустит на "+location+" "+group.toString(this)+(additional == null ? "" : " с "+additional));
        passiveStatsReduce();
    }
    public void callCommunity(Community community) {
        if(isDepressed(false)) return;
        if(!community.isActive()) {
            Utils.print(this.name+" не может обратиться к исчезновшому "+community.toString());
            return;
        }
        community.setLocation(getLocation());
        Utils.print(this.name+" призвал "+community.toString());
        passiveStatsReduce();
    }
    public void wantVisit(Location location) {
        if(isDepressed(false)) return;
        Utils.print(this.name+" хочет посетить "+location.getName());
        addMood(5);
        passiveStatsReduce();
    }
    public void wantCheckHypothesis() {
        if(isDepressed(false)) return;
        if(getHypothesis().isEmpty()) {
            Utils.print("У "+this.name+" нет гипотез, требующих проверки");
            return;
        }
        Utils.print(this.name+" хочет проверить "+getHypothesis().size()+" гипотиз");
        addMood(5);
        passiveStatsReduce();
    }
    public void commitParticipation(Owned object) {
        if(object.getOwners().contains(this)) {
            Utils.print(this.name+" уже зафиксировал свое участие в "+object.toString());
            return;
        }
        object.addOwner(this);
        Utils.print(this.name+" учавствовал в создании "+object.toString());
    }
    public class Hand {
        private Takeable content = null;
        private final String side;
        public Hand(String side) {
            this.side = side;
        }
        public Takeable getContent() {
            return content;
        }
        private boolean setContent(Takeable item) {
            if(content == null) {
                content = item;
                item.bind(Human.this);
                Utils.print(getName()+" взял в "+side+" руку "+this.toString());
                return true;
            }
            return false;
        }
        private boolean dropContent() {
            if(content == null) return false;
            Utils.print(getName()+" отпустил "+content.toString());
            content.unbind();
            content = null;
            return true;
        }
        @Override
        public String toString() {
            return this.side+" рука "+name;
        }
    }
    public static class Hypothesis {
        private String name;
        private int difficulty;

        public Hypothesis(String name, int difficulty) {
            this.name = name;
            this.difficulty = difficulty;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public int getDifficulty() {
            return difficulty;
        }
        public void setDifficulty(int difficulty) {
            this.difficulty = difficulty;
        }
        @Override
        public String toString() {
            return "гипотеза "+this.name;
        }
    }
}
