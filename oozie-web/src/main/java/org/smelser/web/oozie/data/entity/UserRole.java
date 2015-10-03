package org.smelser.web.oozie.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(
		columnNames = { "authority", "userId" }))
public class UserRole{
 
	private Integer id;
	private User user;
	private String authority;
 
	public UserRole() {
	}
 
	public UserRole(User user, String role) {
		this.user = user;
		this.authority = role;
	}
 
	@Id
	@GeneratedValue
	@Column(name = "id", 
		unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}
 
	public void setId(Integer userRoleId) {
		this.id = userRoleId;
	}
 
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "userId", nullable = false)
	public User getUser() {
		return this.user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}
 
	@Column(name = "authority", nullable = false, length = 45)
	public String getRole() {
		return this.authority;
	}
 
	public void setRole(String role) {
		this.authority = role;
	}
 
}
