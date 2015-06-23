package alex.entity;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

public class PageTest extends AbstractEntityTest {

    @Test
    public void creation() throws Exception {
        Page page = new Page();
        assertThat(page.getId(), is(0));
        assertThat(page.getTitle(), nullValue());
        assertThat(page.getContent(), nullValue());
        assertThat(page.isPublicPage(), is(false));
    }
}