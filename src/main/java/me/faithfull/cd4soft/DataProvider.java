package me.faithfull.cd4soft;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Will Faithfull
 */
@Component
public class DataProvider {

    final private static Random random = new Random(System.currentTimeMillis());
    private long delay = 0;

    public ImportantData getData() {
        ImportantData data = generate();
        if(delay > 0) {
            CountDownLatch latch = new CountDownLatch(1);
            try {
                latch.await(delay, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return data;
    }

    public ImportantData generate() {
        ImportantData data = new ImportantData();
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        data.setValue(new String(bytes));
        return data;
    }

    public void setDelay(long millis) {
        this.delay = millis;
    }

}
