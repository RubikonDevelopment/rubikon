package dev.rubikon.events;

import dev.rubikon.utils.Event;

/**
 * Represents the key press event.
 * @param key The key code of the key event.
 * @param action The action of the key event. Can be one of the following:
 *      <ul>
 *               <li>0) {@link org.lwjgl.glfw.GLFW#GLFW_RELEASE GLFW_RELEASE}</li>
 *               <li>1) {@link org.lwjgl.glfw.GLFW#GLFW_PRESS GLFW_PRESS}</li>
 *               <li>2) {@link org.lwjgl.glfw.GLFW#GLFW_REPEAT GLFW_REPEAT}</li>
 *      </ul>
 */
public record KeyPressEvent(int key, int action) implements Event {}
