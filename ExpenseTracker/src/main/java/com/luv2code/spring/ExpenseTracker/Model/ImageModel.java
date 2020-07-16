package com.luv2code.spring.ExpenseTracker.Model;

import java.util.Arrays;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ImageProfile")
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Lob
    @Column(name = "Image")
    private byte[] image;
    
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.PERSIST)
    @JoinColumn(name = "expense_id")
    private Expense expenses;

    public ImageModel() {
        super();
        // TODO Auto-generated constructor stub
    }

	public ImageModel(String name, byte[] image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
		this.expenses = expenses;
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

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Expense getExpenses() {
		return expenses;
	}

	public void setExpenses(Expense expenses) {
		this.expenses = expenses;
	}

	@Override
	public String toString() {
		return "ImageModel [id=" + id + ", name=" + name + ", image=" + Arrays.toString(image) + ", expenses="
				+ expenses + "]";
	}
  
}