package pl.wojtokuba.proj.Storage;

import pl.wojtokuba.proj.Model.File;
import pl.wojtokuba.proj.Model.InnerHost;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class ForeignHostsStorage {
    private final HashMap<String, InnerHost> hosts = new HashMap<>();

    public ForeignHostsStorage push(InnerHost host) {
        this.hosts.put(host.getName(), host);
        return this;
    }

    public boolean delete(InnerHost host){
        this.hosts.remove(host.getName());
        return true;
    }

    public Collection<InnerHost> findAll(){
        return this.hosts.values();
    }

}
