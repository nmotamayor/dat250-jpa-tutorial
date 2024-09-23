package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.creditcards.Address;
import no.hvl.dat250.jpa.tutorial.creditcards.Bank;
import no.hvl.dat250.jpa.tutorial.creditcards.CreditCard;
import no.hvl.dat250.jpa.tutorial.creditcards.Customer;
import no.hvl.dat250.jpa.tutorial.creditcards.Pincode;

public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";

  public static void main(String[] args) {
    try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
        PERSISTENCE_UNIT_NAME); EntityManager em = factory.createEntityManager()) {
      em.getTransaction().begin();
      createObjects(em);
      em.getTransaction().commit();
    }

  }

  private static void createObjects(EntityManager em) {
    // Create Customer
    Customer customer = new Customer();
    customer.setName("Max Mustermann");

    // Create Address
    Address address = new Address();
    address.setStreet("Inndalsveien");
    address.setNumber(28);
    address.addOwner(customer);

    customer.addAddress(address);

    // Create first Pincode for card1
    Pincode pincode = new Pincode();
    pincode.setCode("123");
    pincode.setCount(1);

    // Create first CreditCard
    CreditCard card1 = new CreditCard();
    card1.setNumber(12345);
    card1.setBalance(-5000);
    card1.setCreditLimit(-10000);
    card1.setPincode(pincode);
    card1.setCustomer(customer);

    // Create second CreditCard
    CreditCard card2 = new CreditCard();
    card2.setNumber(123);
    card2.setBalance(1);
    card2.setCreditLimit(2000);
    card2.setPincode(pincode);
    card2.setCustomer(customer);

    // Create Bank
    Bank bank = new Bank();
    bank.setName("Pengebank");

    card1.setOwningBank(bank);
    card2.setOwningBank(bank);

    // Add the credit cards to the bank
    bank.addOwnedCard(card1);
    bank.addOwnedCard(card2);

    // Add the credit cards to the customer
    customer.addCreditCard(card1);
    customer.addCreditCard(card2);

    // Persist the objects
    em.persist(customer);
    em.persist(address);
    em.persist(pincode);
    em.persist(card1);
    em.persist(card2);
    em.persist(bank);
  }
}