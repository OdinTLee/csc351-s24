/** Defines the methods for any type of Queue - FIFO, LIFO, priority */
public interface QueueInterface<T> {

    /** Add a new value to the queue */
    public void push(T value);

    /** Remove the next value form the queue */
    public T pop();

    /** Look at the next value on the queue */
    public T peek();
	
	/** Look to see if the list is empty */
    public boolean isEmpty();
    
    /** Find the max number of nodes ever on the queue */
    //public int max_count();
		
}