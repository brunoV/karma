package me.karma.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * A representation class of the karma value of something
 */
public class Karma {
    @NotEmpty
    private final String name;

    @NotNull
    private final Integer value;

    public Karma(@JsonProperty("name") String name, @JsonProperty("value") Integer value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o.getClass() != this.getClass()) return false;

        Karma other = (Karma) o;
        return Objects.equal(name, other.name) && value == other.value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, value);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", name).add("value", value).toString();
    }
}
