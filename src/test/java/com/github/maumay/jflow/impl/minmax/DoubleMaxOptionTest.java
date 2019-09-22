/**
 *
 */
package com.github.maumay.jflow.impl.minmax;

import com.github.maumay.jflow.test.AbstractDoubleCollectionTest;
import com.github.maumay.jflow.utils.Option;

import java.util.List;
import java.util.OptionalDouble;

/**
 * @author thomasb
 *
 */
public final class DoubleMaxOptionTest extends
        AbstractDoubleCollectionTest<OptionalDouble>
{
    @Override
    protected Collector<OptionalDouble> getCollectorToTest()
    {
        return iter -> iter.maxOp();
    }

    @Override
    protected List<Case<OptionalDouble>> getTestCases()
    {
        return list(new Case<>(list(), Option.emptyDouble()),
                new Case<>(list(1.0), Option.of(1.0)),
                new Case<>(list(9.0, 1.0, 3.0, 2.0), Option.of(9.0)),
                new Case<>(list(1.0, 9.0, 3.0, 2.0), Option.of(9.0)),
                new Case<>(list(Double.NaN, 1.0, 9.0, -2.0), Option.of(9.0)),
                new Case<>(list(1.0, Double.NaN), Option.of(1.0)),
                new Case<>(list(Double.NaN), Option.emptyDouble()));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
