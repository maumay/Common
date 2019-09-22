/**
 *
 */
package com.github.maumay.jflow.impl.minmax;

import com.github.maumay.jflow.test.AbstractIntCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class IntMinTest extends AbstractIntCollectionTest<Integer>
{
    @Override
    protected Collector<Integer> getCollectorToTest()
    {
        return iter -> iter.min();
    }

    @Override
    protected List<Case<Integer>> getTestCases()
    {
        return list(new Case<>(list(1), 1), new Case<>(list(9, 1, 3, 2), 1));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list(new FailCase(list(), IllegalStateException.class));
    }
}
