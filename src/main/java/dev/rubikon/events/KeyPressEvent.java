package dev.rubikon.events;

import dev.rubikon.commons.Event;

/**
 * @param key The key code of the pressed key
 * @param action Type of the key event, can be one of these values:
 *      <ul>
 *               <li>0) {@link org.lwjgl.glfw.GLFW#GLFW_RELEASE GLFW_RELEASE}</li>
 *               <li>1) {@link org.lwjgl.glfw.GLFW#GLFW_PRESS GLFW_PRESS}</li>
 *               <li>2) {@link org.lwjgl.glfw.GLFW#GLFW_REPEAT GLFW_REPEAT}</li>
 *      </ul>
 */
public record KeyPressEvent(int key, int action) implements Event {}
