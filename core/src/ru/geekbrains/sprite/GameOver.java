package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ScaledTouchUpButton;
import ru.geekbrains.math.Rect;

public class GameOver extends ScaledTouchUpButton {

    public GameOver(TextureAtlas atlas,Rect worldBounds) {
        super(atlas.findRegion("message_game_over"));
        setHeightProportion(0.08f);
        setTop(worldBounds.getTop()+0.2f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(worldBounds.getTop()+0.2f);
    }

    @Override
    protected void action() {

    }
}
