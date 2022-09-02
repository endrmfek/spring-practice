package hoteldelluna.springweb.dddPractice.integration.infra;

import hoteldelluna.springweb.dddPractice.eventstore.api.EventEntry;
import hoteldelluna.springweb.dddPractice.integration.EventSender;
import org.springframework.stereotype.Component;

@Component
public class SysoutEventSender implements EventSender {
    @Override
    public void send(EventEntry event) {
        System.out.println("EventSender send event : " + event);
    }
}
