# tickers data processing

Implement a process to find the most popular ticker (the symbol identifying a stock).
Read the javadoc of TransactionStatistics to understand what we mean by popular.
Two implementations. One using a single-thread, the other performing the calculation in parallel. 
If you look at performance, assume a list of 1M entries and 40 different tickers

Implementing the Statistic interface in other to provide an implementation that calculate statistics for an infinite flow of events. You can do many implementations.

The rules:

Implementations of this class is 'threadsafe'.
Implementations may choose to prioritize (or not) various aspects of the performance and or behavior of their instances this is acceptable as long as the compromise can be explained and justified.