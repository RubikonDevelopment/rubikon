#version 130

uniform vec2 resolution;
uniform float time;
//uniform vec3 color;

const float Pi = 3.14159;

const int   complexity      = 35;    // More points of color.
const float fluid_speed     = 1.5;  // Drives speed, higher number will make it slower.
const float color_intensity = 100.0;

void main()
{
    vec2 p = (2.0 * gl_FragCoord.xy - resolution) / max(resolution.x, resolution.y);
    for (int i = 1;i < complexity; i++)
    {
        vec2 newp = p + time * 0.001;
        newp.x += 0.6 / float(i) * sin(float(i) * p.y + time / fluid_speed + 0.3 * float(i)) + 0.5; // + mouse.y/mouse_factor+mouse_offset;
        newp.y += 0.6 / float(i) * sin(float(i) * p.x + time / fluid_speed + 0.3 * float(i + 10)) - 0.5; // - mouse.x/mouse_factor+mouse_offset;
        p = newp;
    }

    // Change the mix ratio to increase the marble feel and change the white color to a light blue color
    float mix_ratio = 0.4 * sin(3.0 * p.x) + 0.6;
    vec3 col = mix(vec3(0.1, 0.0, 0.5), vec3(0.6, 0.9, 1.0), mix_ratio);
    gl_FragColor = vec4(col, 1.0);
}
