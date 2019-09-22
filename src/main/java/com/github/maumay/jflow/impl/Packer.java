package com.github.maumay.jflow.impl;

import com.github.maumay.jflow.iterator.DoubleIterator;
import com.github.maumay.jflow.iterator.collector.DoubleIteratorCollector;
import com.github.maumay.jflow.iterator.collector.IteratorCollector;
import com.github.maumay.jflow.utils.Exceptions;
import com.github.maumay.jflow.vec.DoubleVec;
import com.github.maumay.jflow.vec.Vec;

import java.util.*;

/**
 * @author thomasb
 */
public class Packer
{
    public enum Type
    {
        INCLUDE_REMAINDER, EXCLUDE_REMAINDER;
    }

    public static class OfObject<E> implements IteratorCollector<E, Vec<Vec<E>>>
    {
        private final int packSize;
        private final Type type;

        public OfObject(int packSize, Type type)
        {
            if (packSize <= 0) {
                throw new IllegalArgumentException();
            }
            this.packSize = packSize;
            this.type = Objects.requireNonNull(type);
        }

        @SuppressWarnings("unchecked")
        @Override
        public Vec<Vec<E>> collect(Iterator<? extends E> source)
        {
            List<Vec<E>> dest = new ArrayList<>();
            Object[] curr = new Object[packSize];
            int count = 0;
            while (source.hasNext()) {
                curr[count++] = source.next();
                if (count == packSize) {
                    dest.add(Vec.<E>of((E[]) curr));
                    curr = new Object[packSize];
                    count = 0;
                }
            }
            if (type == Type.INCLUDE_REMAINDER && count > 0) {
                dest.add(Vec.<E>of((E[]) Arrays.copyOf(curr, count)));
            }
            return Vec.copy(dest);
        }
    }

    public static class OfDouble implements
            DoubleIteratorCollector<Vec<DoubleVec>>
    {
        private final int packSize;
        private final Type type;

        public OfDouble(int packSize, Type type)
        {
            Exceptions.require(packSize > 0);
            this.packSize = packSize;
            this.type = Objects.requireNonNull(type);
        }

        @Override
        public Vec<DoubleVec> collect(DoubleIterator source)
        {
            List<DoubleVec> dest = new ArrayList<>();
            double[] curr = new double[packSize];
            int count = 0;
            while (source.hasNext()) {
                curr[count++] = source.nextDouble();
                if (count == packSize) {
                    dest.add(DoubleVec.of(curr));
                    curr = new double[packSize];
                    count = 0;
                }
            }
            if (type == Type.INCLUDE_REMAINDER && count > 0) {
                dest.add(DoubleVec.of(Arrays.copyOf(curr, count)));
            }
            return Vec.copy(dest);
        }
    }

}
