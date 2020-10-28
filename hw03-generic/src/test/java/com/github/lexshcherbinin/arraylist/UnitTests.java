package com.github.lexshcherbinin.arraylist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.lexshcherbinin.arraylist.ArrayCreator.getRandomNumberArray;
import static org.assertj.core.api.Assertions.assertThat;

public class UnitTests {
    private static final List<String> LIST = List.of("0", "1", "2", "3", "4");

    @Test
    @DisplayName("size()")
    void size(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);

        assertThat(list.size() == LIST.size()).isTrue();
        assertThat(list.containsAll(LIST)).isTrue();
    }

    @Test
    @DisplayName("isEmpty()")
    void isEmpty(){
        List<String> list = new DIYarrayList<>();
        assertThat(list.isEmpty()).isTrue();

        list.addAll(LIST);
        assertThat(list.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("contains(Object o)")
    void contains(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);

        assertThat(list.contains("0")).isTrue();
        assertThat(list.contains("9")).isFalse();
    }

    @Test
    @DisplayName("iterator()")
    void iterator(){
        List<String> list = new DIYarrayList<>();
        assertThat(list.iterator().hasNext()).isFalse();

        list.addAll(LIST);
        assertThat(list.iterator().hasNext()).isTrue();
    }

    @Test
    @DisplayName("toArray()")
    void toArray(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);

        assertThat(list.toArray().length == LIST.size()).isTrue();
        assertThat(list.toArray()[0] == "0").isTrue();
    }

    @Test
    @DisplayName("add(T t)")
    void add(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);
        list.add("9");

        assertThat(list.size() == 6).isTrue();
        assertThat(list.get(5).equals("9")).isTrue();
    }

    @Test
    @DisplayName("add(int index, T element)")
    void addIndex(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);
        list.add(3, "9");

        assertThat(list.size() == 6).isTrue();
        assertThat(list.get(3).equals("9")).isTrue();
    }

    @Test
    @DisplayName("addAll(Collection<? extends T> c)")
    void addAllCollection(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);

        assertThat(LIST.equals(list)).isTrue();
    }

    @Test
    @DisplayName("addAll(int index, Collection<? extends T> c)")
    void addAll(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);

        list.addAll(3, List.of("7", "8", "9"));
        List<String> expectedResult = List.of("0", "1", "2", "7", "8", "9", "3", "4");

        assertThat(expectedResult.equals(list)).isTrue();
    }

    @Test
    @DisplayName("remove(int index)")
    void removeIndex(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);
        list.remove(3);

        assertThat(list.size() == 4).isTrue();
        assertThat(list.contains("3")).isFalse();
    }

    @Test
    @DisplayName("remove(Object o)")
    void removeObject(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);
        list.remove("3");

        assertThat(list.size() == 4).isTrue();
        assertThat(list.contains("3")).isFalse();
    }

    @Test
    @DisplayName("removeAll(Collection<?> c)")
    void removeAll(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);
        list.removeAll(List.of("1", "2", "3"));

        assertThat(list.size() == 2).isTrue();
        assertThat(list.get(0).equals("0")).isTrue();
        assertThat(list.get(1).equals("4")).isTrue();
    }

    @Test
    @DisplayName("containsAll(Collection<?> c)")
    void containsAll(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);

        assertThat(list.containsAll(LIST)).isTrue();
    }

    @Test
    @DisplayName("clear()")
    void clear(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);
        list.clear();

        assertThat(list.size() == 0).isTrue();
    }

    @Test
    @DisplayName("get(int index)")
    void get(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);

        assertThat(list.get(3).equals("3")).isTrue();
    }

    @Test
    @DisplayName("set(int index, T element)")
    void set(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);
        String oldElement = list.set(3, "9");

        assertThat(list.size() == 6).isTrue();
        assertThat(list.get(3).equals("9")).isTrue();
        assertThat(list.get(4).equals(oldElement)).isTrue();
    }

    @Test
    @DisplayName("indexOf(Object o)")
    void indexOf(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);

        assertThat(list.indexOf("3") == 3).isTrue();
        assertThat(list.indexOf("9") == -1).isTrue();

    }

    @Test
    @DisplayName("lastIndexOf(Object o)")
    void lastIndexOf(){
        List<String> list = new DIYarrayList<>();
        list.addAll(List.of("1", "2", "3", "3", "3", "4", "3", "3", "5", "6", "7"));

        assertThat(list.lastIndexOf("3") == 7).isTrue();
    }

    @Test
    @DisplayName("subList(int fromIndex, int toIndex)")
    void subList(){
        List<String> list = new DIYarrayList<>();
        list.addAll(LIST);

        List<String> subList = list.subList(2, 3);

        assertThat(subList.get(0).equals("2")).isTrue();
        assertThat(subList.get(1).equals("3")).isTrue();
    }
}