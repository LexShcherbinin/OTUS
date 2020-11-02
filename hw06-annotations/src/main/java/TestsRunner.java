import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestsRunner {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        annotationReader("Tests");
    }

    private static void annotationReader(String className) throws InvocationTargetException, IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InstantiationException {
        List<Method> methodList = Arrays.asList(Class.forName(className).getDeclaredMethods());

        List<Method> beforeMethodList = methodFilter(methodList, Before.class);
        List<Method> afterMethodList = methodFilter(methodList, After.class);
        List<Method> testMethodList = methodFilter(methodList, Test.class);

        int failCount = 0;

        for (Method testMethod : testMethodList) {
            Object instance = Class.forName(className).getConstructor().newInstance();

            for (Method beforeMethod : beforeMethodList) {
                beforeMethod.invoke(instance);
            }

            try {
                testMethod.invoke(instance);

            } catch (Exception e) {
                failCount++;
                System.out.println("fall test " + testMethod.getName());
            }

            for (Method afterMethod : afterMethodList) {
                afterMethod.invoke(instance);
            }
        }

        System.out.println("<====================>");
        System.out.println("All tests: " + testMethodList.size());
        System.out.println("Test successful: " + (testMethodList.size() - failCount));
        System.out.println("Test fail: " + failCount);
        System.out.println("<====================>");
    }

    private static <T extends Annotation> List<Method> methodFilter(List<Method> methodList, Class<T> annotation) {
        return methodList
            .stream()
            .filter(method -> method.getAnnotation(annotation) != null)
            .collect(Collectors.toList());
    }
}