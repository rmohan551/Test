package com.rackmanager.domain.assembler;

import com.rackmanager.api.dto.request.RackRequest;
import com.rackmanager.api.dto.response.RackListResponse;
import com.rackmanager.api.dto.response.RackResponse;
import com.rackmanager.infrastructure.database.entity.Rack;

import java.util.List;
import java.util.stream.Collectors;

public final class RackAssembler {

    /**
     * Convert a {@link Rack} into a {@link RackResponse}.
     * @param rack {@link Rack}
     * @return {@link RackResponse}
     */
    public static RackResponse toRackResponse(final Rack rack){
        return new RackResponse(rack.getId(), rack.getName(), rack.getDate());
    }

    /**
     * Convert a {@link List} of {@link Rack} into a {@link RackListResponse}.
     * @param rackList {@link List} of {@link Rack}
     * @return {@link RackListResponse}
     */
    public static RackListResponse toRackListResponse(final List<Rack> rackList){
        List rackListAssembled = rackList.stream().map(
            r -> new RackResponse(r.getId(), r.getName(), r.getDate())
        ).collect(Collectors.toList());

        return new RackListResponse(rackListAssembled);
    }

    /**
     * Convert a {@link RackRequest} into a {@link Rack}.
     * @param rackRequest {@link RackRequest}
     * @return {@link Rack}
     */
    public static Rack fromRackRequest(final RackRequest rackRequest){
        return new Rack(rackRequest.getId(), rackRequest.getName(), rackRequest.getDate());
    }
}
