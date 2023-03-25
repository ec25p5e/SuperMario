package jade;

import java.awt.event.KeyEvent;

/**
 * Questa classe rappresenta l'editor della scena.
 * Verrà utilizzato per creare i livelli del gioco
 */
public class LevelEditorScene extends Scene {
    private boolean changingScene = false;
    private float timeToChangeScene = 2.0f;

    public LevelEditorScene() {
        System.out.println("Sono il level editor scene");
    }

    /**
     * Questo metodo si occupa di aggiornare i dati
     *
     * @param dt tempo di differenza tra due frame (delta)
     */
    @Override
    public void update(float dt) {
        if(!this.changingScene && KeyListener.isKeyPressed(KeyEvent.VK_SPACE))
            this.changingScene = true;

        if(this.changingScene && this.timeToChangeScene > 0) {
            this.timeToChangeScene -= dt;
            Window.get().setR(Window.get().getR() - dt * 5.0f);
            Window.get().setG(Window.get().getG() - dt * 5.0f);
            Window.get().setB(Window.get().getB() - dt * 5.0f);
        } else if(this.changingScene) {
            Window.changeScene(1);
        }
    }
}
