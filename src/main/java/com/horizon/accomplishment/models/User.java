package com.horizon.accomplishment.models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class User {
	
	// ==== Primary Key =================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
	// ==== Member Variables ============
    @NotEmpty(message="Username is required")
    @Size(min = 2, max = 30, message = "Username must be between 3 and 30 characters")
    private String userName;
    
    @NotEmpty(message="Email is required")
    @Email(message = "Please enter a valid email")
    private String email;
    
    @NotEmpty(message="Password is required")
    @Size(min = 8, max = 128, message="Password must be between 8 and 128 characters")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm password is required")
    @Size(min = 8, max = 128, message="Confirm password must be between 8 and 128 characters")
    private String confirm;
    
    // ==== Relationships =============================================
    @OneToMany(mappedBy="creator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bucket> buckets;

	// ==== Data Creation Trackers ======
    @Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

	// ==== Constructors ================
    public User() {}
    
    public User(String userName, String email, String password, String confirm) {
    	super();
    	this.userName = userName;
    	this.email = email;
    	this.password = password;
    	this.confirm = confirm;
    }

	// ==== Data Creation Event =========
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

    // ==== Getters and Setters =========
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	public List<Bucket> getBuckets() {
		return buckets;
	}

	public void setBuckets(List<Bucket> buckets) {
		this.buckets = buckets;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}
