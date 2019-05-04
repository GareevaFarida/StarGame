package ru.geekbrains.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.MedicineKitPool;
import ru.geekbrains.sprite.MainShip;
import ru.geekbrains.sprite.MedicineKit;

public class MedicineKitGenerator {
    private MedicineKitPool medicineKitPool;
    private float generateInterval = 3f;
    private float generateTimer = generateInterval;
    private TextureRegion[] textureReg;
    private MainShip mainShip;
    private static final int HP_MIN = 9;
    private static final float GENERATED_MIN_KIT_HP = 10f;
    private static final float GENERATED_MAX_KIT_HP = 20f;

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
            float hp_rnd = Rnd.nextFloat(GENERATED_MIN_KIT_HP,GENERATED_MAX_KIT_HP);
            kit.set(textureReg,
                    (int)hp_rnd,
                    0.1f/GENERATED_MIN_KIT_HP*hp_rnd,
                    font);
        }
    }
}
