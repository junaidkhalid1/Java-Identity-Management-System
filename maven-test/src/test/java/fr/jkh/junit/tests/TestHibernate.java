/**
 * 
 */
package fr.jkh.junit.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jkh.iam.datamodel.Identity;
import fr.jkh.iamcore.services.DAO;

/**
 * @author Junaid KHALID
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestHibernate {

	@Autowired
	DataSource ds;

	@Autowired
	SessionFactory sf;

	@Autowired
	DAO dao;

	@Test
	public void testDataSource() throws SQLException {
		Assert.assertNotNull(ds);
		Connection connection = ds.getConnection();
		connection.close();
	}

	@Test
	public void testHibernate() {
		Assert.assertNotNull(sf);
		Session session = sf.openSession();
		session.close();// TODO do not do that outside of the test case
	}

	@Test
	public void testHibernateSaveOrUpdate() {
		Identity identity = new Identity();
		identity.setDisplayName("Junaid");
		identity.setBirthDate(new Date());
		Session session = sf.openSession();
		session.saveOrUpdate(identity);

		session.close();// TODO do not do that outside of the test case
	}

	@Test
	public void testHibernateAllInARow() {
		Identity identity = new Identity();

		identity.setDisplayName("Junaid" + Math.random());
		identity.setBirthDate(new Date());
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(identity);
		tx.commit();

		tx = session.beginTransaction();
		identity.setEmail("junaid@gmail.com");
		session.update(identity);
		tx.commit();

		tx = session.beginTransaction();
		session.delete(identity);
		tx.commit();

		session.close();// TODO do not do that outside of the test case
	}

	@Test
	public void testHibernateDAO() {
		Identity identity = new Identity();
		identity.setDisplayName("Junaid" + Math.random());
		identity.setBirthDate(new Date());
		dao.save(identity);
		identity.setEmail("jkh@gmail.com");
		dao.update(identity);
		dao.delete(identity);

	}

}
