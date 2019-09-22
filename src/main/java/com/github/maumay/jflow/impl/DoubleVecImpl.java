/**
 *
 */
package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.iterator.DoubleIterator;
import com.github.maumay.jflow.iterator.Iter;
import com.github.maumay.jflow.vec.DoubleVec;

import java.util.Arrays;
import java.util.stream.DoubleStream;

/**
 * @author ThomasB
 *
 */
final class DoubleVecImpl implements DoubleVec
{
    private static final DoubleVecImpl EMPTY = new DoubleVecImpl(new double[0]);

    private final double[] data;

    DoubleVecImpl(double[] src)
    {
        this.data = src;
    }

    @Override
    public DoubleIterator iter()
    {
        return new ArraySource.OfDouble(data);
    }

    @Override
    public DoubleStream stream()
    {
        return DoubleStream.of(data);
    }

    @Override
    public double get(int index)
    {
        return data[index];
    }

    @Override
    public int size()
    {
        return data.length;
    }

    public static DoubleVecImpl empty()
    {
        return EMPTY;
    }

    @Override
    public DoubleIterator iterRev()
    {
        return Iter.reverseDoubles(data);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof DoubleVec) {
            DoubleVec other = (DoubleVec) obj;
            return size() == other.size()
                    && Iter.until(size())
                    .all(i -> Double.compare(get(i), other.get(i)) == 0);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode()
    {
        return Arrays.hashCode(data);
    }

    @Override
    public String toString()
    {
        return Arrays.toString(data);
    }

    @Override
    public DoubleVecImpl sorted()
    {
        double[] cpy = Arrays.copyOf(data, data.length);
        Arrays.sort(cpy);
        return new DoubleVecImpl(cpy);
    }
}

/*
 * ---------------------------------------------------------------------* This
 * software is the confidential and proprietary information of Lhasa Limited
 * Granary Wharf House, 2 Canal Wharf, Leeds LS11 5PS --- No part of this
 * confidential information shall be disclosed and it shall be used only in
 * accordance with the terms of a written license agreement entered into by
 * holder of the information with LHASA Ltd.
 * ---------------------------------------------------------------------
 */