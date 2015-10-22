package br.com.gsw.example.liquibase.poc.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "poc")
@SequenceGenerator(sequenceName = "POC_SEQUENCE", name = "POC_SEQUENCE", initialValue = 1, allocationSize = 1)
public class Poc implements Serializable {

    @Id
    @GeneratedValue(generator = "POC_SEQUENCE", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    public Poc() {

    }

    public Poc(final String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}