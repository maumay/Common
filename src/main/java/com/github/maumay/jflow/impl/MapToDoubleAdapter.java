/**
 *
 */
package com.github.maumay.jflow.impl;

import java.util.function.IntToDoubleFunction;
import java.util.function.LongToDoubleFunction;
import java.util.function.ToDoubleFunction;

/**
 * @author thomasb
 *
 */
public final class MapToDoubleAdapter
{
    private MapToDoubleAdapter()
    {
    }

    public static final class FromObject<E>
            extends AbstractIteratorAdapter.OfDouble<AbstractRichIterator<E>>
    {
        private final ToDoubleFunction<? super E> map;

        public FromObject(AbstractRichIterator<E> source,
                ToDoubleFunction<? super E> map)
        {
            super(source.getSize().copy(), source);
            this.map = map;
        }

        @Override
        public boolean hasNext()
        {
            return getSource().hasNext();
        }

        @Override
        public double nextDoubleImpl()
        {
            return map.applyAsDouble(getSource().nextImpl());
        }

        @Override
        public void forwardImpl()
        {
            getSource().forwardImpl();
        }
    }

    public static final class FromLong
            extends AbstractIteratorAdapter.OfDouble<AbstractLongIterator>
    {
        private final LongToDoubleFunction map;

        public FromLong(AbstractLongIterator source, LongToDoubleFunction map)
        {
            super(source.getSize().copy(), source);
            this.map = map;
        }

        @Override
        public boolean hasNext()
        {
            return getSource().hasNext();
        }

        @Override
        public double nextDoubleImpl()
        {
            return map.applyAsDouble(getSource().nextLongImpl());
        }

        @Override
        public void forwardImpl()
        {
            getSource().forwardImpl();
        }
    }

    public static final class FromInt extends
            AbstractIteratorAdapter.OfDouble<AbstractIntIterator>
    {
        private final IntToDoubleFunction map;

        public FromInt(AbstractIntIterator source, IntToDoubleFunction map)
        {
            super(source.getSize().copy(), source);
            this.map = map;
        }

        @Override
        public boolean hasNext()
        {
            return getSource().hasNext();
        }

        @Override
        public double nextDoubleImpl()
        {
            return map.applyAsDouble(getSource().nextIntImpl());
        }

        @Override
        public void forwardImpl()
        {
            getSource().forwardImpl();
        }
    }
}
