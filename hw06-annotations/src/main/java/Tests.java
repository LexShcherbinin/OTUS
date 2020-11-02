import static org.assertj.core.api.Assertions.assertThat;

public class Tests {
    private static final String SUCCESSFUL_MESSAGE = "Test successful";

    @Before
    void beforeMethod() {
        System.out.println("<=====Start_Test=====>");
    }

    @After
    void afterMethod() {
        System.out.println("<======End_Test======>\n\n");
    }

    @Test
    void testMethod1() {
        assertThat(1 + 1 == 2).isTrue();
        System.out.println(SUCCESSFUL_MESSAGE);
    }

    @Test
    void testMethod2() {
        assertThat(2 + 2 == 4).isTrue();
        System.out.println(SUCCESSFUL_MESSAGE);
    }

    @Test
    void testMethod3() {
        assertThat(3 + 3 == 7).isTrue();
        System.out.println(SUCCESSFUL_MESSAGE);
    }

    @Test
    void testMethod4() {
        assertThat(4 + 4 == 8).isTrue();
        System.out.println(SUCCESSFUL_MESSAGE);
    }

    @Test
    void testMethod5() {
        assertThat(5 + 5 == 10).isTrue();
        System.out.println(SUCCESSFUL_MESSAGE);
    }

    @Test
    void testMethod6() {
        assertThat(6 + 6 == 11).isTrue();
        System.out.println(SUCCESSFUL_MESSAGE);
    }

    @Test
    void testMethod7() {
        assertThat(7 + 7 == 14).isTrue();
        System.out.println(SUCCESSFUL_MESSAGE);
    }

    @Test
    void testMethod8() {
        assertThat(8 + 8 == 17).isTrue();
        System.out.println(SUCCESSFUL_MESSAGE);
    }

    @Test
    void testMethod9() {
        assertThat(9 + 9 == 18).isTrue();
        System.out.println(SUCCESSFUL_MESSAGE);
    }

    @Test
    void testMethod10() {
        assertThat(10 + 10 == 20).isTrue();
        System.out.println(SUCCESSFUL_MESSAGE);
    }
}