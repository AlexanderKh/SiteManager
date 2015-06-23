package alex.service;

import alex.config.AppTestConfig;
import alex.dao.PageDAO;
import alex.dao.PermissionDAO;
import alex.dao.UserDAO;
import alex.service.impl.PageServiceImpl;
import alex.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppTestConfig.class})
public abstract class AbstractServiceTest {
    @Mock
    PageDAO pageDAO;
    @Mock
    UserDAO userDAO;
    @Mock
    PermissionDAO permissionDAO;
    @Autowired
    PageServiceImpl pageService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PermissionService permissionService;

    @Before
    public void setUp() throws Exception {
        pageDAO = mock(PageDAO.class);
        userDAO = mock(UserDAO.class);
        permissionDAO = mock(PermissionDAO.class);
        pageService.setPageDAO(pageDAO);
        userService.setUserDAO(userDAO);
        permissionService.setPermissionDAO(permissionDAO);
    }

}
