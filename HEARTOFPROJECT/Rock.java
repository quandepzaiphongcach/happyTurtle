package happy.happy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.lang.reflect.AccessibleObject;

public class Rock extends Actor {
    TextureRegion textureRegion;
    float scaleX = 1.0f;
    float scaleY = 1.0f;
    Polygon polygon;
    Rock(float x, float y , Stage s){
        this.textureRegion = new TextureRegion(new Texture("rock.png")) ;
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        setPosition(MathUtils.random(200,1300), MathUtils.random(50,650));
        s.addActor(this);
        setOrigin(getOriginX()/2,getOriginY()/2);
        float[]toado = new float[]{
            5,19,
            21,8,
            43,9,
            53,15,
            61,33,
            53,56,
            35,62,
            17,57,
            6,39
        };
        polygon = new Polygon(toado);
        polygon.setOrigin(getWidth()/2, getHeight()/2);
        polygon.setPosition(getX(),getY());


    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), scaleX, scaleY, getRotation());
    }
    @Override
    public void act(float delta) {

        polygon.setPosition(getX(),getY());
        polygon.setRotation(getRotation());

    }
    public Polygon getPolygon(){
        return polygon;
    }
}
