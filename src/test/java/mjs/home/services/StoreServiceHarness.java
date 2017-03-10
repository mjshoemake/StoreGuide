package mjs.home.services;

import core.AbstractHibernateTest;
import mjs.model.Franchise;
import mjs.model.Store;

import java.util.List;

public class StoreServiceHarness extends AbstractHibernateTest {


    StoreService storeSvc;
    FranchiseService franchiseSvc;

    public void setUp() throws Exception {
        super.setUp();
        franchiseSvc = new FranchiseService();
        franchiseSvc.setSessionFactory(sessionFactory);
        storeSvc = new StoreService();
        storeSvc.setSessionFactory(sessionFactory);
        log.debug("Setup complete.");
    }

    /**
     * Test method.
     */
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
                store.setName("Test Target");
                store.setAddr1("1525 Market Place Blvd");
                store.setCity("Cumming");
                store.setState("GA");
                store.setZip("30041");
                store.setWebsite("http://www.target.com");
                store.setStore_email("marketplace@target.com");

                // Insert
                int pk = Integer.parseInt(storeSvc.save(store));

                // Update the object with the new pk.
                store.setStore_pk(pk);

                // Check result
                list = storeSvc.getAll();
                if (list.size() == originalCount + 1) {
                    log.info("New store inserted successfully.");
                }

                Store loaded = (Store) storeSvc.getByPK(pk);
                if (loaded.getName().equals("Test Target")) {
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
        } catch (Throwable e) {
            e.printStackTrace();
            assertFailed("Execution with no exceptions.  " + e.getMessage());
        } finally {
            //reportResults();         	
        }
    }

    public static void main(String[] args) {
        StoreServiceHarness harness = new StoreServiceHarness();
        try {
            harness.setUp();
            harness.testManageStores();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
