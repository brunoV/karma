package me.karma.core;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * A representation class of the karma value of something
 */
public class Karma {
    @NotNull
    private final String name;
    private final int value;

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
