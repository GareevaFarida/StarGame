package screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Vector2 touch;
    private Vector2 pos;
    private Vector2 v;
    private Texture img;
    private Vector2 direction;
    private float speed;
    private int imgWidth;
    private int imgHeight;

    @Override
    public void show() {
        super.show();
        touch       = new Vector2();
        pos         = new Vector2();
        direction   = new Vector2();
        v           = new Vector2(0.2f, 0.5f);

        speed       = v.len();
        img         = new Texture("badlogic.jpg");
        imgWidth    = img.getWidth();
        imgHeight   = img.getHeight();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        pos.add(v);
        batch.begin();
        batch.draw(img, pos.x, pos.y);
        batch.end();
        if (Math.abs(pos.x - touch.x) < speed ||
                pos.x <= 0 ||
                pos.x > Gdx.graphics.getWidth() - imgWidth ||
                pos.y <= 0 ||
                pos.y > Gdx.graphics.getHeight() - imgHeight
        ) v.setZero();
    }

    @Override
    public void dispose() {
        super.dispose();
        img.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        System.out.println("touchDown touch.x = " + touch.x + " touch.y = " + touch.y);
        direction.set(touch.cpy().sub(pos));
        direction.nor();
        direction.scl(speed);
        v.set(direction);

        return false;
    }
}
