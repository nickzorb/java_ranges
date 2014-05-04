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
import java.text.NumberFormat;
import java.text.ParseException;

/**
 *
 * @author NickZorb
 * @param <T>
 */
public interface NumericRange<T extends Number> extends Range<T> {
    
    static final int OPEN_OPEN = 0;
    static final int OPEN_CLOSED = 1;
    static final int CLOSED_OPEN = 2;
    static final int CLOSED_CLOSED = 3;
    
    static NumericRange parseRange(String s) throws ParseException {
        String firstPart = s.split(" - ")[0];
        String secondPart = s.split(" - ")[1].split(" : ")[0];
        String lastPart = s.split(" : ")[1];
        Number start;
        Number end;
        Number step;
        int code = 0;
        if (firstPart.startsWith("[")) {
            code = 2;
            firstPart = firstPart.replace("[", "");
        } else if (firstPart.startsWith("(")) {
            firstPart = firstPart.replace("(", "");
        } else {
            throw new IllegalArgumentException();
        }
        if (secondPart.endsWith("]")) {
            code++;
            secondPart = secondPart.replace("]", "");
        } else if (secondPart.endsWith(")")) {
            secondPart = secondPart.replace(")", "");
        } else {
            throw new IllegalArgumentException();
        }
        NumberFormat format = NumberFormat.getInstance();
        start = format.parse(firstPart);
        end = format.parse(secondPart);
        step = format.parse(lastPart.trim());
        return NumericRange.generateRange(start, end, step, code);
    }
    
    static NumericRange<Integer> generateRange(Integer start, Integer end) {
        return NumericRange.generateRange(start, end, 1, CLOSED_CLOSED);
    }
    
    static NumericRange<Integer> generateRange(Integer start, Integer end, int code) {
        return NumericRange.generateRange(start, end, 1, code);
    }
    
    static NumericRange<Integer> generateRange(Integer start, Integer end, Integer step, int code) {
        switch (code) {
            case OPEN_OPEN:
                return new GenericIntegerRange(false, false, start, end, step);
            case OPEN_CLOSED:
                return new GenericIntegerRange(false, true, start, end, step);
            case CLOSED_OPEN:
                return new GenericIntegerRange(true, false, start, end, step);
            case CLOSED_CLOSED:
                return new GenericIntegerRange(true, true, start, end, step);
            default:
                throw new UnsupportedOperationException(); 
        }
    }
    
    static NumericRange<Double> generateRange(Double start, Double end) {
        return NumericRange.generateRange(start, end, Double.MIN_VALUE, CLOSED_CLOSED);
    }
    
    static NumericRange<Double> generateRange(Double start, Double end, int code) {
        return NumericRange.generateRange(start, end, Double.MIN_VALUE, code);
    }
    
    static NumericRange<Double> generateRange(Double start, Double end, Double step, int code) {
        switch (code) {
            case OPEN_OPEN:
                return new GenericRealRange(false, false, start, end, step);
            case OPEN_CLOSED:
                return new GenericRealRange(false, true, start, end, step);
            case CLOSED_OPEN:
                return new GenericRealRange(true, false, start, end, step);
            case CLOSED_CLOSED:
                return new GenericRealRange(true, true, start, end, step);
            default:
                throw new UnsupportedOperationException(); 
        }
    }
    
    static <E extends Number> NumericRange<Double> generateRange(E start, E end, E step, int code) {
        return NumericRange.generateRange(start.doubleValue(), end.doubleValue(), step.doubleValue(), code);
    }
    
    void times(T mul, boolean increaseStep);
    
    void divide(T div, boolean decreaseStep);
    
    default void setStart(T start) {
        setStart(start, true);
    }
    
    void setStart(T start, boolean included);
    
    default void setEnd(T end) {
        setEnd(end, true);
    }
    
    void setEnd(T end, boolean included);
    
    void transfer(T dgr);
    
    @Override
    String toString();
}
