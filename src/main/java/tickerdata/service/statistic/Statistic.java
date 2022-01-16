
package tickerdata.service.statistic;

/**
 * Tracks the statistics of a continual stream of values.
 */
public interface Statistic {

   /**
    * Called to update the statistic with a new sample value.
    *
    * @param value new sample to add to the statistics
    */
   void event(int value);

   /**
    * Returns the mean of the received sample values.
    *
    * @return the mean of the received sample values
    * */
   float mean();

   /**
    * Returns the minimal received sample value.
    *
    * @return the minimal received sample value
    */
   int minimum();

   /*
    * Returns the maximal received sample value.
    *
    * @return the maximal received sample value
    */
   int maximum();

   /**
    *
    * Returns an estimate of the variance of the total population * given the received sample values.
    *
    * @return an estimate of the variance of the population
    */
   default float variance() {
      throw new UnsupportedOperationException("Variance is not supported by this implementation");
   }

}
