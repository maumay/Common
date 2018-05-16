package xawd.jflow.iterators.abstractflows.combinetests;

import static java.util.Arrays.asList;

import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractFlow;
import xawd.jflow.iterators.abstractiterables.AbstractFlowIterable;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;
import xawd.jflow.iterators.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractFlowCombineTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractFlowIterable<String> small = getSmallObjectTestIteratorProvider();
		final AbstractFlowIterable<String> mid = getObjectTestIteratorProvider();
		final AbstractFlowIterable<String> large = getLargeObjectTestIteratorProvider();
		final AbstractFlowIterable<String> empty = getEmptyObjectTestIteratorProvider();

		assertObjectIteratorAsExpected(asList("010", "111"), createCombineIteratorProviderFrom(mid, small, (s1, s2) -> s1 + s2));
		assertObjectIteratorAsExpected(asList("00", "11", "22", "33", "44"), createCombineIteratorProviderFrom(mid, mid, (s1, s2) -> s1 + s2));
		assertObjectIteratorAsExpected(asList("010", "111", "212", "313", "414"), createCombineIteratorProviderFrom(mid, large, (s1, s2) -> s1 + s2));

		assertObjectIteratorAsExpected(asList(), createCombineIteratorProviderFrom(mid, empty, (s1, s2) -> s1 + s2));
		assertObjectIteratorAsExpected(asList(), createCombineIteratorProviderFrom(empty, empty, (s1, s2) -> s1 + s2));
		assertObjectIteratorAsExpected(asList(), createCombineIteratorProviderFrom(empty, mid, (s1, s2) -> s1 + s2));
	}

	private <E1, E2, R> AbstractFlowIterable<R> createCombineIteratorProviderFrom(
			final AbstractFlowIterable<E1> first, final AbstractFlowIterable<E2> second, final BiFunction<E1, E2, R> combiner)
	{
		return new AbstractFlowIterable<R>() {
			@Override
			public AbstractFlow<R> iter() {
				return first.iter().combineWith(second.iter(), combiner);
			}
		};
	}
}
