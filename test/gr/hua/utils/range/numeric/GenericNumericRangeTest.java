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

import gr.hua.utils.range.RangeIterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author NickZorb
 */
public class GenericNumericRangeTest {

    /**
     * Test of toString method, of class GenericNumericRange.
     */
    @Test
    public void testToString() {
        GenericNumericRange instance = new GenericNumericRangeImpl();
        String expResult = "[135.5 - 11351.351) : 0.11";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    public void testClone() {
        GenericNumericRange instance = new GenericNumericRangeImpl();
        assertEquals(instance.toString(), instance.clone().toString());
    }

    public class GenericNumericRangeImpl extends GenericNumericRange<Double> {

        public GenericNumericRangeImpl() {
            super(true, false, 135.5, 11351.351, 0.11);
        }

        @Override
        public GenericNumericRange<Double> clone() {
            return new GenericNumericRangeImpl();
        }

        @Override
        public void times(Double mul, boolean increaseStep) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setStart(Double start, boolean included) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void setEnd(Double end, boolean included) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void transfer(Double dgr) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean empty() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean contains(Double item) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public RangeIterator<Double> iterate() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public int estimateSize() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void divide(Double div, boolean decreaseStep) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    
}
