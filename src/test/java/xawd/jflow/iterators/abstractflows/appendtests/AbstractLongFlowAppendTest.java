/**
 *
 */
package xawd.jflow.iterators.abstractflows.appendtests;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractLongFlow;
import xawd.jflow.iterators.abstractiterables.AbstractIterableLongs;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;
import xawd.jflow.iterators.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractLongFlowAppendTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void test()
	{
		final AbstractIterableLongs populated = getLongTestIteratorProvider();
		final AbstractIterableLongs empty = getEmptyLongTestIteratorProvider();
		final AbstractIterableLongs small = getSmallLongTestIteratorProvider();

		assertLongIteratorAsExpected(new long[] {0, 1, 2, 3, 4, 10, 11}, createAppendIteratorProviderFrom(populated, small));
		assertLongIteratorAsExpected(new long[] {0, 1, 2, 3, 4}, createAppendIteratorProviderFrom(populated, empty));

		assertLongIteratorAsExpected(new long[] {10, 11}, createAppendIteratorProviderFrom(empty, small));
		assertLongIteratorAsExpected(new long[0], createAppendIteratorProviderFrom(empty, empty));
	}

	private AbstractIterableLongs createAppendIteratorProviderFrom(final AbstractIterableLongs source, final AbstractIterableLongs toAppend)
	{
		return new AbstractIterableLongs() {
			@Override
			public AbstractLongFlow iter() {
				return source.iter().append(toAppend.iter());
			}
		};
	}
}
