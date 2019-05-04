package ru.geekbrains.sprite;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.utils.ExplosionType;
import ru.geekbrains.utils.Regions;

public class Explosion extends Sprite {

    private Sound explosionSound;

    private float animateInterval = 0.017f;
    private float animateTimer = animateInterval;
    private final TextureRegion[] shipExpRegion;
    private TextureRegion[] medKitExpRegion = new TextureRegion[10];
    private static final float ANIMATE_INTERVAL_FOR_SHIP_EXPLOSION = 0.017f;
    private static final float ANIMATE_INTERVAL_FOR_KIT_EXPLOSION = 0.03f;

    public Explosion(TextureAtlas atlas, Sound explosionSound) {
        TextureRegion explosionReg = atlas.findRegion("explosion");
        this.shipExpRegion = Regions.split(explosionReg, 9, 9, 74);
        System.arraycopy(shipExpRegion, 0, this.medKitExpRegion, 0, 10);
        this.explosionSound = explosionSound;
    }

    public void set(float height, Vector2 pos, ExplosionType explosionType) {
        this.pos.set(pos);
        this.regions = getTextureRegion(explosionType);
        this.animateInterval = getAnimateInterval(explosionType);
        setHeightProportion(height);
        explosionSound.play();
    }

    private TextureRegion[] getTextureRegion(ExplosionType explosionType) throws IllegalArgumentException {
        switch (explosionType) {
            case SHIP:
                return shipExpRegion;
            case MEDICAL_KIT:
                return medKitExpRegion;
            default:
                throw new IllegalArgumentException("Invalid explosion type: " + explosionType);
        }
    }

    private float getAnimateInterval(ExplosionType explosionType) throws IllegalArgumentException {
        switch (explosionType) {
            case SHIP:
                return ANIMATE_INTERVAL_FOR_SHIP_EXPLOSION;
            case MEDICAL_KIT:
                return ANIMATE_INTERVAL_FOR_KIT_EXPLOSION;
            default:
                throw new IllegalArgumentException("Invalid explosion type: " + explosionType);
        }
    }

    @Override
    public void update(float delta) {
        animateTimer += delta;
        if (animateTimer >= animateInterval) {
            animateTimer = 0f;
            if (++frame == regions.length) {
                destroy();
            }
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        frame = 0;
    }
}
