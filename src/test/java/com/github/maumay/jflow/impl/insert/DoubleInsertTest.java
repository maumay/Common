/**
 *
 */
package com.github.maumay.jflow.impl.insert;

import com.github.maumay.jflow.impl.AbstractDoubleIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

import java.util.Iterator;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * @author thomasb
 *
 */
public final class DoubleInsertTest extends
        AbstractDoubleAdapterTest<AbstractDoubleIterator>
{
    @Override
    protected List<Case<AbstractDoubleIterator>> getTestCases()
    {
        List<Double> empty = list(), populated = list(0.0);
        return list(new Case<>(empty, i -> i.rchain(unbox(empty.iterator())),
                        empty),
                new Case<>(empty, i -> i.rchain(unbox(populated.iterator())),
                        populated),
                new Case<>(populated, i -> i.rchain(unbox(empty.iterator())),
                        populated),
                new Case<>(populated,
                        i -> i.rchain(unbox(list(1.0).iterator())),
                        list(1.0, 0.0)));
    }

    private PrimitiveIterator.OfDouble unbox(Iterator<Double> source)
    {
        return new PrimitiveIterator.OfDouble()
        {
            @Override
            public boolean hasNext()
            {
                return source.hasNext();
            }

            @Override
            public double nextDouble()
            {
                return source.next();
            }
        };
    }
}
