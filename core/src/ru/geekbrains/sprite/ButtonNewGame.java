package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ScaledTouchUpButton;
import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class ButtonNewGame extends ScaledTouchUpButton {
    private Sprite gameOver;

    public ButtonNewGame(TextureAtlas atlas, Sprite gameOver) {
        super(atlas.findRegion("button_new_game"));
        this.gameOver = gameOver;
        setHeightProportion(0.05f);
        setTop(gameOver.getBottom()-0.05f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setTop(gameOver.getBottom()-0.05f);
    }


    @Override
    protected void action() {
        System.out.println("NEW GAME");

    }
}
