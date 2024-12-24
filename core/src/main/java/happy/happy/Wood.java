package happy.happy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Wood extends Actor {
    TextureRegion textureRegion;
    float scaleX = 1.0f;
    float scaleY = 1.0f;
    Polygon polygon;
    Wood(float x, float y , Stage s){
        this.textureRegion = new TextureRegion(new Texture("sign.png")) ;
        setSize(textureRegion.getRegionWidth(), textureRegion.getRegionHeight());
        setPosition(MathUtils.random(200,1300), MathUtils.random(50,650));
        s.addActor(this);
        setOrigin(getOriginX()/2,getOriginY()/2);
        float[]toado = new float[]{
            63,48,
            4,48,
            2,11,
            27,7,
            27,1,
            39,1,
            39,7,
            63,10

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
