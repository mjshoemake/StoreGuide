package mjs.home.controllers

import core.AbstractHibernateTest
import groovy.json.JsonSlurper
import groovyx.net.http.ContentType
import groovyx.net.http.RESTClient
import mjs.model.Franchise
import mjs.model.Store

//import groovyx.net.http.HTTPBuilder
class FranchiseControllerHarness extends AbstractHibernateTest {

    // Base URL for REST calls.
    String baseUrl = 'http://localhost:8080/storeGuide/'

    void setUp() throws Exception {
        super.setUp()
        log.debug 'Setup complete.'
    }

    /**
     * Test method. Test franchise CRUD functionality against the live REST API
     * running in Tomcat.
     */
    void testManageFranchises() {
        try {
            // Create RESTClient instance
            def client = new RESTClient(baseUrl)

            // What is the original number of franchises in the DB?
            def json = new URL("${baseUrl}franchises").text
            def list = new JsonSlurper().parseText(json) as List<Franchise>
            int originalCount = list.size()
            if (list != null) {
                log.info "List of franchises retrieved successfully. Count=" + originalCount
            }

            // Define the store properties
            def franchise = [name: 'Test Target',
                             website: 'http://www.target.com',
                             company_email: 'contactus@target.com']

            // Insert
            def resp = client.post(
                    path: 'franchises',
                    body: franchise,
                    requestContentType: ContentType.JSON
            )
            log.debug "POST response status: ${resp.statusLine}"
            int pk = Integer.parseInt(resp.responseData.key)
            log.debug "Generated PK: $pk"
            if (! resp.statusLine.toString().contains("200 OK")) {
                throw new Exception("An error occurred trying to insert a franchise. Response code: ${resp.statusLine}")
            }

            // Update the object with the new pk.
            franchise.franchise_pk = pk

            // Check result
            json = new URL("${baseUrl}franchises").text
            list = new JsonSlurper().parseText(json) as List<Franchise>
            if (list.size() == originalCount + 1) {
                log.info("New franchise inserted successfully.");
            }

            // Get store by pk
            json = new URL("${baseUrl}franchises/${pk}").text
            Franchise loaded = new JsonSlurper().parseText(json) as Franchise
            if (loaded.getName().equals("Test Target")) {
                log.info("Franchise retrieved successfully by PK.");
            }

            // Delete the store that was added.
            resp = client.delete(
                    path: "franchises/${pk}"
            )
            log.debug "DELETE response status: ${resp.statusLine}"
            if (! resp.statusLine.toString().contains("200 OK")) {
                throw new Exception("An error occurred trying to delete a franchise. Response code: ${resp.statusLine}")
            }

            // Check to make sure the store was deleted successfully.
            json = new URL("${baseUrl}franchises").text
            list = new JsonSlurper().parseText(json) as List<Franchise>
            if (list.size() == originalCount) {
                log.info "Franchise deleted successfully."
                log.debug "Test complete. PASSED."
            } else {
                throw new Exception("Franchise was not deleted successfully.")
            }
        } catch (Throwable e) {
            e.printStackTrace()
            assertFailed("Exceptions occurred during execution.  " + e.getMessage())
        } finally {
            //reportResults()
        }
    }

    public static void main(String[] args) {
        FranchiseControllerHarness harness = new FranchiseControllerHarness()
        try {
            harness.setUp()
            harness.testManageFranchises()
        } catch (Exception e) {
            e.printStackTrace()
        }
    }
}
