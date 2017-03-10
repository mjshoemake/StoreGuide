package mjs.home.controllers

import core.AbstractHibernateTest
import groovy.json.JsonSlurper
import mjs.model.Franchise
import mjs.model.Store
import groovyx.net.http.ContentType

//import groovyx.net.http.HTTPBuilder
import groovyx.net.http.RESTClient

class StoreControllerHarness extends AbstractHibernateTest {

    // Franchise
    String franchiseDisplayText = 'franchise'
    String franchiseRestContext = 'franchises'
    String fkColumn = 'franchise_pk'
    String displayText = 'store'
    String pluralDisplayText = 'stores'
    String restContext = 'stores'
    String pkColumn = 'store_pk'


    // Base URL for REST calls.
    String baseUrl = 'http://localhost:8080/storeGuide/'

    void setUp() throws Exception {
        super.setUp()
        log.debug 'Setup complete.'
    }

    /**
     * Test method. Test store CRUD functionality against the live REST API
     * running in Tomcat.
     */
    void testManageStores() {
        try {
            // Create RESTClient instance
            def client = new RESTClient(baseUrl)

            // Get a franchise from the DB using REST.
            String json = new URL("${baseUrl}${franchiseRestContext}").text
            List list = new JsonSlurper().parseText(json) as List<Franchise>

            log.debug "Loaded $franchiseDisplayText list.  size=${list.size()}"
            if (list.size() > 0) {
                Franchise franchise = list.get(0)
                log.debug "${fkColumn} = ${franchise.franchise_pk}"

                // What is the original number of stores in the DB?
                json = new URL("${baseUrl}${restContext}").text
                list = new JsonSlurper().parseText(json) as List<Store>
                int originalCount = list.size()
                if (list != null) {
                    log.info "List of stores retrieved successfully. Count=" + originalCount
                }

                // Define the itemToDelete properties
                def itemToDelete = [franchise_pk: franchise.getFranchise_pk(),
                                    name: 'Test Target',
                                    addr1: '1525 Market Place Blvd',
                                    city: 'Cumming',
                                    state: 'GA',
                                    zip: '30041',
                                    website: 'http://www.target.com',
                                    store_email: 'marketplace@target.com']

                // Insert
                def resp = client.post(
                        path: restContext,
                        body: itemToDelete,
                        requestContentType: ContentType.JSON
                )
                log.debug "POST response status: ${resp.statusLine}"
                int pk = Integer.parseInt(resp.responseData.key)
                log.debug "Generated PK: $pk"
                if (! resp.statusLine.toString().contains("200 OK")) {
                    throw new Exception("An error occurred trying to delete a ${displayText}. Response code: ${resp.statusLine}")
                }

                // Update the object with the new pk.
                itemToDelete.store_pk = pk

                // Check result
                json = new URL("${baseUrl}${restContext}").text
                list = new JsonSlurper().parseText(json) as List<Store>
                if (list.size() == originalCount + 1) {
                    log.info("New itemToDelete inserted successfully.");
                }

                // Get itemToDelete by pk
                json = new URL("${baseUrl}${restContext}/${pk}").text
                Store loaded = new JsonSlurper().parseText(json) as Store
                if (loaded.getName().equals("Test Target")) {
                    log.info("Store retrieved successfully by PK.");
                }

                // Delete the itemToDelete that was added.
                resp = client.delete(
                        path: "stores/${pk}"
                )
                log.debug "DELETE response status: ${resp.statusLine}"
                if (! resp.statusLine.toString().contains("200 OK")) {
                    throw new Exception("An error occurred trying to delete a ${displayText}. Response code: ${resp.statusLine}")
                }

                // Check to make sure the itemToDelete was deleted successfully.
                json = new URL("${baseUrl}stores").text
                list = new JsonSlurper().parseText(json) as List<Store>
                if (list.size() == originalCount) {
                    log.info "Store deleted successfully."
                    log.info "Test complete. PASSED."
                } else {
                    throw new Exception("Franchise was not deleted successfully.")
                }
            } else {
                log.info "Failed to execute test.  No franchises in the franchise table."
            }
        } catch (Throwable e) {
            e.printStackTrace();
            assertFailed("Exceptions occurred during execution.  " + e.getMessage());
        } finally {
            //reportResults();         	
        }
    }

    public static void main(String[] args) {
        StoreControllerHarness harness = new StoreControllerHarness();
        try {
            harness.setUp();
            harness.testManageStores();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
