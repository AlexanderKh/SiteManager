package alex.service;

import alex.config.AppTestConfig;
import alex.dao.UserDAO;
import alex.entity.User;
import alex.entity.UserGroup;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserDetailsServiceImpl_IntegrationTest {
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    SessionFactory sessionFactory;
    User user;

    @Before
    public void setUp() throws Exception {
        user = new User("Test User", UserGroup.ADMIN);
        user.setPassword("Test Pass");
        userDAO.saveUser(user);
        evict(user);

        flush();
    }

    protected void evict(Object o) {
        sessionFactory.getCurrentSession().evict(o);
    }

    protected void flush() {
        sessionFactory.getCurrentSession().flush();
    }

    @Test
    public void testLoadUserByUsername() throws Exception {
        UserDetails actualUserDetails = userDetailsService.loadUserByUsername("Test User");
        SimpleGrantedAuthority expectedAuthority = new SimpleGrantedAuthority("ADMIN");

        assertThat(actualUserDetails.getUsername(), is("Test User"));
        assertThat(actualUserDetails.getPassword(), is("Test Pass"));
        assertThat(actualUserDetails.isAccountNonExpired(), is(true));
        assertThat(actualUserDetails.isAccountNonLocked(), is(true));
        assertThat(actualUserDetails.isCredentialsNonExpired(), is(true));
        assertThat(actualUserDetails.isEnabled(), is(true));
        assertThat(actualUserDetails.getAuthorities().contains(expectedAuthority), is(true));
    }
}