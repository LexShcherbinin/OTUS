package com.github.lexshcherbinin.arraylist;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class DIYarrayList<T> implements List<T> {
    private int size;
    private T[] elementData;

    public DIYarrayList() {
        this.elementData = (T[]) new Object[0];
        this.size = 0;
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
    public boolean contains(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return true;
                }
            }

        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return Arrays.stream(elementData).iterator();
    }

    @Override
    public Object[] toArray() {
        return elementData;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        elementData = Arrays.copyOf(elementData, size + 1);
        elementData[size++] = t;

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i].equals(o)) {
                    remove(i);
                    return true;
                }
            }

        } else {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    remove(i);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        T[] cArray = (T[]) c.toArray();
        elementData = Arrays.copyOf(elementData, size + cArray.length);
        System.arraycopy(cArray, 0, elementData, size, cArray.length - size);
        size = size + cArray.length;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndex(index);
        T[] cArray = (T[]) c.toArray();
        elementData = Arrays.copyOf(elementData, size + cArray.length);
        System.arraycopy(elementData, index, elementData, index + cArray.length, size - index);
        System.arraycopy(cArray, 0, elementData, index, cArray.length);

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        for (Object o : c) {
            remove(o);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i ++) {
            elementData[i] = null;
        }

        size = 0;
    }

    @Override
    public T get(int index) {
        return elementData[index];
    }

    @Override
    public T set(int index, T element) {
        T returnValue = elementData[index];
        add(index, element);
        return returnValue;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);

        elementData = Arrays.copyOf(elementData, size + 1);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T returnValue = elementData[index];
        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[--size] = null;

        return returnValue;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }

        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (elementData[i] == null) {
                    return i;
                }
            }

        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        return Arrays.asList(elementData).listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex)
            throw new IndexOutOfBoundsException("toIndex +  <  + fromIndex");

        if ((fromIndex > size - 1) || (toIndex > size - 1))
            throw new IndexOutOfBoundsException("fromIndex or toIndex > size");

        System.arraycopy(elementData, fromIndex, elementData, 0, toIndex - fromIndex + 1);

        return Arrays.stream(elementData).collect(toList());
    }

    @Override
    public String toString() {
        Iterator<T> it = iterator();
        if (! it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (;;) {
            T t = it.next();
            sb.append(t == this ? "(this Collection)" : t);
            if (! it.hasNext())
                return sb.append(']').toString();
            sb.append(',').append(' ');
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс " + index);

        }
    }
}