/**
 *
 */
package com.github.maumay.jflow.impl.caching;

import com.github.maumay.jflow.test.AbstractLongCollectionTest;
import com.github.maumay.jflow.vec.LongVec;

import java.util.List;

/**
 * @author thomasb
 *
 */
public final class LongToVecTest extends AbstractLongCollectionTest<LongVec>
{
    @Override
    protected Collector<LongVec> getCollectorToTest()
    {
        return iter -> iter.toVec();
    }

    @Override
    protected List<Case<LongVec>> getTestCases()
    {
        return list(new Case<>(list(), LongVec.empty()),
                new Case<>(list(0L, 1L, 2L, 4L, 2L),
                        LongVec.of(0L, 1L, 2L, 4L, 2L)));
    }

    @Override
    protected List<FailCase> getFailureCases()
    {
        return list();
    }
}
