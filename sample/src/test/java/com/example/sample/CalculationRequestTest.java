package com.example.sample;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CalculationRequestTest {
    @Test
    public void 유효한_숫자를_파싱할_수_있다() {
        // given
        String[] parts = {"2", "+", "3"};

        // when
        CalculationRequest request = new CalculationRequest(parts);

        // then
        assertEquals(2, request.getNum1());
        assertEquals("+", request.getOperator());
        assertEquals(3, request.getNum2());
    }

    @Test
    public void 세자리_숫자가_넘어가는_유효한_숫자를_파싱할_수_있다() {
        // given
        String[] parts = {"232", "+", "123"};

        // when
        CalculationRequest request = new CalculationRequest(parts);

        // then
        assertEquals(232, request.getNum1());
        assertEquals("+", request.getOperator());
        assertEquals(123, request.getNum2());
    }

    @Test
    public void 유효한_길이의_숫자가_들어오지_않으면_에러를_던진다() {
        // given
        String[] parts = {"232", "+"};

        // when
        // then
        assertThrows(BadRequestException.class, () -> {
            new CalculationRequest(parts);
        });
    }

    @Test
    public void 유효하지_않은_연산자가_들어오면_에러를_던진다() {
        // given
        String[] parts = {"232", "x", "123"};

        // when
        // then
        assertThrows(InvalidOperatorException.class, () -> {
            new CalculationRequest(parts);
        });
    }

    @Test
    public void 유효하지_않은_길이의_연산자가_들어오면_에러를_던진다() {
        // given
        String[] parts = {"232", "x2", "123"};

        // when
        // then
        assertThrows(InvalidOperatorException.class, () -> {
            new CalculationRequest(parts);
        });
    }
}
