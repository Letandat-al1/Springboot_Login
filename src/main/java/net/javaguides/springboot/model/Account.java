package net.javaguides.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
public class Account {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long Id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "Password")
	private String Password;
	
	@Column(name = "Email")
	private String Email;
	
	@Column(name = "Phone")
	private String Phone;
	
	@Column(name = "Address")
	private String Address;
	
	@Column(name = "Emoji")
	private String Emoji;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String Name) {
		name = Name;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public String getEmoji() {
		return Emoji;
	}

	public void setEmoji(String emoji) {
		Emoji = emoji;
	}
	
}
