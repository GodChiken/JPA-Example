package service;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.mariadb.jdbc.MySQLDataSource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/**
 * Created by Kim Donghoon on 2016-02-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")
@Transactional
public abstract class SpringTest {
    @PersistenceContext
    protected EntityManager em;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // rcarver - setup the jndi context and the datasource
        try {
            // Create initial context
            System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
            System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
            InitialContext ic = new InitialContext();

            ic.createSubcontext("java:");

            // Construct DataSource
            MySQLDataSource dataSource = new MySQLDataSource();

            dataSource.setUrl("jdbc:mysql://localhost:3306/jpa_12");
            dataSource.setUserName("root");
            dataSource.setPassword("rhkfh1");

            ic.bind("java:/jpa12", dataSource);
        } catch (NamingException ex) {
            ex.printStackTrace();
        }

    }
}
