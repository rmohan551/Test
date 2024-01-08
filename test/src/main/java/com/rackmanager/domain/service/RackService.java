package com.rackmanager.domain.service;

import com.rackmanager.api.dto.request.RackRequest;
import com.rackmanager.api.dto.response.RackListResponse;
import com.rackmanager.api.dto.response.RackResponse;
import com.rackmanager.domain.assembler.RackAssembler;
import com.rackmanager.infrastructure.database.entity.Rack;
import com.rackmanager.infrastructure.database.repository.RackRepository;
import com.rackmanager.infrastructure.exception.RackNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class RackService {

    @Inject
    private RackRepository rackRepository;

    /**
     * Retrieve all racks in the system.
     * @return {@link RackListResponse}
     */
    public RackListResponse findAll(){
        final List<Rack> racks = rackRepository.findAll();
        return RackAssembler.toRackListResponse(racks);
    }

    /**
     * Saves a {@link RackRequest} as a {@link Rack} in the system.
     * @param newRack {@link RackRequest}
     * @return {@link RackRequest}
     */
    public RackResponse saveOne(final RackRequest newRack){
        final Rack rackSaved = rackRepository.save(RackAssembler.fromRackRequest(newRack));
        return RackAssembler.toRackResponse(rackSaved);
    }

    /**
     * Saves a {@link List} of {@link Rack}.
     * @param newRacks {@link List} of {@link Rack}
     * @return {@link RackListResponse}
     */
    public RackListResponse saveBatch(final List<Rack> newRacks){
        final List<Rack> racksSaved = rackRepository.saveAll(newRacks);
        return RackAssembler.toRackListResponse(racksSaved);
    }

    /**
     * Retrieves a {@link RackResponse} given a valid id.
     * @param id {@link UUID}
     * @return {@link RackResponse}
     * @throws RackNotFoundException {@link RackNotFoundException}
     */
    public RackResponse findById(final UUID id) throws RackNotFoundException {
        final Rack rack = rackRepository
                .findById(id)
                .orElseThrow(RackNotFoundException::new);

        return RackAssembler.toRackResponse(rack);
    }

    /**
     * Updates a rack if existent.
     * @param rackRequest {@link RackRequest}
     * @return {@link RackResponse}
     * @throws RackNotFoundException {@link RackNotFoundException}
     */
    public RackResponse updateRack(final RackRequest rackRequest) throws RackNotFoundException {
        final Rack rack = rackRepository
                .findById(rackRequest.getId())
                .orElseThrow(RackNotFoundException::new);

        rack.setDate(rackRequest.getDate());
        rack.setName(rackRequest.getName());
        rackRepository.save(rack);
        return RackAssembler.toRackResponse(rack);
    }

    /**
     * Deletes a rack if existent.
     * @param id {@link UUID}
     * @throws RackNotFoundException {@link RackNotFoundException}
     */
    public void delete(final UUID id) throws RackNotFoundException {
        final Rack rack = rackRepository
                .findById(id)
                .orElseThrow(RackNotFoundException::new);

        rackRepository.delete(rack);
    }
}
