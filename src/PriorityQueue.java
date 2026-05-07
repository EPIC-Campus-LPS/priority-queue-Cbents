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
            if (n == element){
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
        if (myHeap.isEmpty()){
            return null;
        }
        E temp = myHeap.get(0);
        myHeap.set(0, myHeap.get(-1)); // Set root to the last element
        myHeap.set(-1, temp); //Set last value in list to root
        myHeap.remove(-1); // remove root
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

        while (pos > 0) {
            int parentIndex = (pos - 1) / 2;
            int leftChild = (2 * pos) + 1;
            int rightChild = (2 * pos) + 2;

            if (myHeap.get(pos).compareTo(myHeap.get(parentIndex)) < 0) {
                swap(pos, parentIndex);
                pos = parentIndex; // move upward
            }

            else if (myHeap.get(pos).compareTo(myHeap.get(leftChild)) > 0 && myHeap.get(pos).compareTo(myHeap.get(rightChild)) > 0){
                if (myHeap.get(rightChild).compareTo(myHeap.get(leftChild)) > 0){ //If right child > left child
                    swap(pos, leftChild);
                    pos = leftChild;
                }
                else if (myHeap.get(rightChild).compareTo(myHeap.get(leftChild)) < 0){// If right < left
                    swap(pos, rightChild);
                    pos = rightChild;
                }
                else{
                    swap(pos, leftChild);
                    pos = leftChild;
                }
            }

            else if (myHeap.get(pos).compareTo(myHeap.get(leftChild)) > 0){ // > left, swap with left
                swap(pos, leftChild);
                pos = leftChild;
            }
            else if (myHeap.get(pos).compareTo(myHeap.get(rightChild)) > 0){ // > right, swap with right
                swap(pos, rightChild);
                pos = rightChild;
            }
            else {
                break;
            }

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
        int pos = -1000;
        if (myHeap.isEmpty()){
            return false;
        }
        for (int i = 0; i < myHeap.size(); i++){
            if (myHeap.get(i).equals(element)){
                pos = i;
            }
        }
        if (pos == -1000){
            return false;
        }
        else{
            E temp = myHeap.get(pos);
            myHeap.set(pos, myHeap.get(-1));
            myHeap.set(-1, temp);
            myHeap.remove(-1);
            heapify(pos);

            return true;
        }




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