/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.stuff;

import java.util.List;
import net.alephdev.lab4.alive.Human;

/**
 *
 * @author MixaDev
 */
public interface Owned {
    public List<Human> getOwners();
    public void addOwner(Human human);
    public void removeOwner(Human human);
    public void removeOwner(int index);
}
