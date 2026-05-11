import java.util.ArrayList;

/**
 * This class represents a Priority Queue (heap) based on the ordering
 * defined by the compareTo method for the element. "Lower" value will
 * mean "higher" priority.
 *
 * @param <E> the type of elements in the queue
 * @author Cbents
 */
public class PriorityQueue<E extends Comparable<E>> {

    private ArrayList<E> myHeap; //array representation of the heap

    /**
     * Creates an empty Priority Queue
     */
    public PriorityQueue() {
        myHeap = new ArrayList<E>();
    }

    /**
     * Adds the element to the priority queue
     *
     * @param element the element to be added
     */
    public void add(E element) {
        //First case: Check if empty and add
        if (myHeap.isEmpty()){
            myHeap.add(element);
        }

        else{
            myHeap.add(element);
            heapify(myHeap.size()-1);
        }


    }

    /**
     * Swaps two elements in the queue.
     * Pre-condition: 0 <= posOne, posTwo < size of queue
     *
     * @param posOne the first element's position in the queue
     * @param posTwo the second element's position in the queue
     */
    private void swap(int posOne, int posTwo) {
        E temp = myHeap.get(posOne);
        myHeap.set(posOne, myHeap.get(posTwo));
        myHeap.set(posTwo, temp);
    }

    /**
     * Returns whether or not the element is in the heap
     *
     * @param element the element to be searched for
     * @return true if the element is in the queue, false otherwise
     */
    public boolean contains(E element) {
        for (E n : myHeap){
            if (n.equals(element)){
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the element of highest priority, null if queue is empty.
     * Post-condition: the queue is not changed
     *
     * @return the element of highest priority queue
     */
    public E peek() {

        if (myHeap.isEmpty()){
            return null;
        }
        else{
            return myHeap.get(0);
        }


    }

    /**
     * Removes and returns the element of highest priority,
     * returns null if queue is empty.
     *
     * @return the element of highest priority
     */
    public E poll() {
        if (myHeap.isEmpty()) {
            return null;
        }

        E temp = myHeap.get(0);

        // If there is only one element
        if (myHeap.size() == 1) {
            myHeap.remove(0);
            return temp;
        }

        // Move last element to root
        myHeap.set(0, myHeap.get(myHeap.size() - 1));
        myHeap.remove(myHeap.size() - 1);

        // Restore heap property
        heapify(0);

        return temp;
    }

    /**
     * Will "sift down" the element at the given position
     * down to restore the heap property
     *
     * @param pos the starting position for heapify
     */
    private void heapify(int pos) {

        // First: bubble up if needed
        while (pos > 0) {
            int parentIndex = (pos - 1) / 2;

            if (myHeap.get(pos).compareTo(myHeap.get(parentIndex)) < 0) {
                swap(pos, parentIndex);
                pos = parentIndex;
            } else {
                break;
            }
        }

        // sift down if needed
        while (true) {
            int leftChild = (2 * pos) + 1;
            int rightChild = (2 * pos) + 2;
            int smallest = pos;

            // Check left child
            if (leftChild < myHeap.size() &&
                    myHeap.get(leftChild).compareTo(myHeap.get(smallest)) < 0) {
                smallest = leftChild;
            }

            // Check right child
            if (rightChild < myHeap.size() &&
                    myHeap.get(rightChild).compareTo(myHeap.get(smallest)) < 0) {
                smallest = rightChild;
            }

            // If current node is already smallest, stop
            if (smallest == pos) {
                break;
            }

            // Swap and continue downward
            swap(pos, smallest);
            pos = smallest;
        }
    }

    /**
     * Finds and removes the given element from the queue.
     * Returns true if an element was deleted from the queue,
     * false otherwise.
     *
     * @param element the element to be removed from the queue
     * @return true if an element was removed from the queue, false otherwise
     */
    public boolean remove(E element) {
        // Empty heap
        if (myHeap.isEmpty()) {
            return false;
        }

        // Find the element
        int pos = -1;
        for (int i = 0; i < myHeap.size(); i++) {
            if (myHeap.get(i).equals(element)) {
                pos = i;
                break; // remove the first matching element
            }
        }

        // Element not found
        if (pos == -1) {
            return false;
        }

        // If removing the last element, just remove it
        int lastIndex = myHeap.size() - 1;
        if (pos == lastIndex) {
            myHeap.remove(lastIndex);
            return true;
        }

        // Replace the element with the last element
        myHeap.set(pos, myHeap.get(lastIndex));
        myHeap.remove(lastIndex);

        // Restore heap property
        heapify(pos);

        return true;
    }




    /**
     * Returns the number of elements in the queue
     *
     * @return the number of elements in the queue
     */
    public int size() {
        return myHeap.size();
    }

    /**
     * Returns the String representation of the heap
     * (by the order of list, each element separated
     * with a single space)
     *
     * @return the String representation of the heap
     */
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (E item : myHeap) {
            sb.append(item).append(" ");
        }

        return sb.toString().trim(); //remove the trailing space
    }

}