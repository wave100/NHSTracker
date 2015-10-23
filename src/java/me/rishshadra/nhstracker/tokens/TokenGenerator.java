/*
 * Written by Rish Shadra.
 * There's really not much more to this license header than that.
 * 
 */
package me.rishshadra.nhstracker.tokens;

/**
 *
 * @author Rish
 */
import java.util.UUID;

public class TokenGenerator {

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
