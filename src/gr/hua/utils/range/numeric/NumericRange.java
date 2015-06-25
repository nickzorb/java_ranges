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

import gr.hua.utils.range.Range;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 *
 * @author Nikolaos Zormpas <nickzorb@gmail.com>
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
        String[] segments = s.split("/");
        if (segments.length != 4) {
            throw new ParseException("Could not parse range '" + s + "'", 0);
        }
        Number start;
        Number end;
        Number step;
        Long maxSpeed;

        NumberFormat format = NumberFormat.getInstance();
        String temp = segments[0].substring(1);
        int code = 0;
        switch (segments[0].charAt(0)) {
            case '{':
                code = 3;
                start = 0L;
                break;
            case '[':
                code = 2;
            case '(':
                start = format.parse(temp);
                break;
            default:
                throw new ParseException("Could not parse segment '" + segments[0] + "'", 0);
        }
        temp = segments[1].substring(0, segments[1].length() - 1);
        switch (segments[1].charAt(segments[1].length() - 1)) {
            case '}':
                end = format.parse(temp).longValue() - 1L;
                break;
            case ']':
                code++;
            case ')':
                end = format.parse(temp);
                break;
            default:
                throw new ParseException("Could not parse segment '" + segments[1] + "'", 0);
        }
        step = format.parse(segments[2]);
        maxSpeed = format.parse(segments[3]).longValue();
        return generateRange(start, end, step, maxSpeed, InclusionType.values()[code]);
    }

    static NumericRange basicRange(Long items) {
        return generateRange(0L, items - 1L, 1L, 1L, InclusionType.CLOSED_CLOSED);
    }

    static NumericRange<Long> generateRange(Long start, Long end) {
        return generateRange(start, end, 1L, 1L, InclusionType.CLOSED_CLOSED);
    }

    static NumericRange<Long> generateRange(Long start, Long end, Long step, Long maxSpeed) {
        return generateRange(start, end, step, maxSpeed, InclusionType.CLOSED_CLOSED);
    }

    static NumericRange<Long> generateRange(Long start, Long end, InclusionType code) {
        return generateRange(start, end, 1L, 1L, code);
    }

    static NumericRange<Long> generateRange(Long start, Long end, Long step, Long maxSpeed, InclusionType code) {
        switch (code) {
            case OPEN_OPEN:
                return new GenericLongRange(false, false, start, end, step, maxSpeed);
            case OPEN_CLOSED:
                return new GenericLongRange(false, true, start, end, step, maxSpeed);
            case CLOSED_OPEN:
                return new GenericLongRange(true, false, start, end, step, maxSpeed);
            case CLOSED_CLOSED:
                return new GenericLongRange(true, true, start, end, step, maxSpeed);
            default:
                throw new UnsupportedOperationException();
        }
    }

    static NumericRange<Double> generateRange(Double start, Double end) {
        return generateRange(start, end, Double.MIN_VALUE, 1L, InclusionType.CLOSED_CLOSED);
    }

    static NumericRange<Double> generateRange(Double start, Double end, Double step, Long maxSpeed) {
        return generateRange(start, end, step, maxSpeed, InclusionType.CLOSED_CLOSED);
    }

    static NumericRange<Double> generateRange(Double start, Double end, InclusionType code) {
        return generateRange(start, end, Double.MIN_VALUE, 1L, code);
    }

    static NumericRange<Double> generateRange(Double start, Double end, Double step, Long maxSpeed, InclusionType code) {
        switch (code) {
            case OPEN_OPEN:
                return new GenericRealRange(false, false, start, end, step, maxSpeed);
            case OPEN_CLOSED:
                return new GenericRealRange(false, true, start, end, step, maxSpeed);
            case CLOSED_OPEN:
                return new GenericRealRange(true, false, start, end, step, maxSpeed);
            case CLOSED_CLOSED:
                return new GenericRealRange(true, true, start, end, step, maxSpeed);
            default:
                throw new UnsupportedOperationException();
        }
    }

    static <E extends Number> NumericRange generateRange(E start, E end, E step, Long maxSpeed, InclusionType code) {
        if (start.getClass().equals(Long.class) && end.getClass().equals(Long.class) && step.getClass().equals(Long.class)) {
            return generateRange(start.longValue(), end.longValue(), step.longValue(), maxSpeed, code);
        }
        return generateRange(start.doubleValue(), end.doubleValue(), step.doubleValue(), maxSpeed, code);
    }

    default NumericRange<T> times(Double mul) {
        return times(mul, true);
    }

    NumericRange<T> times(Double mul, boolean alterStep);

    default NumericRange<T> setStart(T start) {
        return setStart(start, true);
    }

    NumericRange<T> setStart(T start, boolean included);

    default NumericRange<T> setEnd(T end) {
        return setEnd(end, true);
    }

    NumericRange<T> setEnd(T end, boolean included);

    @Override
    String toString();
}
