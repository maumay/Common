/**
 *
 */
package com.github.maumay.jflow.impl.minmax;

import com.github.maumay.jflow.test.AbstractObjectCollectionTest;
import com.github.maumay.jflow.utils.Option;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author thomasb
 *
 */
public final class ObjectMinOptionTest
        extends AbstractObjectCollectionTest<Double, Optional<Double>>
{
    @Override
    protected Collector<Double, Optional<Double>> getCollectorToTest()
    {
        return iter -> iter.minOp(Comparator.naturalOrder());
    }

    @Override
    protected List<Case<Double, Optional<Double>>> getTestCases()
    {
        return list(new Case<>(list(), Option.empty()),
                new Case<>(list(1.0), Optional.of(1.0)),
                new Case<>(list(9.0, 1.0, 3.0, 2.0), Optional.of(1.0)));
    }

    @Override
    protected List<FailCase<Double>> getFailureCases()
    {
        return list();
    }
}
