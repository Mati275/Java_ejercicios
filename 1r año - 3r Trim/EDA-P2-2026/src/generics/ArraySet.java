package generics;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArraySet<T> implements Iterable<T> { //Coleccion que pueda tener un iterador (ITERATOR)

    private final T[] array;    // Mismo tipo que entra
    private int firstEmptyPos;  // 1r Posicion que tiene


    /**
     * Creates the ArraySet
     * @param c
     * @param size
     */
    public ArraySet(Class<T> c, int size){
        firstEmptyPos = 0;
        array = (T[]) Array.newInstance(c, size);
    }

    /**
     * Adds an element to the ArraySet if this isn't on the container before,
     * or if the ArraySet isn't full,
     * or the element passed in parameters isn't null
     * @param element
     * @return if the element is added successfully
     */
    public boolean add (T element){



        if ( firstEmptyPos == array.length || contains( element ) || element == null ) {
            return false;
        }

        array[firstEmptyPos] = element;
        firstEmptyPos ++;

        return true;
    }

    /**
     *
     * @param index
     * @return the element in the position indicated in the index
     * @throws IndexOutOfBoundsException if the index is null or isn't on the range of the filled elements in the array
     */
    public T get( int index ){

        // The index is lower than 0 or the index is higher or equals the firstEmptyPos
        if (index >= firstEmptyPos || index < 0 ){
            throw new IndexOutOfBoundsException("The index: " + index + " is out of bounds, having: " + firstEmptyPos + "of elements in the array");
        }

        // The element in this position is void --> We have the posiblity that the user adds a null value on the list, and we don't want to return void positions
        else if (array[index] == null) {
            throw new IndexOutOfBoundsException("The element in the position: " + index + " in the list, is null");
        }

        return array[index];
    }

    /**
     *
     * @return if the ArraySet hasn't any element
     */
    public boolean isEmpty(){
        return firstEmptyPos == 0;
    }


    /**
     *
     * @param element
     * @return if the element in the parameter is on the array
     */
    public boolean contains(T element){

        // If the ArraySet isn't empty
        if( !isEmpty() ){

            // Search in the array one element that is the same as the element passed in the parameters
            for (int i = 0; i < firstEmptyPos; i++){

                if (array[i].equals(element)){
                    return true;
                }
            }
        } // The ArraySet is empty / the equivalent element isn't found
        return false;

    }


    /**
     *
     * @param element
     * @return if the element is found and removed
     */
    public boolean remove (T element){

        // Search for an element that is the same that the element passed in the parameters
        for (int i = 0; i < firstEmptyPos; i++) {

            // If it's found
            if (array[i].equals(element)){
                array[i] = null;                    // Remove the element
                firstEmptyPos--;                    // Substract one to the total of elements (the first empty position)

                array[i] = array[firstEmptyPos];    // Fill the void position
                array[firstEmptyPos] = null;        // Make the firstEmptyPos a null value


                return true;

            }
        } // The element isn't found

        return false;
    }

    /**
     *
     * @return the number of elements in the ArraySet
     */
    public int size (){
        return firstEmptyPos;
    }

    /**
     *
     * @return the maximum size of the ArraySet
     */
    public int maxSize (){
        return array.length;
    }

    /**
     *
     * @return The inner class ArraySetIterator for this ArraySet
     */
    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }


    private class ArraySetIterator implements Iterator<T>{

        int currentPos = 0;


        @Override
        public boolean hasNext() {
            return currentPos + 1 <= firstEmptyPos ; // The next position (currentPos + 1) is less than or equals firstEmptyPos, this means that the current position isn't the last
        }

        /**
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if it hasn't a next position
         */
        @Override
        public T next() {
            // If it has a next element
            if (hasNext()) {
                currentPos++;
                return array[currentPos -1];
            } // If it hasn't a next element

            throw new NoSuchElementException("The next position: " + currentPos + " doesn't exist");
        }
    }

}