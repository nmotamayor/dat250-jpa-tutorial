package no.hvl.dat250.jpa.tutorial.creditcards;

import java.util.Collection;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    private Set<Address> address = new HashSet<Address>();
    
    @ManyToMany
    private Set<CreditCard> creditCard = new HashSet<CreditCard>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Address> getAddresses() {
        return address;
    }

    public void addAddress(Address addresse) {
        this.address.add(addresse);
    }

    public Collection<CreditCard> getCreditCards() {
        return creditCard;
    }

    public void addCreditCard(CreditCard creditCard) {
        this.creditCard.add(creditCard);
    }
}