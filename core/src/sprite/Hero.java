package sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import base.Sprite;
import math.Rect;

public class Hero extends Sprite {

    public Hero(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight() / 4);
        pos.set(worldBounds.pos);
    }
}
