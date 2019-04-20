package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;

import screen.MenuScreen;

public class StarGame1 extends Game {

    @Override
    public void create() {
        setScreen(new MenuScreen(this));
    }

}

