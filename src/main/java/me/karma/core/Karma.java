package me.karma.core;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * A representation class of the karma value of something
 */
public class Karma {
    private final int value;

    @NotEmpty
    private final String name;

    public Karma(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
