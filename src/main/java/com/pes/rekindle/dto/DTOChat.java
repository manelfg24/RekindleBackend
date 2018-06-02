
package com.pes.rekindle.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class DTOChat {

    private long id;

    private DTOUser user1;

    private DTOUser user2;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DTOUser getUser1() {
        return user1;
    }

    public void setUser1(DTOUser user1) {
        this.user1 = user1;
    }

    public DTOUser getUser2() {
        return user2;
    }

    public void setUser2(DTOUser user2) {
        this.user2 = user2;
    }
}
