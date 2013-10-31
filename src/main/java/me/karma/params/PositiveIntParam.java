package me.karma.params;

import com.yammer.dropwizard.jersey.params.IntParam;

/**
 * A parameter class for positive (> 0) numbers
 */
public class PositiveIntParam extends IntParam {

    public PositiveIntParam(String input) {
        super(input);
    }

    @Override
    protected String errorMessage(String input, Exception e) {
        return '"' + input + "\" is not a positive number.";
    }

    @Override
    protected Integer parse(String input) {
        Integer value = super.parse(input);

        if (value <= 0)
            throw new NumberFormatException("Value " + input + " cannot be parsed as a positive integer");

        return value;
    }
}
