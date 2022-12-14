/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.alive.groups;

import net.alephdev.lab4.Utils;
import net.alephdev.lab4.alive.SocialGroup;
import net.alephdev.lab4.inanimate.Planet;
import net.alephdev.lab4.inanimate.rocket.Rocket;
import net.alephdev.lab4.stuff.Origin;

/**
 *
 * @author MixaDev
 */
public class Citizens extends SocialGroup {
    public Citizens(String name, Planet planet, Origin origin) {
        super(name, planet, origin);
    }
    public boolean canSendSpaceship(Rocket rocket, Planet destination) {
        if(!rocket.isDestroyed()) {
            Utils.print(this.getName()+" планеты "+this.getPlanet().getName()+" могут отправить "+rocket.getName()+" на "+destination.getName());
            return true;
        }
        Utils.print("У "+this.getName()+" планеты "+this.getPlanet().getName()+" взорвана ракета, они не могут отправить корабли");
        return false;
    }
}
