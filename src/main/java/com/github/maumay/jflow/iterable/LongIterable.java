/**
 *
 */
package com.github.maumay.jflow.iterable;

import com.github.maumay.jflow.iterator.LongIterator;
import com.github.maumay.jflow.iterator.RichIterator;

import java.util.function.LongConsumer;

/**
 * Abstraction of iterable object which can construct enhanced primitive long
 * iterators ({@link LongIterator}).
 *
 * @author t
 */
@FunctionalInterface
public interface LongIterable extends Iterable<Long>
{
    LongIterator iter();

    @Override
    default RichIterator<Long> iterator()
    {
        return iter().boxed();
    }

    default void forEach(final LongConsumer action)
    {
        iter().forEachRemaining(action);
    }
}
