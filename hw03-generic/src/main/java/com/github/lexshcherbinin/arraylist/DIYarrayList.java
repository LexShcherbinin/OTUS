package com.github.lexshcherbinin.arraylist;

import java.util.*;

import static java.util.stream.Collectors.toList;

public class DIYarrayList<T> implements List<T> {
    private int size;
    private T[] elementData;

    public DIYarrayList() {
        this.elementData = getNewArray(0);
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
//        T1[] newElementData = (T1[]) getNewArray(size);
//        System.arraycopy(elementData, 0, newElementData, 0, size);
//        return newElementData;
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(T t) {
        T[] newElementData = getNewArray(size + 1);
        System.arraycopy(elementData, 0, newElementData, 0, size);

        newElementData[size] = t;

        elementData = getNewArray(++size);
        System.arraycopy(newElementData, 0, elementData, 0, size);

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
        for (T t : c) {
            add(t);
        }

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        for (T t : c) {
            add(index, t);
        }

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
        T[] newElementData = getNewArray(size);
        System.arraycopy(elementData, 0, newElementData, 0, size);

        add(index, element);
        return newElementData[index];
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);

        T[] newElementData = getNewArray(size + 1);
        System.arraycopy(elementData, 0, newElementData, 0, size);

        for (int i = size; i > index; i--) {
            newElementData[i] = elementData[i-1];
        }

        newElementData[index] = element;

        elementData = getNewArray(++size);
        System.arraycopy(newElementData, 0, elementData, 0, size);
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T returnValue = elementData[index];

        T[] newElementData = getNewArray(size - 1);
        System.arraycopy(elementData, 0, newElementData, 0, index);
        System.arraycopy(elementData, index + 1, newElementData, index, size - index - 1);

        elementData = getNewArray(size--);
        System.arraycopy(newElementData, 0, elementData, 0, size);

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
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException("toIndex +  <  + fromIndex");
        }

        if ((fromIndex > size - 1) || (toIndex > size - 1)) {
            throw new IndexOutOfBoundsException("fromIndex or toIndex > size");
        }

        T[] newElementData = getNewArray(toIndex - fromIndex + 1);
        System.arraycopy(elementData, fromIndex, newElementData, 0, toIndex - fromIndex + 1);

        return Arrays.stream(newElementData).collect(toList());
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

    private T[] getNewArray(int size) {
        return (T[]) new Object[size];
    }
}