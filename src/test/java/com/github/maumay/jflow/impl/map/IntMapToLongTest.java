/**
 *
 */
package com.github.maumay.jflow.impl.map;

import com.github.maumay.jflow.impl.AbstractLongIterator;
import com.github.maumay.jflow.test.AbstractIntAdapterTest;

import java.util.List;

/**
 * @author t
 *
 */
public class IntMapToLongTest extends
        AbstractIntAdapterTest<AbstractLongIterator>
{
    @Override
    protected List<Case<AbstractLongIterator>> getTestCases()
    {
        Adapter<AbstractLongIterator> adapter = iter -> iter
                .mapToLong(x -> 2 * x);
        return list(new Case<>(list(), adapter, list()),
                new Case<>(list(1, 2, 3), adapter, list(2L, 4L, 6L)));
    }
}
