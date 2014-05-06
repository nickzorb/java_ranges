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
    
    public GenericRealRangeTest() {
    }

    /**
     * Test of clone method, of class GenericRealRange.
     */
    @Test
    public void testClone() {
        System.out.println("clone");
        GenericRealRange instance = null;
        GenericNumericRange<Double> expResult = null;
        GenericNumericRange<Double> result = instance.clone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of times method, of class GenericRealRange.
     */
    @Test
    public void testTimes() {
        System.out.println("times");
        Double mul = null;
        boolean increaseStep = false;
        GenericRealRange instance = null;
        instance.times(mul, increaseStep);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStart method, of class GenericRealRange.
     */
    @Test
    public void testSetStart() {
        System.out.println("setStart");
        Double start = null;
        boolean included = false;
        GenericRealRange instance = null;
        instance.setStart(start, included);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEnd method, of class GenericRealRange.
     */
    @Test
    public void testSetEnd() {
        System.out.println("setEnd");
        Double end = null;
        boolean included = false;
        GenericRealRange instance = null;
        instance.setEnd(end, included);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of transfer method, of class GenericRealRange.
     */
    @Test
    public void testTransfer() {
        System.out.println("transfer");
        Double dgr = null;
        GenericRealRange instance = null;
        instance.transfer(dgr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of empty method, of class GenericRealRange.
     */
    @Test
    public void testEmpty() {
        System.out.println("empty");
        GenericRealRange instance = null;
        boolean expResult = false;
        boolean result = instance.empty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contains method, of class GenericRealRange.
     */
    @Test
    public void testContains() {
        System.out.println("contains");
        Double item = null;
        GenericRealRange instance = null;
        boolean expResult = false;
        boolean result = instance.contains(item);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iterate method, of class GenericRealRange.
     */
    @Test
    public void testIterate() {
        System.out.println("iterate");
        GenericRealRange instance = null;
        RangeIterator<Double> expResult = null;
        RangeIterator<Double> result = instance.iterate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of estimateSize method, of class GenericRealRange.
     */
    @Test
    public void testEstimateSize() {
        System.out.println("estimateSize");
        GenericRealRange instance = null;
        int expResult = 0;
        int result = instance.estimateSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of divide method, of class GenericRealRange.
     */
    @Test
    public void testDivide() {
        System.out.println("divide");
        Double div = null;
        boolean decreaseStep = false;
        GenericRealRange instance = null;
        instance.divide(div, decreaseStep);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
