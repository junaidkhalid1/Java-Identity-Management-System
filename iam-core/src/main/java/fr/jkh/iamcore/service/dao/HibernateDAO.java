/**
 * 
 */
package fr.jkh.iamcore.service.dao;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.jkh.iam.log.IAMLogger;
import fr.jkh.iam.log.impl.IAMLogManager;
import fr.jkh.iam.user.User;
import fr.jkh.iamcore.datamodel.Identity;
import fr.jkh.iamcore.exception.DAOSearchException;

/**
 * @author Junaid KHALID
 *
 */
public class HibernateDAO implements IdentityDAOInterface {
	
	@Inject
	SessionFactory sf;

	private static final IAMLogger logger = IAMLogManager.getIAMLogger(HibernateDAO.class);
	
	/**
	 * @param identity
	 */
	@Override
	public void save(Identity identity) {
		logger.info("=> save this identity : " + identity);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(identity);
		tx.commit();
		logger.info("<= save ok" );
		
	}

	/**
	 * @param identity
	 */
	@Override
	public void update(Identity identity) {
		try {
        	logger.info("=> Update this identity : " + identity);
            final String updateQuery = "update Identity as identity set identity.displayName = :dName, identity.uid = :uid, "
            		+ "identity.birthDate = :bdate"
            		+ " where identity.email = :email";
            Session session = sf.openSession();
            final Query query = session.createQuery(updateQuery);
            query.setParameter("dName", identity.getDisplayName());
            query.setParameter("uid", identity.getUid());
            query.setParameter("email", identity.getEmail());
            query.setParameter("bdate", identity.getBirthDate());
            final int rowCount = query.executeUpdate(); // check that the rowCount is 1
            logger.info("<= Update ok" );
        } catch (RuntimeException re) {
            logger.debug("Update failed", re);
            throw re;
        }
	}

	/**
	 * @param identity
	 */
	@Override
	public void delete(Identity identity) {		
        try {
        	logger.info("=> delete this identity : " + identity);
            final String deleteQuery = "delete from Identity as identity where identity.displayName = :dName";
            Session session = sf.openSession();
            final Query query = session.createQuery(deleteQuery);
            query.setParameter("dName", identity.getDisplayName());
            final int rowCount = query.executeUpdate(); // check that the rowCount is 1
            logger.info("<= delete ok" );
        } catch (RuntimeException re) {
            logger.debug("delete failed", re);
            throw re;
        }
		
	}

	/* (non-Javadoc)
	 * @see fr.tbr.iamcore.service.dao.IdentityDAOInterface#search(fr.tbr.iamcore.datamodel.Identity)
	 */
	@Override
	public Collection<Identity> search(Identity criteria) throws DAOSearchException {
		String hqlString = "from Identity as identity where identity.displayName = :dName or identity.email = :email";
		Session session = sf.openSession();
		Query query = session.createQuery(hqlString);
		query.setParameter("dName", criteria.getDisplayName());
		query.setParameter("email", criteria.getEmail());
		return (Collection<Identity>) query.list();
	}
	
	/**
	 * @param user
	 * To register user
	 */
	@Override
	public void create(User user) {
		logger.info("=> save this user : " + user);
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(user);
		tx.commit();
		logger.info("<= save ok" );
		
	}
	
	/**
	 * @param user
	 * To delete user
	 */
	@Override
	public void deluser(User user) {		
        try {
        	logger.info("=> delete this user : " + user);
            final String deleteQuery = "delete from User as user where user.username = :username";
            Session session = sf.openSession();
            final Query query = session.createQuery(deleteQuery);
            query.setParameter("username", user.getusername());
            final int rowCount = query.executeUpdate(); // check that the rowCount is 1
            logger.info("<= delete ok" );
        } catch (RuntimeException re) {
            logger.debug("delete failed", re);
            throw re;
        }
		
	}
	
	/**
	 * @param user
	 * To update user
	 */
	@Override
	public void updateuser(User user) {
		try {
        	logger.info("=> Update this user : " + user);
            final String updateQuery = "update User as user set user.username = :username"
            		+ " where user.password = :password";
            Session session = sf.openSession();
            final Query query = session.createQuery(updateQuery);
            query.setParameter("username", user.getusername());
            query.setParameter("password", user.getpassword());
            final int rowCount = query.executeUpdate(); // check that the rowCount is 1
            logger.info("<= Update ok" );
        } catch (RuntimeException re) {
            logger.debug("Update failed", re);
            throw re;
        }
	}
	
	/**
	 * @param user
	 * To check user if it exist already in db or not
	 * @return List
	 */
	@Override	
	public List userExistsinDB(User user) {
		try {
        	logger.info("=> User exist : " + user);
		final String hqlString = "select username=:uname from User where username=:uname";
		Session session = sf.openSession();
		final Query query = session.createQuery(hqlString);
		query.setParameter("uname", user.getusername());
		final List rowCount = query.list(); 
		logger.info("<= User exist ok" );
        return rowCount;
		} catch (RuntimeException re) {
	        logger.debug("User exist failed", re);
	        throw re;
	    }
	}
	
	/**
	 * @param user
	 * To check user login if username and password matches
	 * @return List
	 */
	@Override
	public List userLogin(User user) {

		try {
        	logger.info("=> Check this user : " + user);
		final String hqlString = "select username=:uname from User where password=:upass and username=:uname";
		Session session = sf.openSession();
		final Query query = session.createQuery(hqlString);
		query.setParameter("uname", user.getusername());
		query.setParameter("upass", user.getpassword());
		final List rowCount = query.list(); 
		logger.info("<= Check ok" );
        return rowCount;
		} catch (RuntimeException re) {
	        logger.debug("Check failed", re);
	        throw re;
	    }
        
	    }
	
	
	/**
	 * @param sf
	 */
	public void setSessionFactory(SessionFactory sf){
		this.sf = sf;
	}
	
	/**
	 * @return {@link SessionFactory}
	 */
	public SessionFactory getSessionFactory(){
		return this.sf;
	}

}
