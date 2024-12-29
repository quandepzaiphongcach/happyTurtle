package happy.happy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import javax.accessibility.AccessibleRelation;
import java.nio.channels.AcceptPendingException;

public class Shark extends Actor {
    TextureRegion textureRegion;
    float scaleX = 1.0f;
    float scaleY = 1.0f;
    Polygon polygon;
    int speed;
    Shark(float x, float y , Stage s){
        speed = 6;
        this.textureRegion = new TextureRegion(new Texture("sharky.png")) ;
        setSize(textureRegion.getRegionWidth(),textureRegion.getRegionHeight());
        setPosition(x,y);
        s.addActor(this);
        setOrigin(getOriginX()/2,getOriginY()/2);
        float[]toado = new float[]{
            0,0,
            getWidth(),0,
            getWidth(),getHeight(),
            0,getHeight()
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
        if(getX()>Gdx.graphics.getWidth()-getWidth()){
            setRotation(MathUtils.random(90,270));
        }if(getX()<0+getWidth()) {
            setRotation(MathUtils.random(-90,90));
        }
        if(getY()>Gdx.graphics.getHeight()-getHeight()){
            setRotation(MathUtils.random(180,360));
        }if(getY()<0+getHeight()) {
            setRotation(MathUtils.random(0,180));
        }
        moveBy(6*MathUtils.cosDeg(getRotation()),6*MathUtils.sinDeg(getRotation()));
        //if((getRotation()<91)&&(getRotation()>-90)){
            //scaleX = 1;
        //}else {
            //scaleX = -1;
        //}
    }
}
