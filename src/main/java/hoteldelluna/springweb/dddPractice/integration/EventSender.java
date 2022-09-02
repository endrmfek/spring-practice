package hoteldelluna.springweb.dddPractice.integration;

import hoteldelluna.springweb.dddPractice.eventstore.api.EventEntry;

public interface EventSender {
    void send(EventEntry event);
}
