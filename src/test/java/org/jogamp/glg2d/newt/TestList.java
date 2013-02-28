package org.jogamp.glg2d.newt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

class TestList<T> extends ArrayList<T>
{
	private static final long serialVersionUID = -1577150517885488957L;
	private volatile int iterating = 0;

	private boolean violated = false;

	private void checkForModify()
	{
		if (iterating > 0 && !violated)
		{
			violated = true;

			throw new IllegalStateException("Modified where?");
		}
	}

	@Override
	public boolean add(T e)
	{
		checkForModify();

		return super.add(e);
	}

	@Override
	public void add(int index, T element)
	{
		checkForModify();

		super.add(index, element);
	}

	@Override
	public T remove(int index)
	{
		checkForModify();

		return super.remove(index);
	}

	@Override
	public boolean remove(Object o)
	{
		checkForModify();

		return super.remove(o);
	}

	@Override
	public boolean addAll(Collection<? extends T> c)
	{
		checkForModify();

		return super.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c)
	{
		checkForModify();

		return super.addAll(index, c);
	}

	@Override
	public void clear()
	{
		checkForModify();

		super.clear();
	}

	@Override
	public boolean removeAll(Collection<?> c)
	{
		checkForModify();

		return super.removeAll(c);
	}

	@Override
	protected void removeRange(int fromIndex, int toIndex)
	{
		checkForModify();

		super.removeRange(fromIndex, toIndex);
	}

	@Override
	public T set(int index, T element)
	{
		checkForModify();

		return super.set(index, element);
	}

	@Override
	public Iterator<T> iterator()
	{
		System.err.println(Thread.currentThread());

		iterating = iterating + size();

		return new Iterator<T>()
		{
			private Iterator<T> temp = TestList.super.iterator();

			@Override
			public boolean hasNext()
			{
				return temp.hasNext();
			}

			@Override
			public T next()
			{
				iterating = iterating - 1;

				return temp.next();
			}

			@Override
			public void remove()
			{
				temp.remove();
			}
		};
	}
}
