package generics;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArraySet<T> implements Iterable<T> { //Coleccion que pueda tener un iterador (ITERATOR)

    private final T[] array;    // Mismo tipo que entra
    private int firstEmptyPos;  // 1r Posicion que tiene


    /**
     *
     * @param c
     * @param size
     */
    public ArraySet(Class<T> c, int size){
        firstEmptyPos = 0;
        //@SuppressWarnings("unchecked")
        array = (T[]) Array.newInstance(c, size); //Per crear nou array
    }

    //Array mida fija, no elementos repetidos, no posiciones vacias, no hace falta orden
    //TODO: Complete (Crear metodos para añadir, consultar, eliminar, si esta vacio, etc)

    //Add --> Se añade cuando tienes huecos o si no esta, si no devuelve falso

    /**
     *
     * @param element
     * @return
     */
    public boolean add (T element){
        if (firstEmptyPos == array.length || contains(element)) { return false; }

        array[firstEmptyPos] = element;
        firstEmptyPos ++;
        return true;

        //returet --> Elemento que devuelve si es validan true;
    }


    //Get --> Elemento que devuelve si es valida

    /**
     *
     * @param index
     * @return
     */
    public T get(int index){
        if (array[index] == null) {
            throw new IndexOutOfBoundsException("The element in the position: " + index + " in the list, is null");
        } else if (index > array.length){
            throw new IndexOutOfBoundsException("The index: " + index + " is bigger than the length of the list: " + array.length);
        }

        return array[index];
    }

    //isEmpty --> Mirar si hay algun elemento
    /**
     *
     * @return
     */
    public boolean isEmpty (){ return (firstEmptyPos == 0); }


    // Contains --> CERCA Y RECORREGUT
    /**
     *
     * @param element
     * @return
     */
    public boolean contains (T element){
        for (T arrayElement : array){
            if (arrayElement.equals(element)){ return true; }
        }
        return false;
    }

    // remove --> Si no esta, return false, si esta, se elimina (no posiciones vacias entre medio, garantir que este junto, pero no orden)

    /**
     *
     * @param element
     * @return
     */
    public boolean remove (T element){
        for (int i = 0; i < array.length; i++) {

            if (array[i].equals(element)){
                array[i] = null;
                firstEmptyPos--;

                array[i] = array[firstEmptyPos];
                array[firstEmptyPos] = null;

                return true;

            }
        }

        return false;
    }

    //size --> cuantos elementos hay

    /**
     *
     * @return
     */
    public int size (){ return firstEmptyPos; }

    // MaxSize -->

    /**
     *
     * @return
     */
    public int maxSize (){ return array.length;}

    /**
     *
     * @return
     */
    @Override
    public Iterator<T> iterator() { return new ArraySetIterator(); }

    // Clase que representa el ITEARTIR de arraySet Hash
    
    private class ArraySetIterator implements Iterator<T>{
        //TODO: Complete

        int currentPos = 0; //Contains index

        /**
         *
         * @return
         */
        @Override
        public boolean hasNext() { return firstEmptyPos <= currentPos+1; }

        /**
         *
         * @return
         */
        @Override
        public T next() {
            if (hasNext()) {
                currentPos++;
                return array[currentPos];
            }

            throw new NoSuchElementException("The next position: " + currentPos + " doesn't exist");
        }
    }

}
