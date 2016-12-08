/**
 * 
 */
package fr.jkh.iamcore.service.dao;

import java.util.Collection;

import javax.inject.Inject;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import fr.jkh.iam.User;
import fr.jkh.iam.log.IAMLogger;
import fr.jkh.iam.log.impl.IAMLogManager;
import fr.jkh.iamcore.datamodel.Identity;
import fr.jkh.iamcore.exception.DAOSearchException;
import fr.jkh.iamcore.service.authentication.AuthenticationService;

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
		String hqlString = "from Identity as identity where identity.displayName = :dName";
		Session session = sf.openSession();
		Query query = session.createQuery(hqlString);
		query.setParameter("dName", criteria.getDisplayName());
		return (Collection<Identity>) query.list();
	}
	
	
	public void setSessionFactory(SessionFactory sf){
		this.sf = sf;
	}
	
	public SessionFactory getSessionFactory(){
		return this.sf;
	}

}
