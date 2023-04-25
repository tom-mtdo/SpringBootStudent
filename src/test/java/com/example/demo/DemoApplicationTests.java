package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class DemoApplicationTests {

	class Calculator {
		int add(int a, int b) {
			return a + b;
		}
	}

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddNumbers() {
		// given - input
		int num1 = 10;
		int num2 = 20;

		// when - method being tested
		int result = underTest.add(num1, num2);

		// then - expect result
		int expected = 30;
		assertThat(result).isEqualTo(expected);
	}



}
