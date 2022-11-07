package com.example.calculator;

import jdk.jfr.Category;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*import com.example.test.category.UnitTests;
import org.junit.experimental.categories.Category;*/

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@Category(UnitTests.class)
public class CalculatorTest {

    @Mock
    private NumberSource numberSource;

    // Class Under Test
    private Calculator cut;

    @BeforeAll
    public void beforeAll(){
        MockitoAnnotations.openMocks(this);
    }
    @BeforeEach
            public void beforeEach(){
        cut = new Calculator(numberSource);
    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_PositiveAndPositive_ReturnPositive(long value){

        // Arrange
        when( numberSource.next()).thenReturn(value, value);
        // Act.
        long result = cut.multiply();
        // Assert.
        assertTrue(result > 0);



    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_NegativeAndPositive_ReturnNegative(long value){

        // Arrange
        when( numberSource.next()).thenReturn(-value, value);
        // Act.
        long result = cut.multiply();
        // Assert.
        assertTrue(result < 0);

    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_PositiveAndNegative_ReturnNegative(long value){
        // Arrange
        when( numberSource.next()).thenReturn(value, -value);
        // Act.
        long result = cut.multiply();
        // Assert.
        assertTrue(result < 0);

    }

    @ParameterizedTest
    @ValueSource(longs = {1L, 10L, 100L, Long.MAX_VALUE})
    public void calculator_Multiply_NegativeAndNegative_ReturnPositive(long value){
        // Arrange
        when( numberSource.next()).thenReturn(-value, -value);
        // Act.
        long result = cut.multiply();
        // Assert.
        assertTrue(result > 0);

    }
}
