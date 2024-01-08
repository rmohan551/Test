package com.rackmanager.domain.assembler;

import com.rackmanager.api.dto.response.RackResponse;
import com.rackmanager.infrastructure.database.entity.Rack;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * RackAssembler test class.
 */
public class RackAssemblerTest {

    /**
     * shouldAssembleRackResponseFromRack test.
     */
    @Test
    void shouldAssembleRackResponseFromRack() {
        final UUID randomUUID = UUID.randomUUID();
        final String testName = "staticName";
        final Date testDate = new Date();

        final Rack original = new Rack(randomUUID, testName, testDate);

        final RackResponse expected = new RackResponse(randomUUID, testName, testDate);
        final RackResponse actual = RackAssembler.toRackResponse(original);

        assertEquals(expected, actual);
    }
}
