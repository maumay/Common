/**
 *
 */
package com.github.maumay.jflow.iterable;

import com.github.maumay.jflow.iterator.DoubleIterator;
import com.github.maumay.jflow.iterator.RichIterator;

import java.util.function.DoubleConsumer;

/**
 * Abstraction of iterable object which can construct enhanced primitive double
 * iterators ({@link DoubleIterator}).
 *
 * @author t
 */
@FunctionalInterface
public interface DoubleIterable extends Iterable<Double>
{
    /**
     * Constructs an enhanced double iterator traversing the elements encapsulated
     * by this object.
     *
     * @return an enhanced iterator traversing the encapsulated elements.
     */
    DoubleIterator iter();

    @Override
    default RichIterator<Double> iterator()
    {
        return iter().boxed();
    }

    /**
     * Consumes this iterator by applying some action to each element in turn.
     *
     * @param action the action to apply to each element in this iterator.
     */
    default void forEach(DoubleConsumer action)
    {
        iter().forEach(action);
    }
}
