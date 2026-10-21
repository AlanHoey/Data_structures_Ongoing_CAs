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

    public int size() {
        return size;
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is outside bounds of array");
        }
        return data[index];
    }

    public int indexOf(String target){
        for (int i = 0; i < size; i++) {
            if(data[i].equals(target)){
                return i;
            }
        }

        return -1;
    }

    public void add(String value){
        ensureCapacity();

        data[size] = value;
        size++;
    }

    public void add(int index, String value){
        ensureCapacity();

        System.arraycopy(data,index,data,index+1,(size-index));

        size++;
        data[index] = value;

    }

    public String set(int index, String value){

        String removed = data[index];
        data[index] = value;

        return removed;
    }

    public String remove(int index) {
        String removed = data[index];

        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;

        return removed;
    }

    public int removeAll(String value){
        int count = 0;
        for (int i = 0; i < size;) {
            if(data[i].equals(value)){
                for (int j = i; j < size - 1; j++) {
                    data[j] = data[j + 1];
                }
                data[size - 1] = null;
                size--;
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
        int toBeJoinedSize = toBeJoined.size();
        for (int i = 0; i < toBeJoinedSize; i++) {
            add(toBeJoined.get(i));
        }
    }

}

