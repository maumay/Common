/**
 *
 */
package com.github.maumay.jflow.impl.predicates;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;

import java.util.List;

/**
 * @author thomasb
 *
 */
public class ObjectNoneMatchTest extends
        AbstractObjectCollectionTest<Double, Boolean>
{
    @Override
    protected Collector<Double, Boolean> getCollectorToTest()
    {
        return iter -> iter.none(x -> x > 1);
    }

    @Override
    protected List<Case<Double, Boolean>> getTestCases()
    {
        return list(new Case<>(list(), true),
                new Case<>(list(0.0, 2.0, 1.0), false),
                new Case<>(list(0.0, 1.0, 1.0), true));
    }

    @Override
    protected List<FailCase<Double>> getFailureCases()
    {
        return list();
    }
}
