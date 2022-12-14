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
public class RichStratum extends SocialGroup {
    private final int welth = Integer.MAX_VALUE;
    private int amount = 1;
    private List<Object> items = new ArrayList<>();
    public RichStratum(String name, Planet planet, int amount) {
        super(name, planet, Origin.RESIDENT);
        this.amount = amount;
    }
    public RichStratum(String name, Planet planet, Origin origin) {
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
    public void obtain(Object toBuy) {
        if(toBuy instanceof RichStratum) {
            Utils.print("Люди с бесконечным богатством не могут выкупить друг друга!");
            return;
        }
        items.add(toBuy);
        Utils.print("Богачи выкупили себе "+toBuy.toString());
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.welth;
        hash = 31 * hash + this.amount;
        hash = 31 * hash + Objects.hashCode(this.items);
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
        final RichStratum other = (RichStratum) obj;
        if (this.welth != other.welth) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        return Objects.equals(this.items, other.items);
    }
    @Override
    public String toString() {
        return this.getName();
    }
}
