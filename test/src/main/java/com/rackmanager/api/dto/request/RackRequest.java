package com.rackmanager.api.dto.request;

import java.util.Date;
import java.util.UUID;

public class RackRequest {
    private UUID id;
    private String name;
    private Date date;

    public UUID getId() {
        return id;
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
