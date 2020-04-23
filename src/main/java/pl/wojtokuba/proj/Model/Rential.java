package pl.wojtokuba.proj.Model;

import pl.wojtokuba.proj.Exceptions.NotTenantRoleException;
import pl.wojtokuba.proj.Utils.SystemRoles;

import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Rential implements Comparable<Rential> {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int id;
    private Flat flat;
    private User owner;
    private Map<Integer, User> companions;
    private boolean isParkingRent;
    private final Timestamp rentAt;
    private Timestamp rentEnd;
    private boolean archived;
    public Rential(){
        this.id = count.incrementAndGet();
        this.rentAt = new Timestamp(System.currentTimeMillis());
        this.companions = new HashMap<>();
        this.archived = false;
    }

    public int getId() {
        return id;
    }

    public Flat getFlat() {
        return flat;
    }

    public Rential setFlat(Flat flat) {
        this.flat = flat;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Rential setOwner(User owner) throws NotTenantRoleException {
        if(owner.getPermissions() != SystemRoles.TENANT)
            throw new NotTenantRoleException();
        this.owner = owner;
        return this;
    }

    public Map<Integer, User> getCompanions() {
        return companions;
    }

    public Rential setCompanion(User user) {
        this.companions.put(user.getId(), user);
        return this;
    }

    public Timestamp getRentEnd() {
        return rentEnd;
    }

    public Rential setRentEnd(Timestamp rentEnd) {
        this.rentEnd = rentEnd;
        return this;
    }

    public Timestamp getRentAt() {
        return rentAt;
    }

    public boolean isParkingRent() {
        return isParkingRent;
    }

    public Rential setParkingRent(boolean parkingRent) {
        isParkingRent = parkingRent;
        return this;
    }

    public boolean isArchived() {
        return archived;
    }

    public Rential setArchived(boolean archived) {
        this.archived = archived;
        return this;
    }

    @Override
    public int compareTo(Rential o) {
        return this.rentEnd.compareTo(o.rentEnd);
    }
}
