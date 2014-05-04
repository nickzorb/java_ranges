/*
 * Copyright 2014 NickZorb.
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

import gr.hua.utils.range.Range;
import gr.hua.utils.range.RangeIterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author NickZorb
 */
public class NumericRangeTest {
    
//    public NumericRangeTest() {
//    }
//
//    /**
//     * Test of parseRange method, of class NumericRange.
//     */
//    @Test
//    public void testParseRange() throws Exception {
//        System.out.println("parseRange");
//        String s = "";
//        NumericRange expResult = null;
//        NumericRange result = NumericRange.parseRange(s);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateRange method, of class NumericRange.
//     */
//    @Test
//    public void testGenerateRange_Integer_Integer() {
//        System.out.println("generateRange");
//        Integer start = null;
//        Integer end = null;
//        NumericRange<Integer> expResult = null;
//        NumericRange<Integer> result = NumericRange.generateRange(start, end);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateRange method, of class NumericRange.
//     */
//    @Test
//    public void testGenerateRange_3args_1() {
//        System.out.println("generateRange");
//        Integer start = null;
//        Integer end = null;
//        int code = 0;
//        NumericRange<Integer> expResult = null;
//        NumericRange<Integer> result = NumericRange.generateRange(start, end, code);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateRange method, of class NumericRange.
//     */
//    @Test
//    public void testGenerateRange_4args_1() {
//        System.out.println("generateRange");
//        Integer start = null;
//        Integer end = null;
//        Integer step = null;
//        int code = 0;
//        NumericRange<Integer> expResult = null;
//        NumericRange<Integer> result = NumericRange.generateRange(start, end, step, code);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateRange method, of class NumericRange.
//     */
//    @Test
//    public void testGenerateRange_Double_Double() {
//        System.out.println("generateRange");
//        Double start = null;
//        Double end = null;
//        NumericRange<Double> expResult = null;
//        NumericRange<Double> result = NumericRange.generateRange(start, end);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateRange method, of class NumericRange.
//     */
//    @Test
//    public void testGenerateRange_3args_2() {
//        System.out.println("generateRange");
//        Double start = null;
//        Double end = null;
//        int code = 0;
//        NumericRange<Double> expResult = null;
//        NumericRange<Double> result = NumericRange.generateRange(start, end, code);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateRange method, of class NumericRange.
//     */
//    @Test
//    public void testGenerateRange_4args_2() {
//        System.out.println("generateRange");
//        Double start = null;
//        Double end = null;
//        Double step = null;
//        int code = 0;
//        NumericRange<Double> expResult = null;
//        NumericRange<Double> result = NumericRange.generateRange(start, end, step, code);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of generateRange method, of class NumericRange.
//     */
//    @Test
//    public void testGenerateRange_4args_3() {
//        System.out.println("generateRange");
//        Number start = null;
//        Number end = null;
//        Number step = null;
//        int code = 0;
//        NumericRange<Double> expResult = null;
//        NumericRange<Double> result = NumericRange.generateRange(start, end, step, code);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of times method, of class NumericRange.
//     */
//    @Test
//    public void testTimes() {
//        System.out.println("times");
//        Number mul = null;
//        boolean increaseStep = false;
//        NumericRange instance = new NumericRangeImpl();
//        instance.times(mul, increaseStep);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setStart method, of class NumericRange.
//     */
//    @Test
//    public void testSetStart_GenericType() {
//        System.out.println("setStart");
//        Number start = null;
//        NumericRange instance = new NumericRangeImpl();
//        instance.setStart(start);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setStart method, of class NumericRange.
//     */
//    @Test
//    public void testSetStart_GenericType_boolean() {
//        System.out.println("setStart");
//        Number start = null;
//        boolean included = false;
//        NumericRange instance = new NumericRangeImpl();
//        instance.setStart(start, included);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEnd method, of class NumericRange.
//     */
//    @Test
//    public void testSetEnd_GenericType() {
//        System.out.println("setEnd");
//        Number end = null;
//        NumericRange instance = new NumericRangeImpl();
//        instance.setEnd(end);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setEnd method, of class NumericRange.
//     */
//    @Test
//    public void testSetEnd_GenericType_boolean() {
//        System.out.println("setEnd");
//        Number end = null;
//        boolean included = false;
//        NumericRange instance = new NumericRangeImpl();
//        instance.setEnd(end, included);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of transfer method, of class NumericRange.
//     */
//    @Test
//    public void testTransfer() {
//        System.out.println("transfer");
//        Number dgr = null;
//        NumericRange instance = new NumericRangeImpl();
//        instance.transfer(dgr);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of toString method, of class NumericRange.
//     */
//    @Test
//    public void testToString() {
//        System.out.println("toString");
//        NumericRange instance = new NumericRangeImpl();
//        String expResult = "";
//        String result = instance.toString();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of divide method, of class NumericRange.
//     */
//    @Test
//    public void testDivide() {
//        System.out.println("divide");
//        Number div = null;
//        boolean decreaseStep = false;
//        NumericRange instance = new NumericRangeImpl();
//        instance.divide(div, decreaseStep);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    public class NumericRangeImpl<T> implements NumericRange<Integer> {
//
//        public void times(T mul, boolean increaseStep) {
//        }
//
//        public void divide(T div, boolean decreaseStep) {
//        }
//
//        public void setStart(T start, boolean included) {
//        }
//
//        public void setEnd(T end, boolean included) {
//        }
//
//        public void transfer(T dgr) {
//        }
//
//        public String toString() {
//            return "";
//        }
//
//        @Override
//        public boolean empty() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public boolean contains(T item) {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public RangeIterator<T> iterate() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public Range<T> clone() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//
//        @Override
//        public int estimateSize() {
//            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//        }
//    }
    
}
