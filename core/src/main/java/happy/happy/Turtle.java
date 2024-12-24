package happy.happy;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import java.awt.*;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Turtle extends Game {
    SpriteBatch batch;
    Stage stage;
    Music nhannen;
    BitmapFont font;

    @Override
    public void create() {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("OpenSans.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameters = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameters.size = 48;
        fontParameters.color = Color.YELLOW;
        font = fontGenerator.generateFont(fontParameters);
        fontGenerator.dispose();
        stage = new Stage();
        batch = new SpriteBatch();
        nhannen= Gdx.audio.newMusic(Gdx.files.internal("Master_of_the_Feast.ogg"));
        this.setScreen(new MenuScreen(this));

    }

    public void render() {
        super.render();
        if(Gdx.input.isTouched()){
            System.out.println("x = " + Gdx.input.getX() + " y = " + (Gdx.graphics.getHeight() - Gdx.input.getY()));
        }
        nhannen.play();

    }

    @Override
    public void dispose() {

    }
}
