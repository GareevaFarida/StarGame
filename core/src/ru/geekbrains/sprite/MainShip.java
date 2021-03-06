package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Ship;
import ru.geekbrains.math.Rect;
import ru.geekbrains.pool.BulletPool;
import ru.geekbrains.pool.ExplosionPool;
import ru.geekbrains.screen.GameScreen;

public class MainShip extends Ship {

    private static final int HP = 10;
    private static final int INVALID_POINTER = -1;

    private boolean pressedRight;
    private boolean pressedLeft;
    private boolean pressedUp;
    private boolean pressedDown;

    private int rightPointer = INVALID_POINTER;
    private int leftPointer = INVALID_POINTER;

    private GameScreen gameScreen;

    public MainShip(TextureAtlas atlas,
                    BulletPool bulletPool,
                    ExplosionPool explosionPool,
                    Sound shootSound,
                    GameScreen gameScreen) {
        super(atlas.findRegion("main_ship"), 1, 2, 2);
        this.bulletPool = bulletPool;
        this.explosionPool = explosionPool;
        this.bulletRegion = atlas.findRegion("bulletMainShip");
        this.shootSound = shootSound;
        setHeightProportion(0.15f);
        this.reloadInterval = 0.2f;
        this.bulletV.set(0f, 0.5f);
        this.bulletHeight = 0.015f;
        this.damage = 1;
        this.hp = HP;
        this.v0.set(0.5f, 0);
        this.gameScreen = gameScreen;
    }

    public void reset() {
        flushDestroy();
        hp = HP;
        pos.x = worldBounds.pos.x;
        setBottom(worldBounds.getBottom() + 0.05f);
        v.setZero();
    }


    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.05f);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
        if (getTop() > worldBounds.getTop()) {
            setTop(worldBounds.getTop());
            stop();
        }
        if (getBottom() < worldBounds.getBottom()) {
            setBottom(worldBounds.getBottom());
            stop();
        }
    }

    public boolean keyDown(int keycode) {
        pressedDown = false;
        pressedUp = false;
        pressedLeft = false;
        pressedRight = false;
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.W:
            case Input.Keys.UP:
                pressedUp = true;
                moveUp();
                break;
            case Input.Keys.S:
            case Input.Keys.DOWN:
                pressedDown = true;
                moveDown();
                break;
            case Input.Keys.SPACE:
                shoot();
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                } else {
                    stop();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                } else {
                    stop();
                }
                break;

            case Input.Keys.UP:
                pressedUp = false;
                if (pressedUp) {
                    moveUp();
                } else {
                    stop();
                }
                break;
            case Input.Keys.DOWN:
                pressedDown = false;
                if (pressedDown) {
                    moveDown();
                } else {
                    stop();
                }
                break;

        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        if (touch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 touch, int pointer) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > pos.y
                || bullet.getTop() < getBottom()
        );
    }

    private void moveRight() {
        v.set(v0);
    }

    private void moveLeft() {
        v.set(v0).rotate(180);
    }

    private void moveUp() {
        v.set(v0).rotate90(1);
    }

    private void moveDown() {
        v.set(v0).rotate90(-1);
    }

    private void stop() {
        v.setZero();
    }

    @Override
    public void destroy() {
        super.destroy();
        gameScreen.setStateGameOver();
    }
}
