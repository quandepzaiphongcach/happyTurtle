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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
    Stage stage1;
    OrthographicCamera camera;
    Turtle game;
    Player player;
    Rock rock;
    Wood wood;
    StarFish starFish;
    ShapeRenderer shapeRenderer;
    BackGround backGround;
    Array<StarFish>starFishArray;
    Array<Rock>rockArray;
    Array<Wood>woodArray;
    Texture explosion;
    int countStarFish;
    Texture winTexture;
    Music st;
    Texture background;
    Shark shark;
    Texture explosion1;
    int lastTime;
    Texture resestImage;




    public GameScreen(Turtle game){
        this.game = game;
        camera = new OrthographicCamera();
        stage1 = new Stage();
        shapeRenderer = new ShapeRenderer();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        explosion = new Texture("whirlpool.png");
        explosion1 = new Texture("sparkle.png");
        resestImage = new Texture("undo.png");

        backGround = new BackGround(0,0,stage1);
        rock = new Rock(0,0,stage1);
        wood = new Wood(0,0,stage1);
        starFish = new StarFish(100,100,stage1);
        shark = new Shark(200,300,stage1);
        player = new Player(0,0,stage1);
        winTexture = new Texture("you-win.png");
        st = Gdx.audio.newMusic(Gdx.files.internal("Water_Drop.ogg"));

        rockArray = new Array<>();
        starFishArray = new Array<>();
        woodArray = new Array<>();
        woodArray.add(wood);
        starFishArray.add(starFish);
        rockArray.add(rock);
        for(int i = 0;i<3;i++) {
            spawnRock();
        }
        spawnWood();
        for(int j= 0; j<4;j++){
            spawnStarFish();
        }

        countStarFish = 5;
        lastTime = 0;

    }
    @Override
    public void show() {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;
        style.fontColor = Color.WHITE;
        style.up = new TextureRegionDrawable(resestImage);
        TextButton resetButton = new TextButton("",style);
        resetButton.setPosition(Gdx.graphics.getWidth()*0.95f - resetButton.getWidth()/2,
            Gdx.graphics.getHeight()*0.9f - resetButton.getHeight()/2) ;
        game.stage.addActor(resetButton);
        Gdx.input.setInputProcessor(game.stage);
        resetButton.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(new GameScreen(game));
            }
        });

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        player.isCollison = false;

        if((player.getX()>750-(player.speed * MathUtils.cosDeg(player.getRotation()))-player.getWidth()/2)&&(player.getX()<1500-(player.speed * MathUtils.cosDeg(player.getRotation()))-player.getWidth()/2)) {
                stage1.getCamera().position.x = player.getX()+ player.getWidth()/2;
        }
        if((player.getY()>350- (player.speed*MathUtils.sinDeg(player.getRotation()))-player.getHeight()/2)&&(player.getY()<700-(player.speed*MathUtils.sinDeg(player.getRotation()))-player.getHeight()/2)) {
                stage1.getCamera().position.y = player.getY()+player.getHeight()/2;
        }




        for(Wood wood1:woodArray) {
            if (Intersector.overlapConvexPolygons(player.polygon, wood1.polygon)) {
                if(player.speed != 0) {
                    player.moveBy(player.speed * -1 * MathUtils.cosDeg(player.getRotation()), player.speed * -1 * MathUtils.sinDeg(player.getRotation()));
                    if ((0 <= player.getRotation() && player.getRotation() < 180 && player.getRotation() != 90)) {
                        player.moveBy(0, -1);
                    } else if ((0 > player.getRotation() && player.getRotation() >= -180) && player.getRotation() != 90) {
                        player.moveBy(0, 1);

                    } else if (player.getRotation() == 90 || player.getRotation() == -90) {
                        player.moveBy(1, 0);
                    }
                } else {
                    player.moveBy(-1 * MathUtils.cosDeg(player.getRotation()), -1 * MathUtils.sinDeg(player.getRotation()));
                }
                player.speed = 0;
                player.isCollison = true;
            }

        }
        for(Rock rock1:rockArray) {
            if (Intersector.overlapConvexPolygons(player.polygon, rock1.polygon)) {
                if(player.speed != 0) {
                    player.moveBy(player.speed * -1 * MathUtils.cosDeg(player.getRotation()), player.speed * -1 * MathUtils.sinDeg(player.getRotation()));
                    if ((0 <= player.getRotation() && player.getRotation() < 180 && player.getRotation() != 90)) {
                        player.moveBy(0, -1);
                    } else if ((0 > player.getRotation() && player.getRotation() >= -180) && player.getRotation() != 90) {
                        player.moveBy(0, 1);

                    } else if (player.getRotation() == 90 || player.getRotation() == -90) {
                        player.moveBy(1, 0);
                    }
                } else { // nếu đang đứng yên, mà cố tình xoay thì nó sẽ lùi, để nó tự thoát ra
                    player.moveBy(-1 * MathUtils.cosDeg(player.getRotation()), -1 * MathUtils.sinDeg(player.getRotation()));
                }
                player.speed = 0;
                player.isCollison = true;
            }
        }
        for(StarFish starFish1:starFishArray){
            if(Intersector.overlapConvexPolygons(player.polygon,starFish1.polygon)){
                if(countStarFish>1) {
                    st.play();
                    countStarFish--;
                    Explosion no = new Explosion(explosion, 5, 2);
                    no.setPosition(starFish1.getX(), starFish1.getY());
                    stage1.addActor(no);
                    starFish1.remove();
                    starFish1.polygon.setPosition(1000000, 10000000);
                }else {
                    st.play();
                    countStarFish--;
                    Explosion no = new Explosion(explosion1, 8, 8);
                    no.setPosition(starFish1.getX(), starFish1.getY());
                    stage1.addActor(no);
                    starFish1.remove();
                    starFish1.polygon.setPosition(1000000, 10000000);
                }
            }
        }

            if (countStarFish == 0) {
                lastTime++;

            }
        if(lastTime>180) {
            game.setScreen(new WinScreen(game));
        }
        game.batch.begin();
        game.font.draw(game.batch, "StarFish : "+ countStarFish,0,650);
        game.batch.end();

        stage1.act(Gdx.graphics.getDeltaTime());
        stage1.draw();
        game.stage.act();
        game.stage.draw();

        shapeRenderer.end();
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
    public void spawnRock(){
        Rock da = new Rock(0,0,stage1);
        rockArray.add(da);
    }
    public void spawnStarFish(){
        StarFish saobien = new StarFish(0,0,stage1);
        starFishArray.add(saobien);
    }
    public void spawnWood(){
        Wood go = new Wood(0,0,stage1);
        woodArray.add(go);
    }
}
