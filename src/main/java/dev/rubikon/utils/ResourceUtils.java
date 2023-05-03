package dev.rubikon.utils;
/*
 * Copyright LWJGL. All rights reserved.
 * License terms: https://www.lwjgl.org/license
 */
import lombok.experimental.UtilityClass;
import org.lwjgl.*;

import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.file.*;

import static org.lwjgl.BufferUtils.*;
import static org.lwjgl.system.MemoryUtil.*;

/**
 * Represents a utility class for resource-related operations.
 */
@UtilityClass
public class ResourceUtils {
    /**
     * Resizes the specified buffer to the specified new capacity.
     * @param buffer The buffer to resize.
     * @param newCapacity The new capacity.
     * @return The resized buffer.
     *
     * @throws BufferOverflowException If the new capacity is less than the current capacity.
     * @throws ReadOnlyBufferException If the buffer is read-only.
     * @throws IllegalArgumentException If the source buffer is the same as the destination buffer.
     */
    private static ByteBuffer resizeBuffer(ByteBuffer buffer, int newCapacity) {
        ByteBuffer newBuffer = BufferUtils.createByteBuffer(newCapacity);
        buffer.flip();
        newBuffer.put(buffer);
        return newBuffer;
    }

    /**
     * Reads the specified resource and returns the raw data as a ByteBuffer.
     *
     * @param resource   the resource to read
     * @param bufferSize the initial buffer size
     *
     * @return the resource data
     *
     * @throws IOException if an IO error occurs
     */
    private static ByteBuffer ioResourceToByteBuffer(String resource, int bufferSize) throws IOException {
        ByteBuffer buffer;

        Path path = resource.startsWith("http") ? null : Paths.get(resource);
        if (path != null && Files.isReadable(path)) {
            try (SeekableByteChannel fc = Files.newByteChannel(path)) {
                buffer = BufferUtils.createByteBuffer((int)fc.size() + 1);
                while (fc.read(buffer) != -1) {
                    ;
                }
            }
        } else {
            try (
                    InputStream source = resource.startsWith("http")
                            ? new URL(resource).openStream()
                            : ResourceUtils.class.getClassLoader().getResourceAsStream(resource);
                    ReadableByteChannel rbc = Channels.newChannel(source)
            ) {
                buffer = createByteBuffer(bufferSize);

                while (true) {
                    int bytes = rbc.read(buffer);
                    if (bytes == -1) {
                        break;
                    }
                    if (buffer.remaining() == 0) {
                        buffer = resizeBuffer(buffer, buffer.capacity() * 3 / 2); // 50%
                    }
                }
            }
        }

        buffer.flip();
        return memSlice(buffer);
    }

    /**
     * Loads the specified resource and returns the raw data as a ByteBuffer.
     * @param resource The resource to load.
     * @return The buffer containing the resource data.
     *
     * @throws RuntimeException If the resource could not be loaded.
     */
    public static ByteBuffer load(String resource){
        try {
            return ResourceUtils.ioResourceToByteBuffer(resource, 150 * 1024);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource: " + resource, e);
        }
    }

    /**
     * Loads the specified image and returns the raw data as a ByteBuffer.
     * @param resource The image to load.
     * @return The buffer containing the image data.
     *
     * @throws RuntimeException If the image could not be loaded.
     */
    public static ByteBuffer loadImage(String resource){
        try {
            return ResourceUtils.ioResourceToByteBuffer(resource, 32 * 1024);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load resource: " + resource, e);
        }
    }
}
