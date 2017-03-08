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
 @Table(name="franchise")
 @JsonIgnoreProperties(ignoreUnknown = true)
class Franchise extends ModelLoggable {
     /**
      * The PK for this object. This is how an object should be
      * referenced in the database.
      */
     @Id
     @GeneratedValue
     int franchise_pk = -1

     /**
      * The name of the franchise.
      */
     String name = ""

     /**
      * The frachise's website.
      */
     String website = ""

     /**
      * The frachise's company email if applicable. Otherwise, null.
      */
     String company_email = ""

 }
