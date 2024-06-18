import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MyResultTest {
    @Test
    public void testSuccessResult() {
        MyResult<Integer> result = MyResult.success(42);

        assertAll(
            () -> assertTrue(result.isSuccess()),
            () -> assertFalse(result.isFailure()),
            () -> assertEquals(42, result.getOrNull()),
            () -> assertNull(result.exceptionOrNull())
        );

    }

    @Test
    public void testFailureResult() {
        Exception exception = new RuntimeException("Error");
        MyResult<Integer> result = MyResult.failure(exception);

        assertAll(
            () -> assertTrue(result.isFailure()),
            () -> assertFalse(result.isSuccess()),
            () -> assertEquals(exception, result.exceptionOrNull()),
            () -> assertNull(result.getOrNull())
        );
    }

    @Test
    public void testOnSuccess() {
        MyResult<Integer> result = MyResult.success(42);

        result.onSuccess(value -> assertEquals(42, value.intValue()))
            .onFailure(error -> fail("Should not be a failure"));
    }

    @Test
    public void testOnFailure() {
        Exception exception = new RuntimeException("Error");
        MyResult<Integer> result = MyResult.failure(exception);

        result.onFailure(error -> assertEquals("Error", error.getMessage()))
            .onSuccess(value -> fail("Should not be a success"));
    }

}