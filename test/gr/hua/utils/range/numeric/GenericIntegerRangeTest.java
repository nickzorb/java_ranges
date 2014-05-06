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
public class GenericIntegerRangeTest {
    
    private static GenericIntegerRange instance = new GenericIntegerRange(true, true, 1, 40, 1);
    
    public GenericIntegerRangeTest() {
    }

    /**
     * Test of clone method, of class GenericIntegerRange.
     */
    @Test
    public void testClone() {
        instance = new GenericIntegerRange(true, true, 1, 40, 1);
        GenericNumericRange<Integer> expResult = new GenericIntegerRange(true, true, 1, 40, 1);
        GenericNumericRange<Integer> result = instance.clone();
        assertEquals(expResult.toString(), result.toString());
    }

    /**
     * Test of times method, of class GenericIntegerRange.
     */
    @Test
    public void testTimes() {
        instance = new GenericIntegerRange(true, true, 1, 40, 1);
        Integer mul = -3;
        boolean increaseStep = true;
        instance.times(mul, increaseStep);
        GenericNumericRange<Integer> expResult = new GenericIntegerRange(true, true, -120, -3, 3);
        assertEquals(expResult.toString(), instance.toString());
    }

    /**
     * Test of divide method, of class GenericIntegerRange.
     */
    @Test
    public void testDivide() {
        instance = new GenericIntegerRange(true, true, 1, 40, 1);
        Integer div = -3;
        boolean decreaseStep = true;
        instance.divide(div, decreaseStep);
        GenericNumericRange<Integer> expResult = new GenericIntegerRange(true, true, -13, 0, 1);
        assertEquals(expResult.toString(), instance.toString());
    }

    /**
     * Test of setStart method, of class GenericIntegerRange.
     */
    @Test
    public void testSetStart() {
        instance = new GenericIntegerRange(true, true, 1, 40, 1);
        Integer start = 2;
        boolean included = false;
        instance.setStart(start, included);
        GenericNumericRange<Integer> expResult = new GenericIntegerRange(false, true, 2, 40, 1);
        assertEquals(expResult.toString(), instance.toString());
    }

    /**
     * Test of setEnd method, of class GenericIntegerRange.
     */
    @Test
    public void testSetEnd() {
        instance = new GenericIntegerRange(true, true, 1, 40, 1);
        Integer end = 15;
        boolean included = false;
        instance.setEnd(end, included);
        GenericNumericRange<Integer> expResult = new GenericIntegerRange(true, false, 1, 15, 1);
        assertEquals(expResult.toString(), instance.toString());
    }

    /**
     * Test of transfer method, of class GenericIntegerRange.
     */
    @Test
    public void testTransfer() {
        instance = new GenericIntegerRange(true, true, 1, 40, 1);
        Integer dgr = 4;
        instance.transfer(dgr);
        GenericNumericRange<Integer> expResult = new GenericIntegerRange(true, true, 5, 44, 1);
        assertEquals(expResult.toString(), instance.toString());
    }

    /**
     * Test of empty method, of class GenericIntegerRange.
     */
    @Test
    public void testEmpty() {
        instance = new GenericIntegerRange(true, true, 1, 40, 1);
        boolean expResult = false;
        boolean result = instance.empty();
        assertEquals(expResult, result);
        instance = new GenericIntegerRange(false, false, 1, 2, 1);
        expResult = true;
        result = instance.empty();
        assertEquals(expResult, result);
        instance = new GenericIntegerRange(false, true, 1, 1, 1);
        expResult = true;
        result = instance.empty();
        assertEquals(expResult, result);
    }

    /**
     * Test of contains method, of class GenericIntegerRange.
     */
    @Test
    public void testContains() {
        instance = new GenericIntegerRange(true, true, 1, 40, 1);
        Integer item = 1;
        boolean expResult = true;
        boolean result = instance.contains(item);
        assertEquals(expResult, result);
    }

    /**
     * Test of iterate method, of class GenericIntegerRange.
     */
    @Test
    public void testIterate() {
        instance = new GenericIntegerRange(true, true, 1, 40, 1);
        RangeIterator<Integer> result = instance.iterate();
        for (int i = 1; i <= 40; i++) {
            assertEquals(i, (int) result.next());
        }
        for (int i = 39; i > 0; i--) {
            assertEquals(i + 1, (int) result.current());
            assertEquals(i, (int) result.previous());
        }
    }

    /**
     * Test of estimateSize method, of class GenericIntegerRange.
     */
    @Test
    public void testEstimateSize() {
        instance = new GenericIntegerRange(true, true, 1, 40, 1);
        int expResult = 40;
        int result = instance.estimateSize();
        assertEquals(expResult, result);
    }
    
}
