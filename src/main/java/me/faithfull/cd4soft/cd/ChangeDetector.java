package me.faithfull.cd4soft.cd;

/**
 * @author Will Faithfull
 */
public interface ChangeDetector<T> {

    /**
     * Provide the next item in the stream
     * @param observation the next item in the stream
     */
    void update(T observation);

    /**
     * Did the detector signal change at the last item?
     * @return true, if it did.
     */
    boolean isChange();

    /**
     * Has the detector seen enough items to detect change?
     * @return true, if it has.
     */
    boolean isReady();

    /**
     * Reset the detector, wiping any memory component it retains.
     */
    void reset();

}
