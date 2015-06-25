/*
 * Copyright 2015 Nikolaos Zormpas.
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
 * @author NickZorb
 */
public class GenericRealRangeTest {

    public GenericRealRangeTest() {
    }

    /**
     * Test of clone method, of class GenericRealRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testClone() throws ParseException {
        System.out.println("clone");
        String s = "[0.0/14.5]/1.5/3";
        GenericRealRange instance = (GenericRealRange) RangeFactory.parseString(s);
        GenericNumericRange<Double> result = instance.clone();
        assertEquals(instance, result);
    }

    /**
     * Test of times method, of class GenericRealRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testTimes() throws ParseException {
        System.out.println("times");
        String s1 = "[0.0/14.5)/1.5/3";
        String s2 = "(-14.5/0.0]/1.5/3";
        GenericRealRange instance = (GenericRealRange) RangeFactory.parseString(s1);
        NumericRange<Double> expResult = (GenericRealRange) RangeFactory.parseString(s2);
        Double mul = -1.0D;
        boolean alterStep = true;
        NumericRange<Double> result = instance.times(mul, alterStep);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of setStart method, of class GenericRealRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSetStart() throws ParseException {
        System.out.println("setStart");
        String s1 = "[0.0/16.1)/4.0/3";
        String s2 = "(4.2/16.1)/4.0/3";
        GenericRealRange instance = (GenericRealRange) RangeFactory.parseString(s1);
        NumericRange<Double> expResult = (GenericRealRange) RangeFactory.parseString(s2);
        Double start = 4.2D;
        boolean included = false;
        NumericRange<Double> result = instance.setStart(start, included);
        assertEquals(expResult, result);
    }

    /**
     * Test of setEnd method, of class GenericRealRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSetEnd() throws ParseException {
        System.out.println("setEnd");
        String s1 = "[0.0/16.1)/4.0/3";
        String s2 = "[0.0/20.4]/4.0/3";
        GenericRealRange instance = (GenericRealRange) RangeFactory.parseString(s1);
        NumericRange<Double> expResult = (GenericRealRange) RangeFactory.parseString(s2);
        Double end = 20.4D;
        boolean included = true;
        NumericRange<Double> result = instance.setEnd(end, included);
        assertEquals(expResult, result);
    }

    /**
     * Test of empty method, of class GenericRealRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testEmpty() throws ParseException {
        System.out.println("empty");
        String s = "[0.0/15.1]/15.1/3";
        GenericRealRange instance = (GenericRealRange) RangeFactory.parseString(s);
        boolean expResult = false;
        boolean result = instance.empty();
        assertEquals(expResult, result);
        s = "(0.0/15.1)/15.1/3";
        instance = (GenericRealRange) RangeFactory.parseString(s);
        expResult = true;
        result = instance.empty();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class GenericRealRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testContains() throws ParseException {
        System.out.println("contains");
        String s = "[0.0/15.0]/1.3/3";
        GenericRealRange instance = (GenericRealRange) RangeFactory.parseString(s);
        Double item = 1.5D;
        boolean expResult = false;
        boolean result = instance.contains(item);
        assertEquals(expResult, result);
        item = 2.6D;
        expResult = true;
        result = instance.contains(item);
        assertEquals(expResult, result);
    }

    /**
     * Test of iterate method, of class GenericRealRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testIterate() throws ParseException {
        System.out.println("iterate");
        String s = "[0.0/15.0]/1.1/3";
        GenericRealRange instance = (GenericRealRange) RangeFactory.parseString(s);
        RangeIterator<Double> iter = instance.iterate();
        iter.speed(3L);
        for (Double i = 0.0D; i <= 15D; i += 3.3D) {
            assertEquals(Math.abs(i - iter.next()) < 0.000001, true);
        }
    }

    /**
     * Test of size method, of class GenericRealRange.
     *
     * @throws java.text.ParseException
     */
    @Test
    public void testSize() throws ParseException {
        System.out.println("size");
        String s = "[0.0/15.1]/1.0/3";
        GenericRealRange instance = (GenericRealRange) RangeFactory.parseString(s);
        long expResult = 16L;
        long result = instance.size();
        assertEquals(expResult, result);
    }
}
