/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.alive.groups;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.alephdev.lab4.Utils;
import net.alephdev.lab4.alive.SocialGroup;
import net.alephdev.lab4.inanimate.Planet;
import net.alephdev.lab4.stuff.Origin;

/**
 *
 * @author MixaDev
 */
public class PoorStratum extends SocialGroup {
    private final int welth = Integer.MIN_VALUE;
    private int amount = 1;
    private Object dependence;
    private List<Object> banned = new ArrayList<>();
    public PoorStratum(String name, Planet planet, int amount) {
        super(name, planet, Origin.RESIDENT);
        this.amount = amount;
    }
    public PoorStratum(String name, Planet planet, Origin origin) {
        super(name, planet, origin);
    }
    public int getWelth() {
        return welth;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public Object getDependence() {
        return dependence;
    }
    public void setDependence(Object dependence) {
        this.dependence = dependence;
        Utils.print(this.getName()+" находятся в повиновении "+dependence.toString());
    }
    public void addBan(Object ban) {
        banned.add(ban);
        Utils.print(this.getName()+" не могут воспользоваться "+ban.toString());
    }
    public void addBan(Object ban, Planet planet) {
        banned.add(ban);
        Utils.print(this.getName()+" не могут воспользоваться "+ban.toString()+" на планете "+planet.getName());
    }
    public List<Object> getBans() {
        return banned;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.welth;
        hash = 19 * hash + this.amount;
        hash = 19 * hash + Objects.hashCode(this.dependence);
        hash = 19 * hash + Objects.hashCode(this.banned);
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
        final PoorStratum other = (PoorStratum) obj;
        if (this.welth != other.welth) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        if (!Objects.equals(this.dependence, other.dependence)) {
            return false;
        }
        return Objects.equals(this.banned, other.banned);
    }
    @Override
    public String toString() {
        return this.getName();
    }
}
