package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Ship extends Sprite {
    private static final int LEFT = 21;
    private static final int RIGHT = 22;
    private Vector2 v = new Vector2();
    private float speed;

    public Ship(TextureAtlas atlas, float speed) {
        super(atlas.findRegion("main_ship"));
        setHeightProportion(0.2f);
        this.speed = speed;
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setHeightProportion(0.2f);
        setBottom(worldBounds.getBottom() + 0.02f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.x = pos.x + v.x;
        if (pos.x < -0.5f) {
            pos.x = -0.5f;
        }
        if (pos.x > 0.5f) {
            pos.x = 0.5f;
        }
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case LEFT: {
                v.set(-speed, pos.y);
                break;
            }
            case RIGHT: {
                v.set(speed, pos.y);
                break;
            }
        }
        return false;
    }

    public boolean keyUp() {
        v.setZero();
        return false;
    }

}
