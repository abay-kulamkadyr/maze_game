import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;



public class LoreMenu extends BasicGameState{
    Image bg;
    Image kiki;
    Image scully;
    Image ef;
    SpriteSheet kikiSheet;
    Animation kikiWalks;
    SpriteSheet scullySheet;
    Animation scullyWalks;
    private SpriteSheet skullSheet;
    private Animation skullWalks;
    float animationX = 210;
    float animationY = 400;


    private int slide=0;
    private int list=2;

    Input input;



    public LoreMenu(int state) {
    }
    public int getID(){
        return 2;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    bg=new Image("src/main/resources/LoreBG.png");
    kiki=new Image("src/main/resources/LoreKiki.png");
    scully=new Image("src/main/resources/LoreSC.png");
    ef= new Image("src/main/resources/LoreEF.png");
    kikiSheet = new SpriteSheet("src/main/resources/kikiSprite.png",66,125);
    kikiWalks = new Animation(kikiSheet,240);
    scullySheet = new SpriteSheet("src/main/resources/homieSprite.png",125,125);
    scullyWalks = new Animation(scullySheet,240);
    skullSheet = new SpriteSheet("src/main/resources/homiesHomieSprite.png",125,125);
    skullWalks = new Animation(skullSheet,240);




    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        if(slide==0){
            kiki.draw();
            kikiWalks.draw(animationX,animationY);
        }
        if(slide==1){
            scully.draw();
            scullyWalks.draw(animationX - 15,animationY);
        }
        if(slide==2){
            ef.draw();
            skullWalks.draw(animationX - 15,animationY);
        }




    }

    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
        input=container.getInput();
        int mouseX= Mouse.getX();
        int mouseY=Mouse.getY();
        int yBack=682;
        int xBack=16;
        int yArrows=676;
        int xPrev=1059;
        int xNext=1165;

        if( (mouseX<xBack+148 && mouseX>xBack) && (mouseY>yBack-64 && mouseY<yBack)) {
            if (Mouse.isButtonDown(0)) {
                game.enterState(0, new FadeOutTransition(), new FadeInTransition());
            }

        }
          if((mouseX>xPrev && mouseX<xNext) && (mouseY>yArrows-85 && mouseY<yArrows)){
              if (input.isMousePressed(0)){

             if(slide==0){
                 slide=list;
             }
             else{
                 slide--;
             }
         }

         }
          if((mouseX>xNext && mouseX<xNext+105) &&(mouseY>yArrows-85 && mouseY<yArrows)){
              if (input.isMousePressed(0)){
                  if(slide==list){
                      slide=0;
                  }
                  else{
                      slide++;
                  }

              }
          }

    }
}


