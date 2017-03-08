package mjs.home.services;

import java.util.List;
import core.AbstractHibernateTest;
import mjs.common.utils.LogUtils;
import mjs.home.services.FranchiseService;
import mjs.model.Franchise;
import org.junit.Before;
import org.junit.Test;

public class FranchiseServiceTest extends AbstractHibernateTest {

    FranchiseService svc;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        svc = new FranchiseService();
        svc.setSessionFactory(sessionFactory);
        log.debug("Setup complete.");
    }

    /**
     * Test method.
     */
    @Test
    public void testManageFranchises() {
        try {
            // Check result
            List list = svc.getAll();
            int originalCount = list.size();
            if (list!= null) {
                log.info("List of franchises retrieved successfully. Count=" + originalCount);
            }

            Franchise franchise = new Franchise();
            franchise.setName("Target");
            franchise.setWebsite("http://www.target.com");
            franchise.setCompany_email("support@target.com");

            // Insert
            int pk = Integer.parseInt(svc.save(franchise));

            // Update the object with the new pk.
            franchise.setFranchise_pk(pk);

            // Check result
            list = svc.getAll();
            if (list.size() == originalCount + 1) {
                log.info("New franchise inserted successfully.");
            }

            Franchise loaded = (Franchise)svc.getByPK(pk);
            if (loaded.getName().equals("Target")) {
                log.info("Franchise retrieved successfully by PK.");
            }

            // Delete
            svc.delete(franchise);

            // Check result
            list = svc.getAll();
            if (list.size() == originalCount) {
                log.info("Franchise deleted successfully.");
            }

        } catch (Throwable e) {
            e.printStackTrace();
            assertFailed("Execution with no exceptions.  " + e.getMessage());
        } finally {
            //reportResults();         	
        }
    }
}
