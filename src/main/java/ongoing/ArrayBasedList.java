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
}

