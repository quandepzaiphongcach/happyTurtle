package happy.happy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;

public class MenuScreen implements Screen {
    Texture backgroundImage;
    Texture startImages;
    GlyphLayout layout;
    Turtle game;
    OrthographicCamera camera;
    Texture title;
    public MenuScreen(Turtle game){
        this.game = game;
    }
    Texture button;
    Stage stage2;

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        game.batch = new SpriteBatch();
        backgroundImage = new Texture("water-border.jpg");
        startImages = new Texture("message-start.png");
        title = new Texture("starfish-collector.png");
        button=new Texture("button.png") ;
        stage2 = new Stage();

        layout = new GlyphLayout();
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;
        style.fontColor = Color.WHITE;
        style.up = new TextureRegionDrawable(button);
        TextButton startButton = new TextButton("START",style);
        startButton.setSize(200,80);
        startButton.setPosition(Gdx.graphics.getWidth()*0.4f - startButton.getWidth()/2,
            Gdx.graphics.getHeight()*0.3f - startButton.getHeight()/2) ;
        stage2.addActor(startButton);
        startButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GameScreen(game));
            }
        });
        TextButton exitButton = new TextButton("QUIT",style);
        exitButton.setSize(200,80);
        exitButton.setPosition(Gdx.graphics.getWidth()*0.6f - exitButton.getWidth()/2,
            Gdx.graphics.getHeight()*0.3f - startButton.getHeight()/2) ;
        stage2.addActor(exitButton);
        exitButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                 Gdx.app.exit();
            }
        });
        Gdx.input.setInputProcessor(stage2);

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLUE);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundImage, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.draw(title,500,500);
        game.batch.draw(startImages,500,300);
        game.batch.end();
        stage2.act(Gdx.graphics.getDeltaTime());
        stage2.draw();
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            game.setScreen(new GameScreen(game));
        }


    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
