package com.github.maumay.jflow.examples.convenient;

import static com.github.maumay.jflow.vec.Vec.vec;
import static java.util.Arrays.asList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.BinaryOperator;

import com.github.maumay.jflow.iterators.factories.Iter;
import com.github.maumay.jflow.utils.Option;
import com.github.maumay.jflow.vec.IntVec;

/**
 * The {@link Iter} class contains a multitude of static factory methods for
 * constructing new enhanced iterators.
 * 
 * @author thomasb
 */
public final class IterExamples
{
	enum MyEnum
	{
		A, B, C;
	}

	public static void main(String[] args)
	{
		// Construct from varargs
		assert Iter.over(1, 2, 3).toVec().equals(vec(1, 2, 3));

		// Construct from existing collections
		assert Iter.over(Arrays.asList(1, 2, 3)).toVec().equals(vec(1, 2, 3));
		assert Iter.reverse(Arrays.asList(1, 2, 3)).toVec().equals(vec(3, 2, 1));

		// *****************************************************************************************
		// Construct from enumerated types
		assert Iter.over(MyEnum.class).toVec().equals(vec(MyEnum.A, MyEnum.B, MyEnum.C));

		// *****************************************************************************************
		// Construct from optionals
		assert Iter.option(Option.empty()).toVec().equals(vec());
		assert Iter.option(Option.of("a")).toVec().equals(vec("a"));

		// Example usage is flattening a sequence of optional values
		assert Iter.over(Option.of("a"), Option.empty(), Option.of("b")).flatMap(Iter::option)
				.toVec().equals(vec("a", "b"));

		// *****************************************************************************************
		// Flatten stacked collections
		Iterable<Iterable<String>> stacked = asList(asList("a"), new HashSet<>(), vec("b"));
		assert Iter.flatten(stacked).toVec().equals(vec("a", "b"));
		assert Iter.flatten(stacked).anyMatch(s -> s.equals("a"));

		// *****************************************************************************************
		// Construct from maps
		Map<Integer, Integer> map = new HashMap<>();
		map.put(1, 10);
		map.put(2, 20);

		BinaryOperator<Integer> sum = (a, b) -> a + b;
		assert Iter.keys(map).fold(sum) == 3;
		assert Iter.values(map).fold(sum) == 30;
		assert Iter.entries(map).map(pair -> pair._1 * pair._2).fold(sum) == 50;

		// *****************************************************************************************
		// Construct primitive iterators
		assert Iter.ints(1, 2, 3).anyMatch(n -> n == 2);
		assert Iter.doubles(1.0, 2.0, 3.0).allMatch(n -> n > 0);
		assert Iter.longs(1, 2, 3).noneMatch(n -> n > 3);

		// *****************************************************************************************
		// Construct primitive number ranges
		assert Iter.until(5).toVec().equals(IntVec.of(0, 1, 2, 3, 4));
		assert Iter.between(1, 4).toVec().equals(IntVec.of(1, 2, 3));
		assert Iter.between(1, 10, 2).toVec().equals(IntVec.of(1, 3, 5, 7, 9));
		assert Iter.between(9, 0, -2).toVec().equals(IntVec.of(9, 7, 5, 3, 1));

		System.out.println("All assertions passed");
	}

}