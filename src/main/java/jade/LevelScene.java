package jade;

public class LevelScene extends Scene {

    public LevelScene() {
        System.out.println("Sono il level scene");
        Window.get().setR(1);
        Window.get().setG(1);
        Window.get().setB(1);
    }

    /**
     * Questo metodo si occupa di aggiornare i dati
     *
     * @param dt tempo di differenza tra due frame (delta)
     */
    @Override
    public void update(float dt) {

    }
}
