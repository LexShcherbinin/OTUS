package com.github.lexshcherbinin.arraylist;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static com.github.lexshcherbinin.arraylist.ArrayCreator.getRandomNumberArray;
import static org.assertj.core.api.Assertions.assertThat;

public class DIYarrayListTests {

    @Test
    @DisplayName("addAll")
    void shouldAddAllElementsFromOtherCollection(){
        List<Integer> testList = new DIYarrayList<>();
        Integer[] expectedData = getRandomNumberArray(50);

        Collections.addAll(testList, expectedData);
        assertThat(testList).containsExactly(expectedData);
    }

    @Test
    @DisplayName("copy")
    void shouldCorrectCopyAllElementsFromOtherCollection(){
        List<Integer> testList = new DIYarrayList<>();

        List<Integer> expectedData = Arrays.asList(getRandomNumberArray(50))
            .stream()
            .peek(i -> testList.add(0))
            .collect(Collectors.toList());

        Collections.copy(testList, expectedData);
        assertThat(testList).containsExactlyElementsOf(expectedData);
    }

    @Test
    @DisplayName("sort")
    void shouldCorrectSortElements(){
        Integer[] array = getRandomNumberArray(50);

        List<Integer> testList = new DIYarrayList<>();
        Collections.addAll(testList, array);

        List<Integer> expectedData = Arrays.asList(array)
            .stream()
            .sorted()
            .collect(Collectors.toList());

        Collections.sort(testList, Comparator.naturalOrder());
        assertThat(expectedData.equals(testList)).isTrue();
    }
}