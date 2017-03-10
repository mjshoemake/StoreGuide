 package mjs.model

import mjs.common.model.ModelLoggable
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table
import javax.persistence.Transient;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This is the data object or suitcase for a Franchise. This data object
 * should not contain any business logic. This class is used by the
 * AuthenticationManager.
 */
 @Entity
 @Table(name="store")
 @JsonIgnoreProperties(ignoreUnknown = true)
class Store extends ModelLoggable {
     /**
      * The PK for this object. This is how an object should be
      * referenced in the database.
      */
     @Id
     @GeneratedValue
     int store_pk = -1

     /**
      * Foreign key from store to franchise.
      */
     int franchise_pk = -1

     /**
      * The name of the store.
      */
     String name = ""

     /**
      * Address line 1.
      */
     String addr1 = ""

     /**
      * Address line 1.
      */
     String addr2 = ""

     /**
      * Address line 1.
      */
     String addr3 = ""

     /**
      * Address line 1.
      */
     String addr4 = ""

     /**
      * The store's city.
      */
     String city = ""

     /**
      * The store's state.
      */
     String state = ""

     /**
      * The store's zip.
      */
     String zip = ""

     /**
      * The store's website URL.
      */
     String website = ""

     /**
      * The store's email address.
      */
     String store_email = ""

     /**
      * The searchable string used for keyword searches.
      * @param value String
      */
     public void setSearchable(String value) {
         // Do nothing.
     }

     public String getSearchable() {
         try {
             StringBuilder builder = new StringBuilder("|")
             if (name != null) {
                 builder.append(name).append('|')
             }
             if (addr1 != null) {
                 builder.append(addr1).append('|')
             }
             if (addr2 != null) {
                 builder.append(addr2).append('|')
             }
             if (city != null) {
                 builder.append(city).append(', ')
             }
             if (state != null) {
                 builder.append(state).append(' ')
             }
             if (zip != null) {
                 builder.append(zip).append('|')
             }
             if (website != null) {
                 builder.append(website).append('|')
             }
             if (store_email != null) {
                 builder.append(store_email).append('|')
             }
             return builder.toString()
         } catch (Exception e) {
             log.error("An error occurred processing Store.getSearchable().", e);
             return "||";
         }
     }


 }
