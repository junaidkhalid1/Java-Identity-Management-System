package fr.jkh.iam.user;

/**
 * @author Junaid KHALID
 *
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Junaid KHALID
 *
 */

@Entity
@Table(name="USERS")
public class User {

	@Id
	@Column(name="ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long index;
	
	//@Column(unique = true)
	private String username;
	
	private String password;
	
	
	/**
	 * 
	 */
	public User() {
	}
	
	/**
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	/**
	 * @return the username
	 */
	public String getusername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setusername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getpassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setpassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
	/**
	 * @return the index
	 */
	public final long getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public final void setIndex(long index) {
		this.index = index;
	}
}