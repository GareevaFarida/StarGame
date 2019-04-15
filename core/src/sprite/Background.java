package sprite;


import com.badlogic.gdx.graphics.g2d.TextureRegion;

import base.Sprite;
import math.Rect;

public class Background extends Sprite {

    public Background(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.pos);
    }

}