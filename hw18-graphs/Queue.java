public class Queue<T> implements QueueInterface<T>{
	
	@SuppressWarnings("unchecked")
	T[] array = (T[]) new Object[30];
	
	// index of the first element in the Queue
	private int head;
	
	// index of the location to place the next added value
	private int tail;
	
	// variable to count the number of elements in the queue
	private int count = 0;
	
	// variable for the max number of nodes ever on the queue
	private int max_count = 0;
	
// _____________________toString_____________________________
	
	/**
 	* Returns a string representation of the queue.
 	* @return A string containing the elements of the queue.
 	*/
	@Override
	public String toString(){
		return "The Queue is: " + array;
	}
	
// _____________________isEmpty______________________________

	/**
 	* Checks if the linked list is empty.
 	* @return true if the linked list is empty, false otherwise.
 	*/
	public boolean isEmpty(){
		return head == tail;
	}

// _____________________isFull_______________________________

	/**
	* Checks if the circular queue is full.
 	* @return true if the circular queue is full, false otherwise.
 	*/
	public boolean isFull(){
		return head == (tail + 1) % array.length;
	}
	
// _____________________Push/Add_____________________________
	
	/**
 	* Adds an item to the circular queue and increments the tail index.
 	* @param object The item to be added to the circular queue.
 	*/
	public void push(T object){
		
		// Check if the circular queue is full
		if( isFull() == true){
			return;
		}
		
		// Add the item to the current tail position in the circular array
		array[tail] = object;
		
		// Move the tail index to the next position in the circular array and set i equal to head
		tail = (tail + 1) % array.length;
		int i = head;
		
		// Update the count and check if it exceeds the maximum count
		count++;
		if( count > max_count){
			max_count = count;
		}
		return;
	}
	
// _____________________Pop/Remove___________________________
	
	/**
 	* Removes an item from the circular queue and increments the head index.
 	* @return The item removed from the circular queue, or null if the queue is empty.
 	*/
	public T pop(){
		T removed;
		
		// Check if the circular queue is empty
		if( isEmpty() == true){
			return null;
		}
		
		// Remove the item from the current head position in the circular array
		removed = array[head];
		array[head] = null;
		
		// Move the head index to the next position in the circular array
		head = (head + 1) % array.length;
		
		// Update the count
		count--;
		return removed;
	}

// _____________________Peek/Get_____________________________
	
	/**
 	* Retrieves the item at the head of the circular queue without removing it.
 	* @return The item at the head of the circular queue, or null if the queue is empty.
 	*/
	public T peek(){
		T found;
		
		// Check if the circular queue is empty
		if( isEmpty() == true){
			return null;
		}
		
		// Retrieve the item at the current head position in the circular array
		found = array[head];
		return found;
	}
	
// ____________________ max_count getter ____________________
	
	/**
 	* Retrieves the maximum count value recorded during the circular queue operations.
 	* @return The maximum count value.
 	*/
	public int max_count() {
		return max_count;
	}
}
