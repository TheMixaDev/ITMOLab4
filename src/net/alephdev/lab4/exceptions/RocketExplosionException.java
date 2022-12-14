/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.exceptions;

import net.alephdev.lab4.inanimate.rocket.Rocket;

/**
 *
 * @author MixaDev
 */
public class RocketExplosionException extends RuntimeException {
    private final Rocket rocket;
    public RocketExplosionException(String message, Rocket rocket) {
        super(message);
        this.rocket = rocket;
    }
    public Rocket getRocket() {
        return rocket;
    }
    public int getLoss() {
        return rocket.getModules().size()*10000;
    }
}
