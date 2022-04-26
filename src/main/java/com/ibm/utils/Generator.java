package com.ibm.utils;

import java.util.Random;
import java.util.function.Supplier;

public class Generator implements Supplier<String> {
	Random random = new Random(50);
	char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

	@Override
	public String get() {
		System.out.print(letters[random.nextInt(letters.length)]);
		return "" + letters[random.nextInt(letters.length)];
	}
}