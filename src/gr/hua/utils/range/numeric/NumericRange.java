/*
 * Copyright 2014 Nikolaos Zormpas.
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
 * @author Nikolaos Zormpas
 * @param <T>
 */
public interface NumericRange<T extends Number> extends Range<T> {
    
    public static enum InclusionType {
        OPEN_OPEN,
        OPEN_CLOSED,
        CLOSED_OPEN,
        CLOSED_CLOSED
    }
    
    static NumericRange parseRange(String s) throws ParseException {
        s = s.replaceAll("\\.", ",");
        String firstPart = s.split(" - ")[0].trim();
        String secondPart = s.split(" - ")[1].split(" : ")[0].trim();
        String lastPart = s.split(" : ")[1].trim();
        Number start;
        Number end;
        Number minStep;
        Number maxStep;
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
        minStep = format.parse(lastPart.split(" / ")[0]);
        maxStep = format.parse(lastPart.split(" / ")[1]);
        return NumericRange.generateRange(start, end, minStep, maxStep, code);
    }
    
    static NumericRange<Integer> generateRange(Integer start, Integer end) {
        return NumericRange.generateRange(start, end, 1, end - start, InclusionType.CLOSED_CLOSED);
    }
    
    static NumericRange<Integer> generateRange(Integer start, Integer end, Integer minStep, Integer maxStep) {
        return NumericRange.generateRange(start, end, minStep, maxStep, InclusionType.CLOSED_CLOSED);
    }
    
    static NumericRange<Integer> generateRange(Integer start, Integer end, InclusionType code) {
        return NumericRange.generateRange(start, end, 1, end - start, code);
    }
    
    static NumericRange<Integer> generateRange(Integer start, Integer end, Integer minStep, Integer maxStep, InclusionType code) {
        switch (code) {
            case OPEN_OPEN:
                return new GenericIntegerRange(false, false, start, end, minStep, maxStep);
            case OPEN_CLOSED:
                return new GenericIntegerRange(false, true, start, end, minStep, maxStep);
            case CLOSED_OPEN:
                return new GenericIntegerRange(true, false, start, end, minStep, maxStep);
            case CLOSED_CLOSED:
                return new GenericIntegerRange(true, true, start, end, minStep, maxStep);
            default:
                throw new UnsupportedOperationException(); 
        }
    }
    
    static NumericRange<Double> generateRange(Double start, Double end) {
        return NumericRange.generateRange(start, end, Double.MIN_VALUE, end - start, InclusionType.CLOSED_CLOSED);
    }
    
    static NumericRange<Double> generateRange(Double start, Double end, Double minStep, Double maxStep) {
        return NumericRange.generateRange(start, end, minStep, maxStep, InclusionType.CLOSED_CLOSED);
    }
    
    static NumericRange<Double> generateRange(Double start, Double end, InclusionType code) {
        return NumericRange.generateRange(start, end, Double.MIN_VALUE, end - start, code);
    }
    
    static NumericRange<Double> generateRange(Double start, Double end, Double minStep, Double maxStep, InclusionType code) {
        switch (code) {
            case OPEN_OPEN:
                return new GenericRealRange(false, false, start, end, minStep, maxStep);
            case OPEN_CLOSED:
                return new GenericRealRange(false, true, start, end, minStep, maxStep);
            case CLOSED_OPEN:
                return new GenericRealRange(true, false, start, end, minStep, maxStep);
            case CLOSED_CLOSED:
                return new GenericRealRange(true, true, start, end, minStep, maxStep);
            default:
                throw new UnsupportedOperationException(); 
        }
    }
    
    static <E extends Number> NumericRange<Double> generateRange(E start, E end, E minStep, E maxStep, int code) {
        return NumericRange.generateRange(start.doubleValue(), end.doubleValue(), minStep.doubleValue(), maxStep.doubleValue(), code);
    }
    
    default NumericRange<T> times(T mul) {
        return times(mul, true);
    }
    
    NumericRange<T> times(T mul, boolean increaseStep);
    
    default NumericRange<T> divide(T div) {
        return divide(div, true);
    }
    
    NumericRange<T> divide(T div, boolean decreaseStep);
    
    default NumericRange<T> setStart(T start) {
        return setStart(start, true);
    }
    
    NumericRange<T> setStart(T start, boolean included);
    
    default NumericRange<T> setEnd(T end) {
        return setEnd(end, true);
    }
    
    NumericRange<T> setEnd(T end, boolean included);
    
    NumericRange<T> transfer(T dgr);
    
    @Override
    String toString();
}
