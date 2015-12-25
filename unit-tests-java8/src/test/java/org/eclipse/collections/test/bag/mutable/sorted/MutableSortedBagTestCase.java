/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.test.bag.mutable.sorted;

import org.eclipse.collections.api.bag.sorted.MutableSortedBag;
import org.eclipse.collections.impl.bag.sorted.mutable.TreeBag;
import org.eclipse.collections.impl.block.factory.Comparators;
import org.eclipse.collections.test.MutableOrderedIterableTestCase;
import org.eclipse.collections.test.collection.mutable.MutableCollectionTestCase;
import org.junit.Test;

import static org.eclipse.collections.test.IterableTestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public interface MutableSortedBagTestCase extends SortedBagTestCase, MutableCollectionTestCase, MutableOrderedIterableTestCase
{
    @Override
    <T> MutableSortedBag<T> newWith(T... elements);

    @Test
    default void MutableBag_addOccurrences()
    {
        MutableSortedBag<Integer> mutableSortedBag = this.newWith(3, 3, 3, 2, 2, 1);
        mutableSortedBag.addOccurrences(4, 4);
        assertEquals(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 4, 4, 4, 4, 3, 3, 3, 2, 2, 1), mutableSortedBag);
        mutableSortedBag.addOccurrences(1, 2);
        assertEquals(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 4, 4, 4, 4, 3, 3, 3, 2, 2, 1, 1, 1), mutableSortedBag);
        mutableSortedBag.addOccurrences(1, 0);
        assertEquals(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 4, 4, 4, 4, 3, 3, 3, 2, 2, 1, 1, 1), mutableSortedBag);
    }

    @Test(expected = IllegalArgumentException.class)
    default void MutableBag_addOccurrences_throws()
    {
        MutableSortedBag<Integer> mutableSortedBag = this.newWith(3, 3, 3, 2, 2, 1);
        mutableSortedBag.addOccurrences(4, -1);
    }

    @Test
    default void MutableBag_removeOccurrences()
    {
        MutableSortedBag<Integer> mutableBag = this.newWith(3, 3, 3, 2, 2, 1);
        assertFalse(mutableBag.removeOccurrences(4, 4));
        assertEquals(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3, 3, 3, 2, 2, 1), mutableBag);
        assertFalse(mutableBag.removeOccurrences(3, 0));
        assertEquals(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3, 3, 3, 2, 2, 1), mutableBag);
        assertTrue(mutableBag.removeOccurrences(1, 2));
        assertEquals(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3, 3, 3, 2, 2), mutableBag);
        assertTrue(mutableBag.removeOccurrences(3, 2));
        assertEquals(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3, 2, 2), mutableBag);
        assertTrue(mutableBag.removeOccurrences(2, 1));
        assertEquals(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3, 2), mutableBag);
        assertTrue(mutableBag.removeOccurrences(2, 2));
        assertEquals(TreeBag.newBagWith(Comparators.reverseNaturalOrder(), 3), mutableBag);
    }

    @Test(expected = IllegalArgumentException.class)
    default void MutableBag_removeOccurrences_throws()
    {
        MutableSortedBag<Integer> mutableBag = this.newWith(3, 3, 3, 2, 2, 1);
        assertFalse(mutableBag.removeOccurrences(4, -1));
    }
}