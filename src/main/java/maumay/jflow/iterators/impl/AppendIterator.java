/**
 *
 */
package maumay.jflow.iterators.impl;

import static maumay.jflow.iterators.impl.ImplUtils.getSize;

import java.util.Iterator;
import java.util.PrimitiveIterator;

import maumay.jflow.iterators.AbstractEnhancedDoubleIterator;
import maumay.jflow.iterators.AbstractEnhancedIntIterator;
import maumay.jflow.iterators.AbstractEnhancedIterator;
import maumay.jflow.iterators.AbstractEnhancedLongIterator;
import maumay.jflow.iterators.EnhancedDoubleIterator;
import maumay.jflow.iterators.EnhancedIntIterator;
import maumay.jflow.iterators.EnhancedIterator;
import maumay.jflow.iterators.EnhancedLongIterator;
import maumay.jflow.iterators.misc.Optionals;

/**
 * @author ThomasB
 *
 */
public final class AppendIterator
{
	private AppendIterator()
	{
	}

	public static class OfObject<E> extends AbstractEnhancedIterator<E>
	{
		private final EnhancedIterator<E> source;
		private final Iterator<? extends E> appended;

		public OfObject(final EnhancedIterator<E> source,
				final Iterator<? extends E> appended)
		{
			super(Optionals.add(source.size(), getSize(appended)));
			this.source = source;
			this.appended = appended;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext() || appended.hasNext();
		}

		@Override
		public E next()
		{
			return source.hasNext() ? source.next() : appended.next();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			} else {
				ImplUtils.skip(appended);
			}
		}
	}

	public static class OfDouble extends AbstractEnhancedDoubleIterator
	{
		private final EnhancedDoubleIterator source;
		private final PrimitiveIterator.OfDouble appended;

		public OfDouble(final EnhancedDoubleIterator source,
				final PrimitiveIterator.OfDouble appended)
		{
			super(Optionals.add(source.size(), getSize(appended)));
			this.source = source;
			this.appended = appended;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext() || appended.hasNext();
		}

		@Override
		public double nextDouble()
		{
			return source.hasNext() ? source.nextDouble() : appended.nextDouble();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			} else {
				ImplUtils.skip(appended);
			}
		}
	}

	public static class OfLong extends AbstractEnhancedLongIterator
	{
		private final EnhancedLongIterator source;
		private final PrimitiveIterator.OfLong appended;

		public OfLong(final EnhancedLongIterator source,
				final PrimitiveIterator.OfLong appended)
		{
			super(Optionals.add(source.size(), getSize(appended)));
			this.source = source;
			this.appended = appended;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext() || appended.hasNext();
		}

		@Override
		public long nextLong()
		{
			return source.hasNext() ? source.nextLong() : appended.nextLong();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			} else {
				ImplUtils.skip(appended);
			}
		}
	}

	public static class OfInt extends AbstractEnhancedIntIterator
	{
		private final EnhancedIntIterator source;
		private final PrimitiveIterator.OfInt appended;

		public OfInt(final EnhancedIntIterator source,
				final PrimitiveIterator.OfInt appended)
		{
			super(Optionals.add(source.size(), getSize(appended)));
			this.source = source;
			this.appended = appended;
		}

		@Override
		public boolean hasNext()
		{
			return source.hasNext() || appended.hasNext();
		}

		@Override
		public int nextInt()
		{
			return source.hasNext() ? source.nextInt() : appended.nextInt();
		}

		@Override
		public void skip()
		{
			if (source.hasNext()) {
				source.skip();
			} else {
				ImplUtils.skip(appended);
			}
		}
	}
}