# MyResult
- Java로 만들어본 Kotlin Result

- 이렇게 쓸 수 있다!

```java
public class Main {

    public static MyResult<Integer> string2integer(String str) {
        try {
            int integer = Integer.parseInt(str);
            return MyResult.success(integer);
        } catch (NumberFormatException e) {
            return MyResult.failure(e);
        }
    }

    public static MyResult<Integer> divide(int a, int b) {
        if (b != 0) {
            return MyResult.success(a / b);
        } else {
            return MyResult.failure(new ArithmeticException("Division by zero"));
        }
    }

    public static void main(String[] args) {

        string2integer("21")
            .onSuccess(value -> System.out.printf("onSuccess: %s%n", value))
            .onFailure(error -> System.out.printf("onFailure: %s%n", error.getMessage()));

        string2integer("wrong number")
            .onSuccess(value -> System.out.printf("onSuccess: %s%n", value))
            .onFailure(error -> System.out.printf("onFailure: %s%n", error.getMessage()));

        divide(5, 2)
            .onSuccess(value -> System.out.printf("onSuccess: %s%n", value))
            .onFailure(error -> System.out.printf("onFailure: %s%n", error.getMessage()));

        divide(5, 0)
            .onSuccess(value -> System.out.printf("onSuccess: %s%n", value))
            .onFailure(error -> System.out.printf("onFailure: %s%n", error.getMessage()));
    }
}
```