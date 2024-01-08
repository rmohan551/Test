package com.rackmanager.infrastructure.database.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.UuidGenerator;

import java.util.Date;
import java.util.UUID;

/**
 * Rack entity.
 */
@Entity
public class Rack {

    @Id
    @UuidGenerator
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private Date date;

    /**
     * Constructor.
     * @param id an {@link UUID}
     * @param name a rack name
     * @param date a rack date
     */
    public Rack(final UUID id, final String name, final Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    /**
     * Default constructor.
     */
    public Rack() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date;
    }
}
