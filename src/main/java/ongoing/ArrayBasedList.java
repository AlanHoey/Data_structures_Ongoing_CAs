package ongoing;

/**
 * A dynamic list of strings using an array.
 */
public class ArrayBasedList {
    private static final int EXPANSION_MULTIPLIER = 2;
    private int size = 0;
    private String[] data = new String[10];

    /**
     * This will double the size of the array if it is full.
     */
    private void ensureCapacity() {
        if (size == data.length) {
            String[] temp = new String[data.length * EXPANSION_MULTIPLIER]; // new array with doubled capacity

            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
    }

    /**
     * Will check that an index is valid.
     *
     * @param index the index to check.
     * @throws IndexOutOfBoundsException if the index is outside of the list.
     */
    private void validateIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index "+ index + " Is outside bounds of list");
        }
    }

    /**
     * Checks that a value is not null.
     *
     * @param value the value to check.
     * @throws IllegalArgumentException if the value is null.
     */
    private void validateString(String value){
        if(value == null){
            throw new IllegalArgumentException("Value cannot be null");
        }
    }

    /**
     * Get the size of the list.
     *
     * @return the number of elements in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Gets the element at an index.
     *
     * @param index the index of the element.
     * @return the element at that index.
     * @throws IndexOutOfBoundsException if the index is outside the list.
     */
    public String get(int index) {
        validateIndex(index);
        return data[index];
    }

    /**
     * Finds the first index of a value.
     *
     * @param target the value to find.
     * @return the index of the value, or -1 if not found.
     * @throws IllegalArgumentException if the target is null.
     */
    public int indexOf(String target){
        validateString(target);
        for (int i = 0; i < size; i++) {
            if(data[i].equals(target)){
                return i;
            }
        }

        return -1;
    }

    /**
     * Add a value to the end of the list.
     *
     * @param value the value to add.
     * @throws IllegalArgumentException if the value is null.
     */
    public void add(String value){
        validateString(value);
        ensureCapacity();

        data[size] = value;
        size++;
    }

    /**
     * Add a value at an index, moving later elements along.
     *
     * @param index the index to add the value at.
     * @param value the value to add.
     * @throws IllegalArgumentException  if the value is null.
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than the size.
     */
    public void add(int index, String value){
        validateString(value);
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " Is outside bounds of list");
        }
        ensureCapacity();

        // This will shift elements from their index forwards one place to create a space for the new value be be added
        System.arraycopy(data,index,data,index+1,(size-index));

        size++;
        data[index] = value;

    }

    /**
     * Replaces the element at an index.
     *
     * @param index the index to replace.
     * @param value the new value.
     * @return the old element that was replaced.
     * @throws IllegalArgumentException  if the value is null.
     * @throws IndexOutOfBoundsException if the index is outside the list.
     */
    public String set(int index, String value){
        validateString(value);
        validateIndex(index);

        String replaced = data[index];
        data[index] = value;

        return replaced;
    }

    /**
     * Removes the element at its index position.
     *
     * @param index the index to remove.
     * @return the removed element.
     * @throws IndexOutOfBoundsException if the index is outside the list.
     */
    public String remove(int index) {
        validateIndex(index);
        String removed = data[index];

        // shift later elements one place to the left to close the space.
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null; // clear the leftover duplicate reference at the previous last position.
        size--;

        return removed;
    }

    /**
     * Removes all occurrences of a value.
     *
     * @param value the value to remove.
     * @return the number of elements removed.
     * @throws IllegalArgumentException if the value is null.
     */
    public int removeAll(String value){
        validateString(value);
        int count = 0;
        for (int i = 0; i < size;) {
            if(data[i].equals(value)){
                // i is not advanced after a removal, as the next element has shifted into position i.
                remove(i);
                count++;
            } else {
                i++;
            }
        }
        return count;
    }

    /**
     * Remove all elements from the list.
     */
    public void clear(){
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * Create a new list with each element appearing once.
     * This list is not changed.
     *
     * @return the new list without duplicates.
     */
    public ArrayBasedList deduplicate() {
        ArrayBasedList current = new ArrayBasedList();

        for (int i = 0; i < size; i++) {
            // only add the element if the new list does not already contain it.
            if (current.indexOf(data[i]) == -1) {
                current.add(data[i]);
            }
        }
        return current;
    }

    /**
     * Adds all elements of another list to the end of this list.
     *
     * @param toBeJoined the list to add.
     * @throws IllegalArgumentException if the list is null.
     */
    public void join(ArrayBasedList toBeJoined) {
        if (toBeJoined == null) {
            throw new IllegalArgumentException("List to join cannot be null");
        }
        // the size is read once so that joining a list to itself will not loop forever.
        int toBeJoinedSize = toBeJoined.size();
        for (int i = 0; i < toBeJoinedSize; i++) {
            add(toBeJoined.get(i));
        }
    }

}



