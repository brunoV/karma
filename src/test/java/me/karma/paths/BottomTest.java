package me.karma.paths;

import com.google.common.collect.ImmutableList;
import com.sun.jersey.api.client.GenericType;
import com.yammer.dropwizard.testing.ResourceTest;
import me.karma.core.Karma;
import me.karma.db.KarmaDAO;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BottomTest extends ResourceTest {

    private Karma karma = new Karma("foo", 42);
    private KarmaDAO mockDao = mock(KarmaDAO.class);

    @Test
    public void testBottom() throws Exception {
        assertThat("Basic karma resource path should return the correct Karma object",
                client().resource("/bottom/1").get(new GenericType<List<Karma>>(){}),
                equalTo(Arrays.asList(karma)));
    }

    @Override
    protected void setUpResources() throws Exception {
        when(mockDao.bottom(anyInt())).thenReturn(ImmutableList.of(karma));
        addResource(new Bottom(mockDao));
    }
}
