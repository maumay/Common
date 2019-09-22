/**
 *
 */
package com.github.maumay.jflow.impl.map;

import com.github.maumay.jflow.impl.AbstractRichIterator;
import com.github.maumay.jflow.test.AbstractDoubleAdapterTest;

import java.util.List;

/**
 * @author t
 *
 */
public final class DoubleMapToObjectTest
        extends AbstractDoubleAdapterTest<AbstractRichIterator<String>>
{
    @Override
    protected List<Case<AbstractRichIterator<String>>> getTestCases()
    {
        Adapter<AbstractRichIterator<String>> adapter = iter -> iter
                .mapToObj(x -> "" + (int) x);
        return list(new Case<>(list(), adapter, list()), new Case<>(
                list(1.0, 2.0, 3.0, 4.0), adapter, list("1", "2", "3", "4")));
    }
}
