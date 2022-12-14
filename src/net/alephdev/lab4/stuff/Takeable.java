/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.stuff;

import net.alephdev.lab4.alive.Human;

/**
 *
 * @author MixaDev
 */
public interface Takeable {
    public int getWeight();
    public boolean bind(Human human);
    public boolean unbind();
    public Human getHuman();
}
