package screen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import base.BaseScreen;
import math.Rect;
import sprite.Background;
import sprite.Ship;
import sprite.Star;

public class GameScreen extends BaseScreen {
    private Texture bg;
    private Background background;
    private TextureAtlas atlas;
    private TextureAtlas mainAtlas;
    private Star[] starList;
    private Ship ship;

    @Override
    public void show() {
        super.show();
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        mainAtlas = new TextureAtlas("textures/mainAtlas.tpack");
        starList = new Star[256];
        for (int i = 0; i < starList.length; i++) {
            starList[i] = new Star(atlas);
        }
        ship = new Ship(mainAtlas,0.01f);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    private void update(float delta) {
        for (Star star : starList) {
            star.update(delta);
        }
        ship.update(delta);
    }

    private void draw() {
        batch.begin();
        background.draw(batch);
        for (Star star : starList) {
            star.draw(batch);
        }
        ship.draw(batch);
        batch.end();
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (Star star : starList) {
            star.resize(worldBounds);
        }
        ship.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        bg.dispose();
        atlas.dispose();
        mainAtlas.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        super.keyDown(keycode);
        return ship.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        super.keyUp(keycode);
        return ship.keyUp();
    }
}
