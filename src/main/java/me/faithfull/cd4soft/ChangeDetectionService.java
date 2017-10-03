package me.faithfull.cd4soft;

import lombok.extern.slf4j.Slf4j;
import me.faithfull.cd4soft.cd.ChangeDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.RequestHandledEvent;

/**
 * @author Will Faithfull
 */
@Service
@Slf4j
public class ChangeDetectionService {

    @Autowired
    ChangeDetector<Double> changeDetector;

    @EventListener
    public void update(RequestHandledEvent traceEvent) {
        changeDetector.update((double) traceEvent.getProcessingTimeMillis());

        if(!changeDetector.isReady()) {
            return;
        }

        boolean change = changeDetector.isChange();

        if(change) {
            log.info("CHANGE DETECTED! Time to send a midnight page to the sysadmin. Anomalous value: {}",
                    traceEvent.getProcessingTimeMillis());

            // One alarm is enough. This is the new data source now.
            // If it changes again, we want to know.
            changeDetector.reset();
        }
    }

}
