package no.hvl.dat250.jpa.tutorial.relationships;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;



@Entity
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @OneToMany(mappedBy = "family", cascade = CascadeType.ALL)
    private final List<Person> members = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Person> getMembers() {
        return members;
    }
}