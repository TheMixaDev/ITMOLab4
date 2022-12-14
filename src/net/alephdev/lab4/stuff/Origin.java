/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.stuff;

/**
 *
 * @author MixaDev
 */
public enum Origin {
    RESIDENT("житель"),
    ALIEN("пришельцы"),
    NOT_STATED("неизвестные");
    private String name;
    Origin(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return this.name;
    }
}
