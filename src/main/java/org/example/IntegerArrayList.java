package org.example;

import java.util.Arrays;

public class IntegerArrayList implements IntegerList {

    private Integer[] list;
    private int size;
    private final int DEFAULT_SIZE = 10;

    public IntegerArrayList() {
        this.list = new Integer[DEFAULT_SIZE];
    }
    public IntegerArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Illegal capacity!");
        }
        this.list = new Integer[capacity];
    }

    private void grow() {
        int capacityOld = size;
        int capacityNew = capacityOld + (capacityOld/2);
        list = Arrays.copyOf(list, capacityNew);
    }

    @Override
    public Integer add(Integer item) {
        checkIfFullAndGrow();
        nullCheck(item);
        list[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkIndex(index);
        nullCheck(item);
        checkIfFullAndGrow();
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
    public Integer set(int index, Integer item) {
        checkIndex(index);
        nullCheck(item);
        list[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        nullCheck(item);
        int index = indexOf(item);
        if (index == -1) {
            throw new NotFoundException();
        }
        return remove(index);
    }

    @Override
    public Integer remove(int index) {
        checkIndex(index);
        Integer item = list[index];
        if (index != size) {
            System.arraycopy(list, index + 1, list, index, size - index);
        }
        size--;
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        Integer[] listCopy = toArray();
        sort(listCopy);
        return binarySearch(listCopy, item);
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (list[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return list[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
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
        this.list = new Integer[size];
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(list, size);
    }

    private void nullCheck(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }

    private void checkIfFullAndGrow() {
        if (size == list.length) {
            grow();
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index > size) {
            throw new InvalidIndexException();
        }
    }

    public Integer[] getList() {
        return list;
    }

    public int getSize() {
        return size;
    }

    private void sort(Integer[] list) {
        quickSort(list, 0, list.length - 1);
    }

    public static void quickSort(Integer[] arr, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(arr, begin, end);
            quickSort(arr, begin, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, end);
        }
    }
    private static int partition(Integer[] arr, int begin, int end) {
        int pivot = arr[end];
        int i = (begin - 1);
        for (int j = begin; j < end; j++) {
            if (arr[j] <= pivot) {
                i++;
                swapElements(arr, i, j);
            }
        }
        swapElements(arr, i + 1, end);
        return i + 1;
    }

    private static void swapElements(Integer[] arr, int left, int right) {
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private boolean binarySearch(Integer[] list, Integer item) {
        int min = 0;
        int max = list.length - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item.equals(list[mid])) {
                return true;
            }
            if (item < list[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }
}
