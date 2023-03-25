package jade;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener INSTANCE;
    private boolean[] keyPressed = new boolean[350];

    private KeyListener() {
    }

    public static KeyListener get() {
        if(KeyListener.INSTANCE == null)
            KeyListener.INSTANCE = new KeyListener();

        return KeyListener.INSTANCE;
    }

    public static void keyCallback(final long window, final int key, final int scancode, final int action, final int mods) {
        if(action == GLFW_PRESS)
            KeyListener.get().keyPressed[key] = true;
        else if(action == GLFW_RELEASE)
            KeyListener.get().keyPressed[key] = false;
    }

    public static boolean isKeyPressed(final int keyCode) {
        return get().keyPressed[keyCode];
    }
}
