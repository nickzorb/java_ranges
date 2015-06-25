/*
 * Copyright 2015 Nikolaos Zormpas <nickzorb@gmail.com>.
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
package gr.hua.utils.range.numeric;

import gr.hua.utils.range.RangeFactory;
import gr.hua.utils.range.RangeIterator;
import java.text.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Nikolaos Zormpas <nickzorb@gmail.com>
 */
public class GenericLongRangeTest {

    public GenericLongRangeTest() {
    }

    /**
     * Test of clone method, of class GenericLongRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testClone() throws ParseException {
        System.out.println("clone");
        String s = "[0/15]/1/3";
        GenericLongRange instance = (GenericLongRange) RangeFactory.parseString(s);
        GenericNumericRange<Long> result = instance.clone();
        assertEquals(instance, result);
    }

    /**
     * Test of times method, of class GenericLongRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testTimes() throws ParseException {
        System.out.println("times");
        String s1 = "[0/16)/4/3";
        String s2 = "(-8/0]/2/3";
        GenericLongRange instance = (GenericLongRange) RangeFactory.parseString(s1);
        NumericRange<Long> expResult = (GenericLongRange) RangeFactory.parseString(s2);
        Double mul = -0.5D;
        boolean alterStep = true;
        NumericRange<Long> result = instance.times(mul, alterStep);
        assertEquals(expResult, result);
    }

    /**
     * Test of setStart method, of class GenericLongRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSetStart() throws ParseException {
        System.out.println("setStart");
        String s1 = "[0/16)/4/3";
        String s2 = "(4/16)/4/3";
        GenericLongRange instance = (GenericLongRange) RangeFactory.parseString(s1);
        NumericRange<Long> expResult = (GenericLongRange) RangeFactory.parseString(s2);
        Long start = 4L;
        boolean included = false;
        NumericRange<Long> result = instance.setStart(start, included);
        assertEquals(expResult, result);
    }

    /**
     * Test of setEnd method, of class GenericLongRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSetEnd() throws ParseException {
        System.out.println("setEnd");
        String s1 = "[0/16)/4/3";
        String s2 = "[0/20]/4/3";
        GenericLongRange instance = (GenericLongRange) RangeFactory.parseString(s1);
        NumericRange<Long> expResult = (GenericLongRange) RangeFactory.parseString(s2);
        Long end = 20L;
        boolean included = true;
        NumericRange<Long> result = instance.setEnd(end, included);
        assertEquals(expResult, result);
    }

    /**
     * Test of empty method, of class GenericLongRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testEmpty() throws ParseException {
        System.out.println("empty");
        String s = "[0/15]/1/3";
        GenericLongRange instance = (GenericLongRange) RangeFactory.parseString(s);
        boolean expResult = false;
        boolean result = instance.empty();
        assertEquals(expResult, result);
        s = "(0/15)/15/3";
        instance = (GenericLongRange) RangeFactory.parseString(s);
        expResult = true;
        result = instance.empty();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class GenericLongRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testContains() throws ParseException {
        System.out.println("contains");
        String s = "[0/15]/2/3";
        GenericLongRange instance = (GenericLongRange) RangeFactory.parseString(s);
        Long item = 1L;
        boolean expResult = false;
        boolean result = instance.contains(item);
        assertEquals(expResult, result);
        item = 2L;
        expResult = true;
        result = instance.contains(item);
        assertEquals(expResult, result);
    }

    /**
     * Test of iterate method, of class GenericLongRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testIterate() throws ParseException {
        System.out.println("iterate");
        String s = "[0/15]/1/3";
        GenericLongRange instance = (GenericLongRange) RangeFactory.parseString(s);
        RangeIterator iter = instance.iterate();
        iter.speed(3L);
        for (long i = 0; i < 16; i += 3) {
            assertEquals(i, iter.next());
        }
    }

    /**
     * Test of size method, of class GenericLongRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSize() throws ParseException {
        System.out.println("size");
        String s = "[0/15]/1/3";
        GenericLongRange instance = (GenericLongRange) RangeFactory.parseString(s);
        long expResult = 16L;
        long result = instance.size();
        assertEquals(expResult, result);
    }
}
