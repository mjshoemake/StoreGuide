package mjs.home.services;

import java.util.List;
import core.AbstractHibernateTest;
import mjs.common.utils.LogUtils;
import mjs.home.services.StoreService;
import mjs.model.Franchise;
import mjs.model.Store;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.Before;
import org.junit.Test;

public class StoreServiceTest extends AbstractHibernateTest {

    StoreService storeSvc;
    FranchiseService franchiseSvc;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        franchiseSvc = new FranchiseService();
        franchiseSvc.setSessionFactory(sessionFactory);
        franchiseSvc.setJDBCConnectionExceptionIgnored(true);
        storeSvc = new StoreService();
        storeSvc.setSessionFactory(sessionFactory);
        storeSvc.setJDBCConnectionExceptionIgnored(true);
        log.debug("Setup complete.");
    }

    /**
     * Test method.
     */
    @Test
    public void testManageStores() {
        try {
            // Get a franchise from the list.
            List list = franchiseSvc.getAll();
            log.debug("Loaded franchise list.  size="+list.size());
            if (list.size() > 0) {
                Franchise franchise = (Franchise) list.get(0);
                log.debug("franchise_pk = " + franchise.getFranchise_pk());

                // Check result
                list = storeSvc.getAll();
                int originalCount = list.size();
                if (list!= null) {
                    log.info("List of stores retrieved successfully. Count=" + originalCount);
                }

                Store store = new Store();
                store.setFranchise_pk(franchise.getFranchise_pk());
                store.setName("Target");
                store.setAddr1("1525 Market Place Blvd");
                store.setCity("Cumming");
                store.setState("GA");
                store.setZip("30041");
                store.setWebsite("http://www.target.com");
                store.setStore_email("marketplace@target.com");

                // Insert
                int pk = Integer.parseInt(storeSvc.save(store));

                // Update the object with the new pk.
                store.setFranchise_pk(pk);

                // Check result
                list = storeSvc.getAll();
                if (list.size() == originalCount + 1) {
                    log.info("New store inserted successfully.");
                }

                Store loaded = (Store)storeSvc.getByPK(pk);
                if (loaded.getAddr1().equals("1525 Market Place Blvd")) {
                    log.info("Store retrieved successfully by PK.");
                }

                // Delete
                storeSvc.delete(store);

                // Check result
                list = storeSvc.getAll();
                if (list.size() == originalCount) {
                    log.info("Store deleted successfully.");
                }
            } else {
                log.info("Failed to execute test.  No franchises in the franchise table.");
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
