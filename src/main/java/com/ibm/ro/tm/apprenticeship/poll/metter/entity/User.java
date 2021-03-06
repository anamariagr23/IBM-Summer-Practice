/**
 * 
 */
package com.ibm.ro.tm.apprenticeship.poll.metter.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * @author O09860826
 *
 */

@Entity
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8816369699035300946L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;

	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
		
	
	protected User() {

	}

	public User(String name, Role role) {

		this.name = name;
		this.role = role;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the roles
	 */
	public Role getRoles() {
		return role;
	}
	
		
	
	//setters
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setRoles(Role newRole) {
		this.role = newRole;
	}
	
		

}
