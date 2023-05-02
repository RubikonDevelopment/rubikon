package dev.rubikon.renderer.shader;

import dev.rubikon.Rubikon;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL20.*;

/**
 * making own shader object cause minecrafts sucks
 */
public class Shader {
    private int program;
    //default path for reading the shader into a string
    public Shader(String fragmentName,String vertexName) {
        //create program
        this.program = glCreateProgram();
        //attach vertex and fragment shaders onto a program
        glAttachShader(this.program, createShader(fragmentName, GL_FRAGMENT_SHADER));
        glAttachShader(this.program, createShader(vertexName, GL_VERTEX_SHADER));
        //link to a program
        glLinkProgram(this.program);
    }

    public void bind() {
        glUseProgram(program);
    }

    public void unbind() {
        glUseProgram(0);
    }

    public void uniform1i(String name,int i) {
        glGetUniformi(glGetUniformLocation(program,name),i);
    }


    public int getProgram() {
        return program;
    }

    private int createShader(String source, int shaderType) {
        int shader;
        //create shader attach the source and compile it
        shader = glCreateShader(shaderType);
        glShaderSource(shader, source);
        glCompileShader(shader);
        //check if the shader was compiled successfully
        int compiled = glGetShaderi(shader, GL_COMPILE_STATUS);
        if (compiled == GL_FALSE) {
            //get the shader info log and print it
            String log = glGetShaderInfoLog(shader);
            Rubikon.LOGGER.error("Shader#createShader shader compilation failed: " + log);
        }
        return shader;
    }
}
