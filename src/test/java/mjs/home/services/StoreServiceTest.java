package mjs.home.services;

import java.util.List;
import core.AbstractHibernateTest;
import mjs.common.utils.LogUtils;
import mjs.home.services.StoreService;
import mjs.model.Store;
import org.junit.Before;
import org.junit.Test;

public class StoreServiceTest extends AbstractHibernateTest {

    StoreService svc;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        //svc = new StoreService();
        //svc.setSessionFactory(sessionFactory);
        log.debug("Setup complete.");
    }

    /**
     * Test method.
     */
    @Test
    public void testManageStores() {
        try {
            // Check result
/*
            List list = svc.getAll();
            int originalCount = list.size();
            if (list!= null) {
                log.info("List of stores retrieved successfully. Count=" + originalCount);
            }

            Store store = new Store();
            store.setName("Target");
            store.setAddr1("1525 Market Place Blvd");
            store.setCity("Cumming");
            store.setState("GA");
            store.setZip("30041");
            store.setWebsite("http://www.target.com");
            store.setStore_email("marketplace@target.com");

            // Insert
            int pk = Integer.parseInt(svc.save(store));

            // Update the object with the new pk.
            store.setFranchise_pk(pk);

            // Check result
            list = svc.getAll();
            if (list.size() == originalCount + 1) {
                log.info("New store inserted successfully.");
            }

            Store loaded = (Store)svc.getByPK(pk);
            if (loaded.getAddr1().equals("1525 Market Place Blvd")) {
                log.info("Store retrieved successfully by PK.");
            }

            // Delete
            svc.delete(store);

            // Check result
            list = svc.getAll();
            if (list.size() == originalCount) {
                log.info("Store deleted successfully.");
            }
*/
        } catch (Throwable e) {
            e.printStackTrace();
            assertFailed("Execution with no exceptions.  " + e.getMessage());
        } finally {
            //reportResults();         	
        }
    }
}
