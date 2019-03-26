/**
 *
 */
package com.github.maumay.jflow.iterators;

import java.util.OptionalInt;
import java.util.PrimitiveIterator;
import java.util.function.IntConsumer;

/**
 * A precursor interface to {@linkplain IntIterator}.
 *
 * @author t
 */
public interface SafeIntIterator extends PrimitiveIterator.OfInt, Skippable// , OptionallySized
{
	/**
	 * A safe alternative to directly calling {@link #nextInt()} method.
	 *
	 * @return An OptionalInt wrapping the next element if there is one.
	 */
	OptionalInt nextIntOp();

	/**
	 * Perform the supplied action for each element left in this iterator sequence,
	 * in doing so the iterator is consumed.
	 *
	 * @param action The action to perform.
	 */
	void forEach(IntConsumer action);

	@Override
	@Deprecated
	default Integer next()
	{
		throw new UnsupportedOperationException("Boxing using this method is banned for Flows!!");
	}
}
