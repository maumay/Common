/**
 *
 */
package com.github.maumay.jflow.test;

import com.github.maumay.jflow.impl.AbstractIterator;
import com.github.maumay.jflow.impl.AbstractRichIterator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;

/**
 * @author thomasb
 *
 */
public abstract class AbstractObjectAdapterTest<T, I extends AbstractIterator>
        extends AbstractAdapterTest implements FiniteIteratorTest
{
    protected abstract List<Case<T, I>> getTestCases();

    @Test
    public final void testIterationBehaviour()
    {
        for (Case<T, I> testcase : getTestCases()) {
            List<TestIterable<I>> providers = IteratorProvider
                    .buildIterables(testcase.source, testcase.adapter);

            assertIteratorAsExpected(testcase.result, providers);
        }
    }

    @Test
    public final void testOwnershipBehaviour()
    {
        for (Case<T, I> testcase : getTestCases()) {
            List<TestIterable<AbstractRichIterator<T>>> providers = IteratorProvider
                    .buildIterables(testcase.source);

            for (TestIterable<AbstractRichIterator<T>> provider : providers) {
                assertAdaptionRemovesOwnership(provider.iter(),
                        testcase.adapter);
            }
        }
    }

    @FunctionalInterface
    public static interface Adapter<T, I extends AbstractIterator>
            extends Function<AbstractRichIterator<T>, I>
    {
    }

    public static class Case<T, I extends AbstractIterator>
    {
        final List<T> source;
        final Adapter<T, I> adapter;
        final List<?> result;

        public Case(List<T> source, Adapter<T, I> adapter, List<?> result)
        {
            this.source = source;
            this.result = result;
            this.adapter = adapter;
        }
    }
}
