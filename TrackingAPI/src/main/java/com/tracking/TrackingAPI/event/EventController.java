package com.tracking.TrackingAPI.event;

import com.tracking.TrackingAPI.event.domain.Event;
import com.tracking.TrackingAPI.event.domain.EventService;
import com.tracking.TrackingAPI.event.error.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Logger;

/**
 * Kontroler, który wystawia endpointy.
 */
@RequestMapping("/tracking")
@RestController
public class EventController {

    private static final Logger logger = Logger.getLogger(EventController.class.getSimpleName());
    private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping(value = "/event")
    public ResponseEntity<?> registerEvent(@Valid @RequestBody Event event, Errors errors) {
        if (errors.hasErrors()) {
            logger.info("Add event - bad request. " + errors.getAllErrors());

            return ResponseEntity.badRequest()
                    .body(new Error("Missing or incomplete event body"));
        }

        logger.info("About to add new event: " + event);

        Event savedEvent = eventService.addEvent(event);
        return ResponseEntity.ok(savedEvent);
    }

    @GetMapping(value = "/logs")
    public ResponseEntity<List<Event>> getFirstEvents() {

        logger.info("About to handle last logs request.");

        List<Event> firstEvents = eventService.findFirstEvents(10);
        return ResponseEntity.ok(firstEvents);
    }
//    /**
//     * nowy endpoint - wszystkie logi
//     */
//    @GetMapping(value = "/new_logs")
//    public ResponseEntity<List<Event>> getEvents() {
//
//        logger.info("About to handle all logs request.");
//
//        List<Event> lastEvents = eventService.findEvents();
//        return ResponseEntity.ok(lastEvents);
//    }
}
