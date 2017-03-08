package core;

import junit.framework.TestCase;
import mjs.common.exceptions.CoreException;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;


/**
 * Baseclass for unit tests that need to write to a log
 * file.
 * @author mishoema
 */
public abstract class AbstractHibernateTest extends AbstractLoggableTest {

    protected SessionFactory sessionFactory = null;

    /**
     * Setup the unit test, including loading the 
     * log4j configuration and initialize the Logger
     * objects.
     * @throws Exception
     */
    public void setUp() throws Exception {
        super.setUp();
        sessionFactory = setupHibernate();
    }

    /**
     * Setup the Hibernate connection.
     */
    private SessionFactory setupHibernate() {
        SessionFactory factory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
        log.debug("Hibernate configured and ready.");
        return factory;
    }

}
