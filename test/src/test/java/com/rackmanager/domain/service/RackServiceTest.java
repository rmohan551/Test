package com.rackmanager.domain.service;

import com.rackmanager.api.dto.response.RackResponse;
import com.rackmanager.infrastructure.database.entity.Rack;
import com.rackmanager.infrastructure.database.repository.RackRepository;
import com.rackmanager.infrastructure.exception.RackNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * RackServiceTest class.
 */
@ExtendWith(MockitoExtension.class)
public class RackServiceTest {

    @InjectMocks
    public RackService rackService;

    @Mock
    public RackRepository rackRepository;

    /**
     * shouldReturnRackResponseWithSuccess test.
     */
    @Test
    void shouldReturnRackResponseWithSuccess() throws RackNotFoundException {
        final UUID randomUUID = UUID.randomUUID();
        final String testName = "staticName";
        final Date testDate = new Date();

        final Rack original = new Rack(randomUUID, testName, testDate);
        final RackResponse expected = new RackResponse(randomUUID, testName, testDate);

        when(rackRepository.findById(eq(randomUUID))).thenReturn(Optional.of(original));

        final RackResponse actual = rackService.findById(randomUUID);

        assertEquals(expected, actual);
    }

    /**
     * shouldThrowRackNotFoundExceptionWithInvalidId test.
     */
    @Test()
    void shouldThrowRackNotFoundExceptionWithInvalidId() {
        final UUID randomUUID = UUID.randomUUID();

        when(rackRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(RackNotFoundException.class, () -> rackService.findById(randomUUID));
    }
}
