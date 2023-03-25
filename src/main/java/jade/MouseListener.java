package jade;

import lombok.Getter;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

/**
 * Classe che gestisce gli eventi del mouse
 */
@Getter
public class MouseListener {
    private double scrollX;
    private double scrollY;
    private double xPos;
    private double yPos;
    private double lastX;
    private double lastY;
    private boolean[] mouseButtonPressed = new boolean[3];
    private boolean isDragging;

    private static MouseListener INSTANCE;

    /**
     * Costruttore che inizializza gli attributi a 0.0
     */
    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.xPos = 0.0;
        this.yPos = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;
    }

    /**
     * Questo metodo ritorna l'istanza del gestore mouse
     * In questo modo si indica che questa è una classe singleton
     * @return istanza della classe singleton
     */
    public static MouseListener get() {
        if(MouseListener.INSTANCE == null)
            MouseListener.INSTANCE = new MouseListener();

        return MouseListener.INSTANCE;
    }

    /**
     * Metodo di callback che serve per impostare i parametri degli attributi
     * della classe
     * @param window contesto di riferimento
     * @param xPos posizione X del mouse
     * @param yPos posizione Y del mouse
     */
    public static void mousePosCallback(final long window, final double xPos, final double yPos) {
        MouseListener.get().lastX = MouseListener.get().xPos;
        MouseListener.get().lastY = MouseListener.get().yPos;
        MouseListener.get().xPos = xPos;
        MouseListener.get().yPos = yPos;
        MouseListener.get().isDragging = MouseListener.get().mouseButtonPressed[0] ||
                MouseListener.get().mouseButtonPressed[2];
    }

    /**
     * Questo metodo si occupa di gestire i click dei tre tasti del mouse
     * @param window contesto di riferimento
     * @param button pulsante cliccato
     * @param action azione eseguita
     * @param mods modalità (parametro non utlizzato ma gestito dal callback)
     */
    public static void mouseButtonCallback(final long window, final int button, final int action, final int mods) {
        if(action == GLFW_PRESS) {
            if(button < MouseListener.get().mouseButtonPressed.length)
                MouseListener.get().mouseButtonPressed[button] = true;
        } else if(action == GLFW_RELEASE) {
            if(button < MouseListener.get().mouseButtonPressed.length) {
                MouseListener.get().mouseButtonPressed[button] = false;
                MouseListener.get().isDragging = false;
            }
        }
    }

    /**
     * Questa funziona serve per impostare lo scroll del cursore del mouse
     * @param window contesto di riferimento
     * @param xOffset cambiamento della posizione X
     * @param yOffset cambiamento della posizione Y
     */
    public static void mouseScrollCallback(final long window, final double xOffset, final double yOffset){
        MouseListener.get().scrollX = xOffset;
        MouseListener.get().scrollY = yOffset;
    }

    /**
     * Questo metodo serve quando il frame corrente termina
     * e ne viene creato un nuovo successivamente.
     * Si reimpostano i valori a "0" e memorizzata la posizione della X e Y
     * del mouse (ultima posizione)
     */
    public static void endFrame() {
        MouseListener.get().scrollX = 0;
        MouseListener.get().scrollY = 0;
        MouseListener.get().lastX = MouseListener.get().xPos;
        MouseListener.get().lastY = MouseListener.get().yPos;
    }

    /**
     * Questo metodo serve per capire se il pulsante è premuto o no
     * @param button pulsante da individuare la situazione
     * @return valore booleano
     */
    public static boolean mouseButtonDown(final int button) {
        if(button < MouseListener.get().mouseButtonPressed.length)
            return MouseListener.get().mouseButtonPressed[button];

        return false;
    }

    // ======================= //
    // ==== CUSTOM GETTER ==== //
    // ======================= //

    public static float getDx() {
        return (float) (MouseListener.get().lastX - MouseListener.get().xPos);
    }

    public static float getDy() {
        return (float) (MouseListener.get().lastY - MouseListener.get().yPos);
    }
}
