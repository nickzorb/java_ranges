/*
 * Copyright 2014 Nick Zorbas.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package gr.hua.utils.range.discrete;

import gr.hua.utils.range.RangeIterator;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author NickZorb
 */
public class GenericDiscreteRangeTest {
    
    private static final List<String> TEST_ARRAY_1 = Arrays.asList("1", "2", "3", "4", "3", "5");
    private static final List<String> TEST_ARRAY_2 = Arrays.asList("another", "test", "string");
    private static final List<String> TEST_ARRAY_3 = Arrays.asList("");
    private static final List<String> TEST_ARRAY_4 = Arrays.asList();
    
    public GenericDiscreteRangeTest() {
    }

    /**
     * Test of remove method, of class GenericDiscreteRange.
     */
    @Test
    public void testRemove() {
        String item = "4";
        GenericDiscreteRange<String> instance = new GenericDiscreteRange();
        instance.append(TEST_ARRAY_1);
        instance.remove(item);
        assertEquals("{\"1\", \"2\", \"3\", \"3\", \"5\"}",instance.toString());
    }

    /**
     * Test of append method, of class GenericDiscreteRange.
     */
    @Test
    public void testAppend() {
        Object item = "2";
        GenericDiscreteRange instance = new GenericDiscreteRange();
        instance.append(TEST_ARRAY_1);
        instance.append(item);
        assertEquals("{\"1\", \"2\", \"3\", \"4\", \"3\", \"5\", \"2\"}",instance.toString());
    }

    /**
     * Test of empty method, of class GenericDiscreteRange.
     */
    @Test
    public void testEmpty() {
        GenericDiscreteRange instance = new GenericDiscreteRange();
        assertEquals(true, instance.empty());
        instance.append(TEST_ARRAY_4);
        assertEquals(true, instance.empty());
        instance.append(TEST_ARRAY_3);
        assertEquals(false, instance.empty());
    }

    /**
     * Test of contains method, of class GenericDiscreteRange.
     */
    @Test
    public void testContains() {
        Object item = "4";
        GenericDiscreteRange instance = new GenericDiscreteRange();
        instance.append(TEST_ARRAY_1);
        assertTrue(instance.contains(item));
        assertTrue(!instance.contains("12"));
    }

    /**
     * Test of iterate method, of class GenericDiscreteRange.
     */
    @Test
    public void testIterate() {
        GenericDiscreteRange<String> instance = new GenericDiscreteRange();
        instance.append(TEST_ARRAY_1);
        RangeIterator<String> iter = instance.iterate();
        int i = 0;
        while (iter.hasNext()) {
            assertEquals(iter.next(), TEST_ARRAY_1.get(i));
            i++;
        }
        assertEquals(i, TEST_ARRAY_1.size());
    }

    /**
     * Test of clone method, of class GenericDiscreteRange.
     */
    @Test
    public void testClone() {
        GenericDiscreteRange instance = new GenericDiscreteRange();
        instance.append(TEST_ARRAY_1);
        GenericDiscreteRange clone = (GenericDiscreteRange) instance.clone();
        assertTrue(instance.equals(clone));
    }

    /**
     * Test of split method, of class GenericDiscreteRange.
     */
    @Test
    public void testSplit() {
        Object item = "3";
        GenericDiscreteRange instance = new GenericDiscreteRange();
        instance.append(TEST_ARRAY_1);
        instance.append("2");
        List<GenericDiscreteRange> ranges = instance.split(item);
        assertEquals(ranges.size(), 3);
        assertEquals("{\"1\", \"2\"}",ranges.get(0).toString());
        assertEquals("{\"4\"}",ranges.get(1).toString());
        assertEquals("{\"5\", \"2\"}",ranges.get(2).toString());
        item = "2";
        ranges = instance.split(item);
        assertEquals(ranges.size(), 2);
        assertEquals("{\"1\"}",ranges.get(0).toString());
        assertEquals("{\"3\", \"4\", \"3\", \"5\"}",ranges.get(1).toString());
    }

    /**
     * Test of toString method, of class GenericDiscreteRange.
     */
    @Test
    public void testToString() {
        GenericDiscreteRange instance = new GenericDiscreteRange();
        instance.append(TEST_ARRAY_1);
        assertEquals("{\"1\", \"2\", \"3\", \"4\", \"3\", \"5\"}",instance.toString());
    }

    /**
     * Test of itemSet method, of class GenericDiscreteRange.
     */
    @Test
    public void testItemSet() {
        GenericDiscreteRange instance = new GenericDiscreteRange();
        instance.append(TEST_ARRAY_1);
        HashSet expResult = new HashSet(TEST_ARRAY_1);
        HashSet result = (HashSet)instance.itemSet();
        assertEquals(expResult, result);
    }

    /**
     * Test of estimateSize method, of class GenericDiscreteRange.
     */
    @Test
    public void testEstimateSize() {
        GenericDiscreteRange instance = new GenericDiscreteRange();
        instance.append(TEST_ARRAY_1);
        int expResult = 6;
        int result = instance.estimateSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class GenericDiscreteRange.
     */
    @Test
    public void testEquals() {
        GenericDiscreteRange target = new GenericDiscreteRange();
        GenericDiscreteRange instance = new GenericDiscreteRange();
        target.append(TEST_ARRAY_1);
        instance.append(TEST_ARRAY_2);
        assertTrue(!instance.equals(target));
        target = new GenericDiscreteRange();
        instance = new GenericDiscreteRange();
        target.append(TEST_ARRAY_1);
        instance.append(TEST_ARRAY_1);
        assertTrue(instance.equals(target));
    }
    
}
