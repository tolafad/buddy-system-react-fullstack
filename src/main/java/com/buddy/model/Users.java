package com.buddy.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })

public class Users {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column
	private String name;

	@Column
	private String email;

	@OneToOne(fetch = FetchType.LAZY)
	private Users buddy;

	public Users() {
		super();
	}

	Users(Users user) {
		this.setId(user.getId());
		this.setName(user.getName());
		this.setEmail(user.getEmail());

	}

	Users(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email; 
    }
	
    public static Builder builder() {
        return new Builder();
    }
    
	public Users getBuddy() {
		return buddy == null ? null : new Users(buddy);
	}

	public void setBuddy(Users buddy) {
		this.buddy = buddy;
	}

	@Override
	public String toString() {
		return "Users [id=" + id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		return id == other.id;
	}

	public static class Builder {

		private int id;

		private String name;

		private String email;

		private Users buddy;

 		public Builder withId(Integer id) {
			this.id = id;
			return this;
		}

 		public Builder withName(String name) {
			this.name = name;
			return this;
		}

 		public Builder withEmail(String email) {
			this.email = email;
			return this;
		}

 		public Builder witBuddy(Users buddy) {
			this.buddy = buddy;
			return this;
		}

		public Users build() {
			return new Users(this);
		}

	}

}
