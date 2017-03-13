import java.lang.reflect.Field;

public class TestClojure {
    public static void main(String[] args) {
        final String[] students = {"Carlos", "Alexandre", "Fattor"};
        new Runnable() {
            public void run() {
                for (String student : students)
                    System.out.print(student);
            }
        }.run();
    }
}
