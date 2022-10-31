package org.example;

import com.sun.jdi.InvalidCodeIndexException;

import java.util.Arrays;

public class StringArrayList implements StringList {

    private String[] list;
    private int size;
    private final int DEFAULT_SIZE = 10;

    public StringArrayList() {
        this.list = new String[DEFAULT_SIZE];
    }
    public StringArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity!");
        }
        this.list = new String[capacity];
    }

    private void expand() {
        int capacityOld = size;
        int capacityNew = capacityOld + 5;
        list = Arrays.copyOf(list, capacityNew);
    }

    @Override
    public String add(String item) {
        if (this.checkIfFull()) {
            expand();
        }
        nullCheck(item);
        list[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkIndex(index);
        nullCheck(item);
        if (this.checkIfFull()) {
            expand();
        }
        if (index == size) {
            list[size++] = item;
        } else {
            System.arraycopy(list, index, list, index + 1, size - index);
            list[index] = item;
            size++;
        }
        return item;
    }

    @Override
    public String set(int index, String item) {
        checkIndex(index);
        nullCheck(item);
        list[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        nullCheck(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new NotFoundException();
        }
        return remove(index);
    }

    @Override
    public String remove(int index) {
        checkIndex(index);
        String item = list[index];
        if (index != size) {
            System.arraycopy(list, index + 1, list, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        checkIndex(index);
        return list[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        this.list = new String[size];
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(list, size);
    }

    private void nullCheck(String item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private boolean checkIfFull() {
        return size == list.length;
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    public String[] getList() {
        return list;
    }

    public int getSize() {
        return size;
    }


}
