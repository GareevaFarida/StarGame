package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Vector2 touch;
    private Vector2 pos;
    private Vector2 v;
    private Texture img;
    private Vector2 direction;
    private Vector2 pointOfDestination;
    private float speed;
    private int imgWidth;
    private int imgHeight;
    private static final int SCREEN_HIGHT = Gdx.graphics.getHeight();
    private static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final int UP = 19;
    private static final int DOWN = 20;
    private static final int LEFT = 21;
    private static final int RIGHT = 22;

    @Override
    public void show() {
        super.show();
        touch = new Vector2();
        pos = new Vector2();
        direction = new Vector2();
        pointOfDestination = new Vector2();
        v = new Vector2(1, 1);
        speed = v.len();
        img = new Texture("badlogic.jpg");
        imgWidth = img.getWidth();
        imgHeight = img.getHeight();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        pos.add(v);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        if (Math.abs(pos.x - pointOfDestination.x) < speed
                & Math.abs(pos.y-pointOfDestination.y)< speed
        ) v.setZero();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, SCREEN_HIGHT - screenY);
        System.out.println("touchDown touch.x = " + touch.x + " touch.y = " + touch.y);
        pointOfDestination.set(touch);
        changeDirection();

        return false;
    }

    private void changeDirection() {
        direction.set(pointOfDestination.cpy().sub(pos));
        direction.nor();
        direction.scl(speed);
        v.set(direction);
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case UP: {
                pointOfDestination.set(pos.x, SCREEN_HIGHT);
                changeDirection();
                break;
            }
            case DOWN: {
                pointOfDestination.set(pos.x, 0);
                changeDirection();
                break;
            }
            case LEFT: {
                pointOfDestination.set(0, pos.y);
                changeDirection();
                break;
            }
            case RIGHT: {
                pointOfDestination.set(SCREEN_WIDTH, pos.y);
                changeDirection();
                break;
            }
        }
        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        v.setZero();
        return super.keyUp(keycode);
    }
}
