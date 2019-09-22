/**
 *
 */
package com.github.maumay.jflow.impl.minmax;

import com.github.maumay.jflow.test.AbstractLongCollectionTest;
import com.github.maumay.jflow.utils.Option;

import java.util.List;
import java.util.OptionalLong;

/**
 * @author thomasb
 *
 */
public final class LongMinOptionTest extends
        AbstractLongCollectionTest<OptionalLong>
{
    @Override
    protected Collector<OptionalLong> getCollectorToTest()
    {
        return iter -> iter.minOp();
    }

    @Override
    protected List<Case<OptionalLong>> getTestCases()
    {
        return list(new Case<>(list(), Option.emptyLong()),
                new Case<>(list(1L), Option.of(1L)),
                new Case<>(list(9L, 1L, 3L, 2L), Option.of(1L)));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
