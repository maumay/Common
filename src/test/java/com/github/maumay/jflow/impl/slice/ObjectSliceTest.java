/**
 *
 */
package com.github.maumay.jflow.impl.slice;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractObjectAdapterTest;

import java.util.List;

import static java.lang.Math.max;

/**
 * @author thomasb
 *
 */
public final class ObjectSliceTest
        extends AbstractObjectAdapterTest<Double, AbstractRichIterator<Double>>
{
    @Override
    protected List<Case<Double, AbstractRichIterator<Double>>> getTestCases()
    {
        List<Double> src = list(0.0, 1.0, 2.0, 3.0, 4.0, 5.0);
        return list(new Case<>(list(), i -> i.slice(n -> n), list()),
                new Case<>(src, i -> i.slice(n -> n), src),
                new Case<>(src, i -> i.slice(n -> n + 1),
                        list(1.0, 2.0, 3.0, 4.0, 5.0)),
                new Case<>(src, i -> i.slice(n -> 2 * n), list(0.0, 2.0, 4.0)),
                new Case<>(src, i -> i.slice(n -> max(0, 3 * n - 1)),
                        list(0.0, 2.0, 5.0)));
    }
}
