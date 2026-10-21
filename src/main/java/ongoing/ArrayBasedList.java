package ongoing;

public class ArrayBasedList {
    private static final int EXPANSION_MULTIPLIER = 2;
    private int size = 0;
    private String[] data = new String[10];

    private void ensureCapacity() {
        if (size == data.length) {
            String[] temp = new String[data.length * EXPANSION_MULTIPLIER];

            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
    }

    private void validateIndex(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("Index "+ index + " Is outside bounds of list");
        }
    }

    private void validateString(String value){
        if(value == null){
            throw new IllegalArgumentException("Value cannot be null");
        }
    }

    public int size() {
        return size;
    }

    public String get(int index) {
        validateIndex(index);
        return data[index];
    }

    public int indexOf(String target){
        validateString(target);
        for (int i = 0; i < size; i++) {
            if(data[i].equals(target)){
                return i;
            }
        }

        return -1;
    }

    public void add(String value){
        validateString(value);
        ensureCapacity();

        data[size] = value;
        size++;
    }

    public void add(int index, String value){
        validateString(value);
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index " + index + " Is outside bounds of list");
        }
        ensureCapacity();

        System.arraycopy(data,index,data,index+1,(size-index));

        size++;
        data[index] = value;

    }

    public String set(int index, String value){
        validateString(value);
        validateIndex(index);

        String replaced = data[index];
        data[index] = value;

        return replaced;
    }

    public String remove(int index) {
        validateIndex(index);
        String removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;

        return removed;
    }

    public int removeAll(String value){
        validateString(value);
        int count = 0;
        for (int i = 0; i < size;) {
            if(data[i].equals(value)){
                remove(i);
                count++;
            } else {
                i++;
            }
        }
        return count;
    }

    public void clear(){
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    public ArrayBasedList deduplicate() {
        ArrayBasedList current = new ArrayBasedList();

        for (int i = 0; i < size; i++) {
            if (current.indexOf(data[i]) == -1) {
                current.add(data[i]);
            }
        }
        return current;
    }

    public void join(ArrayBasedList toBeJoined) {
        if (toBeJoined == null) {
            throw new IllegalArgumentException("List to join cannot be null");
        }
        int toBeJoinedSize = toBeJoined.size();
        for (int i = 0; i < toBeJoinedSize; i++) {
            add(toBeJoined.get(i));
        }
    }

}



