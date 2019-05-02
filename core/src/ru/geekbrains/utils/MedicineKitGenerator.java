package ru.geekbrains.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.pool.MedicineKitPool;
import ru.geekbrains.sprite.MainShip;
import ru.geekbrains.sprite.MedicineKit;

public class MedicineKitGenerator {
    private MedicineKitPool medicineKitPool;
    private float generateInterval = 3f;
    private float generateTimer = generateInterval;
    private TextureRegion[] textureReg;
    private Vector2 v = new Vector2(0f, -0.1f);
    private MainShip mainShip;
    private static final int HP_MIN = 9;
    private Font font;

    public MedicineKitGenerator(Texture img, MedicineKitPool medicineKitPool, MainShip mainShip, Font font) {
        this.medicineKitPool = medicineKitPool;
        this.textureReg = new TextureRegion[]{new TextureRegion(img)};
        this.mainShip = mainShip;
        this.font = font;
    }

    public void generate(float delta) {
        if (mainShip.getHp()>HP_MIN){
            return;
        }
        generateTimer += delta;
        if (generateTimer >= generateInterval) {
            generateTimer = 0f;
            MedicineKit kit = medicineKitPool.obtain();
            kit.set(textureReg,
                    10,
                    v,
                    0.1f,
                    font);
        }
    }
}
