package com.rackmanager.api.dto.response;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/*
 * Rack response class.
 */
public class RackResponse {
    private UUID id;
    private String name;
    private Date date;

    /**
     * RackResponse constructor.
     * @param id the id
     * @param name
     * @param date
     */
    public RackResponse(final UUID id, final String name, final Date date) {
        this.id = id;
        this.name = name;
        this.date = date;
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

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RackResponse that = (RackResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, date);
    }
}
