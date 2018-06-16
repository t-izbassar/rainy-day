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
package com.github.tizbassar.rainyday.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

/**
 * Received hills DTO.
 *
 * <p>This class should be used only within REST boundaries, as it
 * specify contract with endpoint clients.</p>
 *
 * @since 0.1
 */
final class HillsDto {

    /**
     * Hills.
     */
    private final List<Integer> hills;

    /**
     * Ctor.
     * @param hills Received hills.
     */
    @JsonCreator
    HillsDto(@JsonProperty("hills") final List<Integer> hills) {
        this.hills = hills;
    }

    /**
     * Get received hills.
     * @return Hills
     */
    public List<Integer> getHills() {
        return this.hills;
    }

    @Override
    @SuppressWarnings("PMD.OnlyOneReturn")
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        final HillsDto dto = (HillsDto) obj;
        return Objects.equals(this.getHills(), dto.getHills());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getHills());
    }
}
