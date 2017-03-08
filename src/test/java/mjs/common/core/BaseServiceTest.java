package mjs.common.core;

import java.util.Map;
import core.AbstractHibernateTest;
import mjs.common.utils.LogUtils;
import mjs.model.User;
import org.junit.Before;
import org.junit.Test;

public class BaseServiceTest extends AbstractHibernateTest {

    BaseService obj;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        obj = new BaseService(User.class.getName(), "user", "fname+lname", "user_pk", "users");
        log.debug("Setup complete.");
    }

    /**
     * Test method.
     */
    @Test
    public void testFilterToMap() {
        try {
            String filter = "fname=Mi*;lname=Sh*";
            Map result = obj.filterToMap(filter);

            LogUtils.println(result, "   ", true);
            assertTrue("Two keys in map.", result.size() == 2);
            assertTrue(result.get("fname").equals("Mi*"));
            assertTrue(result.get("lname").equals("Sh*"));

        	System.out.println("Test complete.  Exiting.");

        } catch (Throwable e) {
            e.printStackTrace();
            assertFailed("Execution with no exceptions.  " + e.getMessage());
        } finally {
            //reportResults();         	
        }
    }


}
