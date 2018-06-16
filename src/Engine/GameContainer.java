package Engine;

import Game.GameManager;

import javax.tools.Tool;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameContainer implements Runnable{

    private Thread thread;
    private Window window;
    private Renderer renderer;
    private Input input;
    private AbstractGame game;

    private int fps;
    private boolean running = false;
    private boolean fullscreen = true;
    private final double UPDATE_CAP = 1.0 / 60.0;
    private Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
    private int width = 1920, height = 1080;
    private float scale = (float) (screensize.getWidth() / width);
    private String title = "Bitcoin Clicker v1.0";

    public GameContainer(AbstractGame game){
        this.game = game;
    }

    public void start(GameManager gm) {
        window = new Window(this);
        renderer = new Renderer(this);
        input = new Input(this);

        thread = new Thread(this);
        thread.run();
    }

    public void stop() {

    }

    public void run() {
        running = true;

        boolean render;
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;

        double frameTime = 0;
        int frames = 0;
        fps = 0;

        while(running){
            render = false;

            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;

            unprocessedTime += passedTime;
            frameTime += passedTime;

            while(unprocessedTime >= UPDATE_CAP){
                unprocessedTime -= UPDATE_CAP;
                if(input.isKeyUp(KeyEvent.VK_Q)){
                    System.exit(0);
                }
                render = true;
                game.update(this, (float) UPDATE_CAP);
                input.update();
                if(frameTime > 1.0){
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }

            if(render){
                renderer.clear();
                game.render(this, renderer);
                renderer.drawText("FPS: " + fps, 0,0, 0xffffffff);
                window.update();
                frames++;
            }else{
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        dispose();

    }

    public void dispose(){

    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Window getWindow() {
        return window;
    }

    public Input getInput() {
        return input;
    }

    public int getFps() {
        return fps;
    }

}
