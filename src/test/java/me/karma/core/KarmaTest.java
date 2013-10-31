package me.karma.core;

import com.yammer.dropwizard.validation.Validator;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static com.yammer.dropwizard.testing.JsonHelpers.*;

public class KarmaTest {
    Karma fooKarma;
    Validator validator;

    @Before
    public void setUp() throws Exception {
        fooKarma = new Karma("foo", 42);
        validator = new Validator();
    }

    @Test
    public void serializesToJson() throws Exception {
        assertThat(asJson(fooKarma), equalTo(jsonFixture("fixtures/karma.json")));
    }

    @Test
    public void deserializesFromJson() throws Exception {
        assertThat(fromJson(jsonFixture("fixtures/karma.json"), Karma.class), equalTo(fooKarma));
    }

    @Test
    public void testNullName() throws Exception {
        Karma karma = fromJson(jsonFixture("fixtures/karma-null-name.json"), Karma.class);
        List<String> errors = validator.validate(karma);
        assertThat(errors.size(), is(not(0)));
    }

    @Test(expected = Exception.class)
    public void testInvalidValue() throws Exception {
        fromJson(jsonFixture("fixtures/karma-invalid-value.json"), Karma.class).toString();
    }

    @Test
    public void testNullValue() throws Exception {
        Karma karma = fromJson(jsonFixture("fixtures/karma-null-value.json"), Karma.class);
        System.out.println(karma);
        List<String> errors = validator.validate(karma);
        assertThat(errors.size(), is(not(0)));
    }

    @Test
    public void testEmptyName() throws Exception {
        Karma karma = fromJson(jsonFixture("fixtures/karma-empty-name.json"), Karma.class);
        List<String> errors = validator.validate(karma);
        assertThat(errors.size(), is(not(0)));
    }

    @Test
    public void testGetValue() throws Exception {
        Integer value = 42;
        assertThat(new Karma("foo", value).getValue(), is(value));
    }

    @Test
    public void testGetName() throws Exception {
        String name = "foo";
        assertThat(new Karma(name, 42).getName(), is(name));
    }
}
