package happy.happy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Player extends Actor {
    TextureRegion textureRegion;
    float scaleX = 1.0f;
    float scaleY = 1.0f;
    Polygon polygon;
    Animation<TextureRegion> animation;
    float time;
    float speed;
    boolean isCollison = false;
    float locateX;
    float locateY;


    Player(float x, float y , Stage s){
        speed = 0;
        Texture texture = new Texture("turtle.png");
       int cot = 6;
        int hang = 1;
        setPosition(x,y);
        setSize(texture.getWidth()/cot, texture.getHeight()/hang);
        float speed = 0.05f;
        TextureRegion[][] tam = TextureRegion.split(texture, texture.getWidth()/cot, texture.getHeight()/hang);// đưa tất cả vào danh một danh sách ảnh, vì 6 cột 1 hàng nên sẽ có 6 phần tử: 6 x 1
        TextureRegion[] frames = new TextureRegion[cot*hang];
        int index = 0;
        for (int i = 0; i < hang; i++) {
            for (int j = 0; j < cot; j++) {
                frames[index++] = tam[i][j];
            }
        }
        animation = new com.badlogic.gdx.graphics.g2d.Animation<TextureRegion>(speed, frames);
        animation.setPlayMode(Animation.PlayMode.LOOP);
        time = 0;

        s.addActor(this);
        setOrigin(getWidth()/2,getHeight()/2);
        float[]toado = new float[]{
            21,30,
            38,23,
            59,24,
            74,39,
            74,56,
            57,72,
            34,70,
            19,64,
            12,47,
            16,35
        };

        polygon = new Polygon(toado);
        polygon.setOrigin(getWidth()/2, getHeight()/2);
        polygon.setPosition(getX(),getY());


    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        textureRegion = animation.getKeyFrame(time);
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), scaleX, scaleY, getRotation());
    }
    @Override
    public void act(float delta) {
        polygon.setPosition(getX(),getY());
        polygon.setRotation(getRotation());

        if(!isCollison) {
            if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                rotateBy(-2);

            }
            if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                rotateBy(2);
            }

            if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                locateX += speed*MathUtils.cosDeg(this.getRotation());
                locateY += speed*MathUtils.sinDeg(this.getRotation());
                if (speed < 4.05f) {
                    speed = speed + 0.05f;
                }
                time += delta;

            } else {
                speed = speed * 0.99f;
            }
            moveBy(speed * MathUtils.cosDeg(this.getRotation()), speed * MathUtils.sinDeg(this.getRotation()));

        }
    }
    public Polygon getPolygon(){
        return polygon;
    }

}
