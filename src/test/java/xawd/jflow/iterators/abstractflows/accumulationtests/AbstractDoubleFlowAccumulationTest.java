/**
 *
 */
package xawd.jflow.iterators.abstractflows.accumulationtests;

import java.util.function.DoubleBinaryOperator;

import org.junit.jupiter.api.Test;

import xawd.jflow.iterators.AbstractDoubleFlow;
import xawd.jflow.iterators.abstractiterables.AbstractIterableDoubles;
import xawd.jflow.iterators.testutilities.IteratorExampleProvider;
import xawd.jflow.iterators.testutilities.IteratorTest;

/**
 * @author ThomasB
 */
class AbstractDoubleFlowAccumulationTest extends IteratorExampleProvider implements IteratorTest
{
	@Test
	void testAccumulationWithoutId()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		assertDoubleIteratorAsExpected(new double[] {0, 1, 3, 6, 10}, createAccumulationWithoutIdIteratorProviderFrom(populated, (a, b) -> a + b));
		assertDoubleIteratorAsExpected(new double[] {}, createAccumulationWithoutIdIteratorProviderFrom(empty, (a, b) -> a + b));
	}

	private AbstractIterableDoubles createAccumulationWithoutIdIteratorProviderFrom(final AbstractIterableDoubles source, final DoubleBinaryOperator accumulator)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iter() {
				return source.iter().accumulate(accumulator);
			}
		};
	}

	@Test
	void testAccumulationWithId()
	{
		final AbstractIterableDoubles populated = getDoubleTestIteratorProvider();
		final AbstractIterableDoubles empty = getEmptyDoubleTestIteratorProvider();

		assertDoubleIteratorAsExpected(new double[] {5, 6, 8, 11, 15}, createAccumulationWithIdIteratorProviderFrom(populated, 5, (a, b) -> a + b));
		assertDoubleIteratorAsExpected(new double[] {}, createAccumulationWithIdIteratorProviderFrom(empty, 5, (a, b) -> a + b));
	}

	private AbstractIterableDoubles createAccumulationWithIdIteratorProviderFrom(final AbstractIterableDoubles source, final double id, final DoubleBinaryOperator accumulator)
	{
		return new AbstractIterableDoubles() {
			@Override
			public AbstractDoubleFlow iter() {
				return source.iter().accumulate(id, accumulator);
			}
		};
	}
}
