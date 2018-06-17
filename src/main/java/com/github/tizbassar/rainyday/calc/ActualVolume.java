/*
 * MIT License
 *
 * Copyright (c) 2018 Tolegen Izbassar
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.tizbassar.rainyday.calc;

import java.util.List;
import javax.ejb.Stateless;

/**
 * Calculates volume of the holes covered by water for given hills.
 *
 * @since 0.1
 * @checkstyle DesignForExtensionCheck (100 lines)
 */
@Stateless
public class ActualVolume implements Volume {

    @Override
    public int calculate(final List<Integer> hills) {
        return ActualVolume.difference(
            hills,
            new LeftSurface(hills).character(),
            new RightSurface(hills).character()
        );
    }

    /**
     * The difference between characters of the surface.
     * @param hills Hills
     * @param left Left character of the surface
     * @param right Right character of the surface
     * @return Difference, which is, in fact, the desired volume
     */
    private static int difference(final List<Integer> hills,
        final List<Integer> left, final List<Integer> right) {
        int result = 0;
        for (int index = 0; index < hills.size(); index += 1) {
            final int min = Math.min(left.get(index), right.get(index));
            final int hill = hills.get(index);
            if (hill < min) {
                result += min - hill;
            }
        }
        return result;
    }
}
