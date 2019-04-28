package ru.geekbrains.sprite;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ScaledTouchUpButton;
import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.screen.GameScreen;

public class ButtonNewGame extends ScaledTouchUpButton {
    private Sprite gameOver;
    private Game game;
    private GameScreen gameScreen;

    public ButtonNewGame(TextureAtlas atlas, Sprite gameOver, Game game, GameScreen gameScreen) {
        super(atlas.findRegion("button_new_game"));
        this.gameOver = gameOver;
        this.game = game;
        this.gameScreen = gameScreen;
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
        gameScreen.setStateNewGame();
        game.setScreen(gameScreen);
    }
}
