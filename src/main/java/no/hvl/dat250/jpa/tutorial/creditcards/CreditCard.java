package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer number;
    private Integer creditLimit;
    private Integer balance;


    @ManyToOne
    private Pincode pinCode;
    
    @ManyToOne
    private Bank owner;


    @ManyToOne
    private Customer customer;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Integer getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Integer creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Pincode getPincode() {
        return pinCode;
    }

    public void setPincode(Pincode pincode) {
        this.pinCode = pincode;
    }

    public Bank getOwningBank() {
        return owner;
    }

    public void setOwningBank(Bank owningBank) {
        this.owner = owningBank;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}