package hoteldelluna.springweb.dddPractice.integration;

public interface OffsetStore {
    long get();
    void update(long nextOffset);
}
