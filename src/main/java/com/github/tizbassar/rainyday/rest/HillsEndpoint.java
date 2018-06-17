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

import com.github.tizbassar.rainyday.calc.Volume;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * Main endpoint, that will calculate water volume for given hills.
 *
 * @since 0.1
 */
@Path("/hills")
public class HillsEndpoint {

    /**
     * Volume.
     */
    private final Volume volume;

    /**
     * Ctor.
     *
     * <p>Required by JAX-RS specification.</p>
     */
    public HillsEndpoint() {
        this(null);
    }

    /**
     * Ctor.
     * @param volume Volume
     */
    @Inject
    public HillsEndpoint(final Volume volume) {
        this.volume = volume;
    }

    /**
     * Calculates water volume for given hills.
     * @param hills Given hills
     * @return Calculated water volume
     * @checkstyle DesignForExtensionCheck (8 lines)
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public int waterVolume(final HillsDto hills) {
        if (hills.getHills().stream().anyMatch(x -> x < 0)) {
            throw new BadRequestException(
                "Hills should contain only positive values."
            );
        }
        return this.volume.calculate(hills.getHills());
    }
}
