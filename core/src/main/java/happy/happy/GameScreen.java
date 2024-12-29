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
import com.badlogic.gdx.scenes.scene2d.Stage;
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


    public GameScreen(Turtle game){
        this.game = game;
        camera = new OrthographicCamera();
        stage1 = new Stage();
        shapeRenderer = new ShapeRenderer();
        camera.setToOrtho(false, Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        explosion = new Texture("whirlpool.png");
        background = new Texture("water.jpg");
        rock = new Rock(0,0,stage1);
        wood = new Wood(0,0,stage1);
        starFish = new StarFish(100,100,stage1);
        shark = new Shark(200,300,stage1);
        player = new Player(600,300,stage1);
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

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.BLACK);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(background, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.font.draw(game.batch, "StarFish : "+ countStarFish,0,650);
        game.batch.end();
        player.isCollison = false;
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
                st.play();
                countStarFish--;
                Explosion no = new Explosion(explosion,5,2);
                no.setPosition(starFish1.getX(),starFish1.getY());
                stage1.addActor(no);
                starFish1.remove();
                starFish1.polygon.setPosition(1000000,10000000);
            }
        }
        if(countStarFish == 0){
            game.setScreen(new WinScreen(game));
        }

        stage1.act(Gdx.graphics.getDeltaTime());
        stage1.draw();
        //shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        //shapeRenderer.setColor(Color.RED);
        //shapeRenderer.polygon(player.getPolygon().getTransformedVertices());
        //shapeRenderer.polygon(rock.getPolygon().getTransformedVertices());
        //shapeRenderer.polygon(wood.getPolygon().getTransformedVertices());
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
