package screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import base.BaseScreen;
import math.Rect;
import sprite.Background;
import sprite.Hero;

public class MenuScreen extends BaseScreen {

    private Texture bg;
    private Background background;
    private Texture imghero;
    private Hero hero;
    private Vector2 v;
    private Vector2 direction;
    private Vector2 pointOfDestination;
    private float speed;

    @Override
    public void show() {
        super.show();
        bg = new Texture("fone.jpg");
        background = new Background(new TextureRegion(bg));
        imghero = new Texture("badlogic.jpg");
        hero = new Hero(new TextureRegion(imghero));
        v = new Vector2(0.005f, 0.005f);
        speed = v.len();
        pointOfDestination = new Vector2();
        direction = new Vector2();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        hero.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        hero.pos.add(v);
        if (hero.isMe(pointOfDestination)) {
            v.setZero();
            hero.pos.set(pointOfDestination);
        }
        batch.begin();
        background.draw(batch);
        hero.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        imghero.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        pointOfDestination.set(touch);
        changeDirection();
        return false;
    }

    private void changeDirection() {
        direction.set(pointOfDestination.cpy().sub(hero.pos));
        direction.setLength(speed);
        v.set(direction);
    }
}