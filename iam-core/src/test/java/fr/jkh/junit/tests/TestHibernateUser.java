package fr.jkh.junit.tests;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.jkh.iam.user.User;
import fr.jkh.iamcore.exception.DAOSaveException;
import fr.jkh.iamcore.exception.DAOUpdateException;
import fr.jkh.iamcore.service.dao.DAODeleteException;
import fr.jkh.iamcore.service.dao.IdentityDAOInterface;

/**
 * @author Junaid KHALID
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/applicationContext.xml")
public class TestHibernateUser {

	@Inject
	DataSource ds;

	@Inject
	SessionFactory sf;

	@Inject
	IdentityDAOInterface dao;

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
	public void testHibernateSaveOrUpdateUser() {
		User user = new User();
		user.setusername("Junaid");
		Session session = sf.openSession();
		session.saveOrUpdate(user);

		session.close();// TODO do not do that outside of the test case
	}

	@Test
	public void testHibernateAllInARow() {
		User user = new User();
		user.setusername("Khan" + Math.random());;
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();

		tx = session.beginTransaction();;
		user.setpassword("test");
		session.update(user);
		tx.commit();

		tx = session.beginTransaction();
		session.delete(user);
		tx.commit();

		session.close();// TODO do not do that outside of the test case
	}
	
	/**
	 * <pre>
	 * "Given two identities in the database with one which the displayName is Clement"
	 * "When we execute the query passing the Clement criteria as the displayName"
	 * "Then we should have only one result"
	 * </pre>
	 * 
	 * @throws DAOSaveException
	 * @throws DAODeleteException
	 */
	@Test
	public void testUserHibernateQueryLanguage() throws DAOSaveException, DAODeleteException {
		List<?> userlist = null;
		Session session = sf.openSession();
		try {
			
			dao.create(new User("Thomas", "Broussard"));
			
			String HqlUser = "from User as user where user.username = :username";
			Query queryuser = session.createQuery(HqlUser);
			
			queryuser.setParameter("username", "Thomas");
			userlist = queryuser.list();
			
			System.out.println(userlist);
			Assert.assertEquals(1, userlist.size());
		} finally {
			if (userlist != null) {
				// Finally, leave the database empty
				for (Object obj : userlist) {
					User user = (User) obj;
					dao.deluser(user);
				}
			}
			session.close();// TODO do not do that outside of the test case
		}
	}

@Test
public void testHibernateUser() throws DAOSaveException, DAOUpdateException, DAODeleteException {
	User user = new User();
	user.setusername("Thomas" + Math.random());
	user.setpassword("pass");
	dao.create(user);
	dao.updateuser(user);
	dao.deluser(user);

	}

@Test
public void testHibernateuserLogin() {
	User user = new User();
	user.setusername("check");
	user.setpassword("check");
	dao.userLogin(user);

	}

@Test
public void testHibernateuserUserExist() {
	User user = new User();
	user.setusername("check");
	dao.userExistsinDB(user);
	}
}
