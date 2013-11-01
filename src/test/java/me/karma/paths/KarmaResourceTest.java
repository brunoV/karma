package me.karma.paths;

import com.yammer.dropwizard.testing.ResourceTest;
import me.karma.core.Karma;
import me.karma.db.KarmaDAO;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KarmaResourceTest extends ResourceTest {

    private Karma karma = new Karma("foo", 42);
    private KarmaDAO mockDao = mock(KarmaDAO.class);

    @Test
    public void testGetKarma() throws Exception {

        assertThat("Basic karma resource path should return the correct Karma object",
                client().resource("/" + karma.getName()).get(Karma.class), equalTo(karma));

        assertThat("Inexistent entries are represented by Karma objects with value of 0",
                client().resource("/" + "inexistent").get(Karma.class).getValue(), equalTo(0));
    }

    @Override
    protected void setUpResources() throws Exception {
        when(mockDao.get("foo")).thenReturn(karma.getValue());
        when(mockDao.get("inexistent")).thenReturn(null);

        when(mockDao.insert("foo", 420)).thenReturn(1);
        addResource(new KarmaResource(mockDao));
    }
}
