/**
 *
 */
package com.github.maumay.jflow.iterators.impl.zip;

import static java.util.Arrays.asList;

import org.junit.jupiter.api.Test;

import com.github.maumay.jflow.iterators.impl.AbstractEnhancedIterator;
import com.github.maumay.jflow.testutilities.AbstractEnhancedIterable;
import com.github.maumay.jflow.testutilities.AbstractIterableLongs;
import com.github.maumay.jflow.testutilities.IteratorExampleProvider;
import com.github.maumay.jflow.testutilities.IteratorTest;
import com.github.maumay.jflow.utils.LongTup;

/**
 * @author ThomasB
 *
 */
class AbstractLongIteratorZipTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testZipWithLong()
	{
		AbstractIterableLongs small = getSmallLongTestIteratorProvider();
		AbstractIterableLongs mid = getLongTestIteratorProvider();
		AbstractIterableLongs large = getLargeLongTestIteratorProvider();
		AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();

		assertObjectIteratorAsExpected(
				asList(LongTup.of(0, 0), LongTup.of(1, 1), LongTup.of(2, 2),
						LongTup.of(3, 3), LongTup.of(4, 4)),
				createZipIteratorProviderFrom(mid, mid));

		assertObjectIteratorAsExpected(
				asList(LongTup.of(0, 10), LongTup.of(1, 11), LongTup.of(2, 12),
						LongTup.of(3, 13), LongTup.of(4, 14)),
				createZipIteratorProviderFrom(mid, large));

		assertObjectIteratorAsExpected(asList(LongTup.of(0, 10), LongTup.of(1, 11)),
				createZipIteratorProviderFrom(mid, small));

		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(mid, empty));
		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(empty, empty));
		assertObjectIteratorAsExpected(asList(),
				createZipIteratorProviderFrom(empty, mid));
	}

	private AbstractEnhancedIterable<LongTup> createZipIteratorProviderFrom(
			AbstractIterableLongs first, AbstractIterableLongs second)
	{
		return new AbstractEnhancedIterable<LongTup>() {
			@Override
			public AbstractEnhancedIterator<LongTup> iter()
			{
				return first.iter().zipWith(second.iter());
			}
		};
	}

	// @Test
	// void testZipWithObject()
	// {
	// AbstractIterableLongs populatedLongs = getLongTestIteratorProvider();
	// AbstractIterableLongs emptyLongs = getEmptyLongTestIteratorProvider();
	//
	// AbstractEnhancedIterable<String> smallObjects =
	// getSmallObjectTestIteratorProvider();
	// AbstractEnhancedIterable<String> midObjects =
	// getObjectTestIteratorProvider();
	// AbstractEnhancedIterable<String> largeObjects =
	// getLargeObjectTestIteratorProvider();
	// AbstractEnhancedIterable<String> emptyObjects =
	// getEmptyObjectTestIteratorProvider();
	//
	// assertObjectIteratorAsExpected(asList(LongWith.of(0, "10"), LongWith.of(1,
	// "11")),
	// createZipIteratorProviderFrom(populatedLongs, smallObjects));
	//
	// assertObjectIteratorAsExpected(
	// asList(LongWith.of(0, "0"), LongWith.of(1, "1"), LongWith.of(2, "2"),
	// LongWith.of(3, "3"), LongWith.of(4, "4")),
	// createZipIteratorProviderFrom(populatedLongs, midObjects));
	//
	// assertObjectIteratorAsExpected(
	// asList(LongWith.of(0, "10"), LongWith.of(1, "11"), LongWith.of(2, "12"),
	// LongWith.of(3, "13"), LongWith.of(4, "14")),
	// createZipIteratorProviderFrom(populatedLongs, largeObjects));
	//
	// assertObjectIteratorAsExpected(asList(),
	// createZipIteratorProviderFrom(emptyLongs, emptyObjects));
	// assertObjectIteratorAsExpected(asList(),
	// createZipIteratorProviderFrom(emptyLongs, smallObjects));
	// }
	//
	// private <E> AbstractEnhancedIterable<LongWith<E>>
	// createZipIteratorProviderFrom(
	// AbstractIterableLongs first, AbstractEnhancedIterable<E> second)
	// {
	// return new AbstractEnhancedIterable<LongWith<E>>() {
	// @Override
	// public AbstractEnhancedIterator<LongWith<E>> iter()
	// {
	// return first.iter().zipWith(second.iter());
	// }
	// };
	// }
}
