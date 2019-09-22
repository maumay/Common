/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.Iterator;
import java.util.PrimitiveIterator;

/**
 * @author thomasb
 *
 */
public final class IteratorWrapper
{
    private IteratorWrapper()
    {
    }

    public static <E> AbstractRichIterator<? extends E> wrap(
            Iterator<? extends E> src)
    {
        if (src instanceof AbstractRichIterator<?>) {
            return (AbstractRichIterator<? extends E>) src;
        } else {
            return new OfObject<>(src);
        }
    }

    public static AbstractIntIterator wrap(PrimitiveIterator.OfInt src)
    {
        if (src instanceof AbstractIntIterator) {
            return (AbstractIntIterator) src;
        } else {
            return new OfInt(src);
        }
    }

    public static AbstractLongIterator wrap(PrimitiveIterator.OfLong src)
    {
        if (src instanceof AbstractLongIterator) {
            return (AbstractLongIterator) src;
        } else {
            return new OfLong(src);
        }
    }

    public static AbstractDoubleIterator wrap(PrimitiveIterator.OfDouble src)
    {
        if (src instanceof AbstractDoubleIterator) {
            return (AbstractDoubleIterator) src;
        } else {
            return new OfDouble(src);
        }
    }

    public static class OfObject<E> extends AbstractRichIterator<E>
    {
        private final Iterator<? extends E> source;

        public OfObject(Iterator<? extends E> source)
        {
            super(new LowerBound(0));
            this.source = source;
        }

        @Override
        public boolean hasNext()
        {
            return source.hasNext();
        }

        @Override
        public E nextImpl()
        {
            return source.next();
        }

        @Override
        public void forwardImpl()
        {
            source.next();
        }
    }

    private static class OfLong extends AbstractLongIterator
    {
        private final PrimitiveIterator.OfLong source;

        public OfLong(PrimitiveIterator.OfLong source)
        {
            super(new LowerBound(0));
            this.source = source;
        }

        @Override
        public boolean hasNext()
        {
            return source.hasNext();
        }

        @Override
        public long nextLongImpl()
        {
            return source.nextLong();
        }

        @Override
        public void forwardImpl()
        {
            source.nextLong();
        }
    }

    private static class OfInt extends AbstractIntIterator
    {
        private final PrimitiveIterator.OfInt source;

        public OfInt(PrimitiveIterator.OfInt source)
        {
            super(new LowerBound(0));
            this.source = source;
        }

        @Override
        public boolean hasNext()
        {
            return source.hasNext();
        }

        @Override
        public int nextIntImpl()
        {
            return source.nextInt();
        }

        @Override
        public void forwardImpl()
        {
            source.nextInt();
        }
    }

    private static class OfDouble extends AbstractDoubleIterator
    {
        private final PrimitiveIterator.OfDouble source;

        public OfDouble(PrimitiveIterator.OfDouble source)
        {
            super(new LowerBound(0));
            this.source = source;
        }

        @Override
        public boolean hasNext()
        {
            return source.hasNext();
        }

        @Override
        public double nextDoubleImpl()
        {
            return source.nextDouble();
        }

        @Override
        public void forwardImpl()
        {
            source.nextDouble();
        }
    }
}
