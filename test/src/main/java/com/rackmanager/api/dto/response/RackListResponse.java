package com.rackmanager.api.dto.response;

import java.util.List;

public class RackListResponse {

    private List<RackResponse> rackResponseList;

    /**
     * RackListResponse constructor.
     * @param rackResponseList a list of {@link RackResponse}
     */
    public RackListResponse(final List<RackResponse> rackResponseList) {
        this.rackResponseList = rackResponseList;
    }

    public List<RackResponse> getRackResponseList() {
        return rackResponseList;
    }

    /**
     * RackListResponse setter.
     * @param rackResponseList a list of {@link RackResponse}
     */
    public void setRackResponseList(final List<RackResponse> rackResponseList) {
        this.rackResponseList = rackResponseList;
    }
}
