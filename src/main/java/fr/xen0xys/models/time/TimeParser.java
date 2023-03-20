package fr.xen0xys.models.time;

import org.jetbrains.annotations.NotNull;

public class TimeParser {
    public static long parse(@NotNull final String time){
        int value = Integer.parseInt(time.substring(0, time.length() - 1));
        char unit = time.charAt(time.length() - 1);
        return switch (unit) {
            case 's' -> value * 1000L;
            case 'm' -> (long) value * 60 * 1000;
            case 'h' -> (long) value * 60 * 60 * 1000;
            case 'd' -> (long) value * 24 * 60 * 60 * 1000;
            case 'w' -> (long) value * 7 * 24 * 60 * 60 * 1000;
            default -> throw new IllegalArgumentException("Invalid time unit");
        };
    }
}
