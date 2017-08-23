package mjs.home.services;

import java.util.List;
import core.AbstractHibernateTest;
import mjs.common.utils.LogUtils;
import mjs.home.services.UserService;
import mjs.model.User;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.Before;
import org.junit.Test;

public class UserServiceTest extends AbstractHibernateTest {

    UserService svc;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        svc = new UserService();
        svc.setSessionFactory(sessionFactory);
        svc.setJDBCConnectionExceptionIgnored(true);
        log.debug("Setup complete.");
    }

    /**
     * Test method.
     */
    @Test
    public void testManageUsers() {
        try {
            // Check result
            List list = svc.getAll();
            int originalCount = list.size();
            if (list!= null) {
                log.info("List of users retrieved successfully.");
            }

            User user = new User();
            user.setFname("Bob");
            user.setLname("Tester");
            user.setCity("Alpharetta");
            user.setState("GA");
            user.setUsername("btester");
            user.setEmail("btester@gmail.com");
            user.setPassword("mypass");
            user.setLogin_enabled("Y");

            // Insert the user.
            int pk = Integer.parseInt(svc.save(user));

            // Update the user object with the new pk.
            user.setUser_pk(pk);

            // Check result
            list = svc.getAll();
            if (list.size() == originalCount + 1) {
                log.info("New user inserted successfully.");
            }

            User loaded = (User)svc.getByPK(pk);
            if (loaded.getFname().equals("Bob")) {
                log.info("User retrieved successfully by PK.");
            }

            // Delete the user.
            svc.delete(user);

            // Check result
            list = svc.getAll();
            if (list.size() == originalCount) {
                log.info("User deleted successfully.");
            }

        } catch (Exception e) {
            if (! e.getMessage().contains("Could not open connection")) {
                e.printStackTrace();
                assertFailed("Execution with no exceptions.  " + e.getMessage());
            } else {
                // Ignore connection issues.  This means that the DB isn't running, which may be the case
                // under certain circumstances (ex. build from Jenkins).  In those cases, this test is not
                // valid.
            }
        } finally {
            //reportResults();         	
        }
    }
}
