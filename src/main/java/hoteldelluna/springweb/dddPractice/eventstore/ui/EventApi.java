package hoteldelluna.springweb.dddPractice.eventstore.ui;

import hoteldelluna.springweb.dddPractice.eventstore.api.EventEntry;
import hoteldelluna.springweb.dddPractice.eventstore.api.EventStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EventApi {
    private EventStore eventStore;

    public EventApi(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    @GetMapping(value = "/api/events")
    public List<EventEntry> list(
            @RequestParam("offset") Long offset,
            @RequestParam("limit") Long limit) {
                return eventStore.get(offset, limit);
    }
}
