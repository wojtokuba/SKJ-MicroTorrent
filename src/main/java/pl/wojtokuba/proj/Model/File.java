package pl.wojtokuba.proj.Model;

import java.util.concurrent.atomic.AtomicInteger;

public class File {
    private static final AtomicInteger count = new AtomicInteger(0);

    private final int id;
    private Rential rential;

    public File(){
        this.id = count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public Rential getRential() {
        return rential;
    }

    public File setRential(Rential rential) {
        this.rential = rential;
        return this;
    }
}
