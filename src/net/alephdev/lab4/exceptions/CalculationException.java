/*
 * Created by MixaDev
 * https://vk.com/mixadev
 */
package net.alephdev.lab4.exceptions;

/**
 *
 * @author MixaDev
 */
public class CalculationException extends Exception {
    private final Object calculationObject;
    public CalculationException(String message, Object calculationObject) {
        super(message);
        this.calculationObject = calculationObject;
    }
    public Object getCalculationObject() {
        return calculationObject;
    }
}
