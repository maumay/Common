/**
 * 
 */
package xawd.jflow;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import xawd.jflow.iterators.SkippableIterator;
import xawd.jflow.zippedpairs.IntWith;

/**
 * @author t
 *
 */
public final class Flows 
{
	public static <T> ObjectFlow<T> from(final Iterable<T> src)
	{
		return new AbstractObjectFlow<T>() {
			@Override
			public SkippableIterator<T> iterator() {
				return skipifyIterator(src.iterator());
			}
		};
	}
	
	private static <T> SkippableIterator<T> skipifyIterator(final Iterator<T> iterator)
	{
		return new SkippableIterator<T>() {
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}
			@Override
			public T next() {
				return iterator.next();
			}
			@Override
			public void skip() {
					next();
			}
		};
	}
	
	private static <T> ObjectFlow<IntWith<T>> enumerate(final Iterable<T> src)
	{
		return from(src).enumerate();
	}
	
	public static void main(final String[] args)
	{
		final List<String> strings = Arrays.asList("hello", "world", "hopefully", "this", "will", "be", "good");
		final List<String> strings2 = Arrays.asList("today", "is", "a", "lovely", "day");
		
		System.out.println(from(strings).combineWith(strings2, (a, b) -> a + b).toImmutableList());
		
//		for (final IntWith<String> enumeratedString : enumerate(strings)) 
//		{
//			System.out.println(enumeratedString);
//		}
		
		System.out.println(from(strings).drop(2).append(strings2).toImmutableList());
		System.out.println(from(strings).insert(strings2).filter(s -> s.charAt(0) == 'h').toImmutableList());
	}
}
