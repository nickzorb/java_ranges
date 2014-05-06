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

package gr.hua.utils.range.numeric;

import gr.hua.utils.range.RangeIterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author NickZorb
 */
public class GenericRealRangeTest {
    
    private static GenericRealRange instance = new GenericRealRange(true, true, 1.0, 40.0, 1.0);
    
    public GenericRealRangeTest() {
    }

    /**
     * Test of clone method, of class GenericRealRange.
     */
    @Test
    public void testClone() {
        instance = new GenericRealRange(true, true, 1.0, 40.0, 1.0);
        GenericRealRange expResult = new GenericRealRange(true, true, 1.0, 40.0, 1.0);
        GenericNumericRange<Double> result = instance.clone();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of times method, of class GenericRealRange.
     */
    @Test
    public void testTimes() {
        instance = new GenericRealRange(true, true, 1.0, 40.0, 1.0);
        Double mul = -3.0;
        boolean increaseStep = true;
        instance.times(mul, increaseStep);
        GenericRealRange expResult = new GenericRealRange(true, true, -120.0, -3.0, 3.0);
        assertEquals(expResult.toString(), instance.toString());
    }

    /**
     * Test of divide method, of class GenericRealRange.
     */
    @Test
    public void testDivide() {
        instance = new GenericRealRange(true, true, 3.0, 45.0, 3.0);
        Double div = -3.0;
        boolean decreaseStep = true;
        instance.divide(div, decreaseStep);
        GenericRealRange expResult = new GenericRealRange(true, true, -15.0, -1.0, 1.0);
        assertEquals(expResult.toString(), instance.toString());
    }

    /**
     * Test of setStart method, of class GenericRealRange.
     */
    @Test
    public void testSetStart() {
        instance = new GenericRealRange(true, true, 1.0, 40.0, 1.0);
        Double start = 2.0;
        boolean included = false;
        instance.setStart(start, included);
        GenericRealRange expResult = new GenericRealRange(false, true, 2.0, 40.0, 1.0);
        assertEquals(expResult.toString(), instance.toString());
    }

    /**
     * Test of setEnd method, of class GenericRealRange.
     */
    @Test
    public void testSetEnd() {
        instance = new GenericRealRange(true, true, 1.0, 40.0, 1.0);
        Double end = 15.0;
        boolean included = false;
        instance.setEnd(end, included);
        GenericRealRange expResult = new GenericRealRange(true, false, 1.0, 15.0, 1.0);
        assertEquals(expResult.toString(), instance.toString());
    }

    /**
     * Test of transfer method, of class GenericRealRange.
     */
    @Test
    public void testTransfer() {
        instance = new GenericRealRange(true, true, 1.0, 40.0, 1.0);
        Double dgr = 4.0;
        instance.transfer(dgr);
        GenericRealRange expResult = new GenericRealRange(true, true, 5.0, 44.0, 1.0);
        assertEquals(expResult.toString(), instance.toString());
    }

    /**
     * Test of empty method, of class GenericRealRange.
     */
    @Test
    public void testEmpty() {
        instance = new GenericRealRange(true, true, 1.0, 40.0, 1.0);
        boolean expResult = false;
        boolean result = instance.empty();
        assertEquals(expResult, result);
        instance = new GenericRealRange(false, false, 1.0, 2.0, 1.0);
        expResult = true;
        result = instance.empty();
        assertEquals(expResult, result);
        instance = new GenericRealRange(false, true, 1.0, 1.0, 1.0);
        expResult = true;
        result = instance.empty();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class GenericRealRange.
     */
    @Test
    public void testContains() {
        instance = new GenericRealRange(true, true, 1.0, 40.0, 1.0);
        Double item = 1.0;
        boolean expResult = true;
        boolean result = instance.contains(item);
        assertEquals(expResult, result);
    }

    /**
     * Test of iterate method, of class GenericRealRange.
     */
    @Test
    public void testIterate() {
        instance = new GenericRealRange(true, true, 1.0, 40.0, 1.0);
        RangeIterator<Double> result = instance.iterate();
        for (double i = 1; i <= 40; i++) {
            assertEquals(i, (double) result.next(), 0.0000000000001);
        }
        for (double i = 39; i > 0; i--) {
            assertEquals(i + 1, (double) result.current(), 0.0000000000001);
            assertEquals(i, (double) result.previous(), 0.0000000000001);
        }
    }

    /**
     * Test of estimateSize method, of class GenericRealRange.
     */
    @Test
    public void testEstimateSize() {
        instance = new GenericRealRange(true, true, 1.0, 40.0, 10.5);
        int expResult = 4;
        int result = instance.estimateSize();
        assertEquals(expResult, result);
    }
    
}
