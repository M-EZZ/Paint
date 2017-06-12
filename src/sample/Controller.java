package sample;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.lang.Math;

import java.security.PublicKey;

public class Controller {


    @FXML
    Group g;
    @FXML
    ColorPicker clr;
    @FXML
    ChoiceBox<Integer> choiceBox;

    Rectangle bg = new Rectangle(0,0,1190,560);
    int flag =0 , flagg =0;
    static double x,y,w,h;
    static int stroke =7;
    static int Counter = 0;
    int freehand_flag=0;
    static Color C  = Color.BLACK;

    public void test(){

    }

    public void rect(){
        if(flag==0){
            bg.setFill(Color.WHITE);
            g.getChildren().addAll(bg);
            flag++;
        }
        Rectangle r=new Rectangle();

        r.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EventHandler handler3 ,handler1;
                int z= Window.display();

                if (z == 1){
                    bg.addEventHandler(MouseEvent.MOUSE_DRAGGED , handler3 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            r.setWidth(event.getX() - r.getX());
                            r.setHeight(event.getY() - r.getY());
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler( MouseEvent.MOUSE_DRAGGED , handler3);
                        }
                    });
                }
                else if (z==2)
                {
                    bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler3=new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            r.setX(Math.abs(event.getX()));
                            r.setY(Math.abs(event.getY()));
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler(MouseEvent.MOUSE_DRAGGED,handler3);
                        }
                    });
                }
                else if(  z == 3)
                {
                    Rectangle copy=new Rectangle();
                    bg.addEventHandler(MouseEvent.MOUSE_PRESSED, handler3= new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            copy.setX(event.getX());
                            copy.setY(event.getY());
                            copy.setHeight(r.getHeight());
                            copy.setWidth(r.getWidth());
                            copy.setFill(Color.WHITE);
                            copy.setStrokeWidth(stroke);
                            copy.setStroke(C);
                            g.getChildren().add(copy);
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler( MouseEvent.MOUSE_PRESSED , handler3);
                        }
                    });
                }
                else if( z == 4){
                    g.getChildren().remove(r);
                }
                else if (z==5){
                    r.setFill(C);
                }
                else{
                    r.getTransforms().addAll(new Rotate(z,r.getX(),r.getY()));
                }
            }
        });
        r.setStrokeWidth(stroke);
        r.setStroke(C);
        r.setFill(Color.WHITE);

        EventHandler<MouseEvent> handler1, handler2 ;
        bg.addEventHandler(MouseEvent.MOUSE_PRESSED, handler1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x=event.getX();
                y=event.getY();
                r.setX(event.getX());
                r.setY(event.getY());
                g.getChildren().add(r);
            }
        });

        bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler2 =new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                r.setX(x);
                r.setY(y);
                if( event.getX()-x > 0){
                r.setWidth( Math.abs(event.getX()-x));
                r.setHeight(Math.abs(event.getY()-y));
                }
                else if (event.getX()-x <0){
                    r.setX(event.getX());
                    r.setY(event.getY());
                    r.setWidth( Math.abs(event.getX()-x));
                    r.setHeight(Math.abs(event.getY()-y));
                }
            }
        });
        bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bg.removeEventHandler(MouseEvent.MOUSE_PRESSED , handler1);
                bg.removeEventHandler(MouseEvent.MOUSE_DRAGGED,handler2);
            }
        });

    }

    public void line (){
        if(flag==0){
            bg.setFill(Color.WHITE);
            g.getChildren().addAll(bg);
            flag++;
        }

        Line l = new Line();
        l.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                EventHandler handler3 ,handler1;
                int z= Window.display();

                if (z == 1){
                    bg.addEventHandler(MouseEvent.MOUSE_DRAGGED , handler3 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            l.setEndX(event.getX());
                            l.setEndY(event.getY());
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler( MouseEvent.MOUSE_DRAGGED , handler3);
                        }
                    });
                }
                else if (z==2)
                {
                    bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler3=new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            double x = l.getStartX()-l.getEndX();
                            double y = l.getStartY()-l.getEndY();
                            if(x>0 && y>0){
                                l.setEndY(event.getY());
                                l.setEndX(event.getX());
                                l.setStartX(event.getX()+x);
                                l.setStartY(event.getY()+y);
                            }
                            else if (x<0 && y<0){
                                l.setStartY(event.getY());
                                l.setStartX(event.getX());
                                l.setEndX(event.getX() + Math.abs(x));
                                l.setEndY(event.getY() + Math.abs(y));
                            }

                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler(MouseEvent.MOUSE_DRAGGED,handler3);
                        }
                    });
                }
                else if(  z == 3)
                {
                    Line copy = new Line();
                    bg.addEventHandler(MouseEvent.MOUSE_PRESSED, handler3= new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            double x = l.getEndX()-l.getStartX();
                            double y = l.getEndY()-l.getStartY();
                            copy.setStartX(event.getX());
                            copy.setStartY(event.getY());
                            copy.setEndY(copy.getStartY()+Math.abs(y));
                            copy.setEndX(copy.getStartX()+Math.abs(x));
                            copy.setStrokeWidth(10);
                            copy.setStroke(C);
                            g.getChildren().add(copy);
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler( MouseEvent.MOUSE_PRESSED , handler3);
                        }
                    });
                }
                else if( z == 4){
                    g.getChildren().remove(l);
                }
                else if (z==5){
                    l.setStroke(C);
                }
                else
                {
                    l.getTransforms().addAll(new Rotate(z,l.getStartX(), l.getStartY() ));
                }
            }
        });
        l.setStrokeWidth(stroke);
        l.setStroke(C);
        EventHandler<MouseEvent> handler1, handler2 ;
        bg.addEventHandler(MouseEvent.MOUSE_PRESSED, handler1 =new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                l.setStartX(event.getX());
                l.setStartY(event.getY());
                l.setEndY(event.getY());
                l.setEndX(event.getX());
                g.getChildren().add(l);
            }
        });
        bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                l.setEndX(event.getX());
                l.setEndY(event.getY());
            }
        });
        bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bg.removeEventHandler(MouseEvent.MOUSE_PRESSED , handler1);
                bg.removeEventHandler(MouseEvent.MOUSE_DRAGGED,handler2);
            }
        });
    }

    public void oval (){
        if(flag==0){
            bg.setFill(Color.WHITE);
            g.getChildren().addAll(bg);
            flag++;
        }

        Ellipse r=new Ellipse();
        r.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Ellipse copy=new Ellipse();
                int z=Window.display();
                EventHandler <MouseEvent> handler3;
                if(z==3)
                {
                    bg.addEventHandler(MouseEvent.MOUSE_PRESSED,handler3=new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            copy.setCenterX(event.getX());
                            copy.setCenterY(event.getY());
                            copy.setRadiusX(r.getRadiusX());
                            copy.setRadiusY(r.getRadiusY());
                            copy.setFill(Color.WHITE);
                            copy.setStroke(C);
                            g.getChildren().add(copy);
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler(MouseEvent.MOUSE_PRESSED,handler3);
                        }
                    });
                }
                else if (z==2)
                {
                    bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler3=new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            r.setCenterX(Math.abs(event.getX()));
                            r.setCenterY(Math.abs(event.getY()));
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler(MouseEvent.MOUSE_RELEASED,handler3);
                        }
                    });
                }
                else if(z==1)
                {

                    bg.addEventHandler(MouseEvent.MOUSE_DRAGGED , handler3 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            r.setRadiusX(Math.abs(event.getX()- r.getCenterX()));
                            r.setRadiusY(Math.abs(event.getY()-r.getCenterY()));
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler( MouseEvent.MOUSE_DRAGGED , handler3);
                        }
                    });

                }
                else if( z == 4){
                    g.getChildren().remove(r);
                }
                else if (z==5){
                    r.setFill(C);
                }
                else
                {
                    r.getTransforms().addAll(new Rotate(z,r.getCenterX(),r.getCenterY()));
                }

            }
        });


        r.setFill(Color.WHITE);
        r.setStroke(C);
        r.setStrokeWidth(stroke);

        EventHandler<MouseEvent> handler1, handler2 ;

        bg.addEventHandler(MouseEvent.MOUSE_PRESSED, handler1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                r.setCenterX(event.getX());
                r.setCenterY(event.getY());
                g.getChildren().add(r);
            }
        });

        bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                r.setRadiusX(event.getX()-r.getCenterX());
                r.setRadiusY(event.getY()-r.getCenterY());
            }
        });
        bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bg.removeEventHandler(MouseEvent.MOUSE_PRESSED , handler1);
                bg.removeEventHandler(MouseEvent.MOUSE_DRAGGED,handler2);
            }
        });

    }

    public void circle(){
        if(flag==0){
            bg.setFill(Color.WHITE);
            g.getChildren().addAll(bg);
            flag++;
        }
        Circle Mycircle=new Circle();
        Mycircle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Circle copy=new Circle();
                int z=Window.display();
                EventHandler <MouseEvent> handler3;
                if(z==3)
                {
                    bg.addEventHandler(MouseEvent.MOUSE_PRESSED,handler3=new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            copy.setCenterX(event.getX());
                            copy.setCenterY(event.getY());
                            copy.setRadius(Mycircle.getRadius());
                            copy.setFill(Color.WHITE);
                            copy.setStroke(C);
                            copy.setStrokeWidth(stroke);
                            g.getChildren().add(copy);
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler(MouseEvent.MOUSE_PRESSED,handler3);
                        }
                    });
                }
                else if (z==2)
                {
                    bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler3=new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Mycircle.setCenterX(Math.abs(event.getX()));
                            Mycircle.setCenterY(Math.abs(event.getY()));
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler(MouseEvent.MOUSE_RELEASED,handler3);
                        }
                    });
                }
                else if(z==1)
                {


                    bg.addEventHandler(MouseEvent.MOUSE_DRAGGED , handler3 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            Mycircle.setRadius(event.getX() - Mycircle.getCenterX());

                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler( MouseEvent.MOUSE_DRAGGED , handler3);
                        }
                    });

                }
                else if( z == 4){
                    g.getChildren().remove(Mycircle);
                }
                else if (z==5){
                    Mycircle.setFill(C);
                }

            }
        });



        Mycircle.setFill(Color.WHITE);
        Mycircle.setStroke(C);
        Mycircle.setStrokeWidth(stroke);
        EventHandler<MouseEvent> handler1, handler2 ;
        bg.addEventHandler(MouseEvent.MOUSE_PRESSED, handler1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Mycircle.setCenterX(event.getX());
                Mycircle.setCenterY(event.getY());
                Mycircle.setRadius(0);
                g.getChildren().add(Mycircle);
            }
        });

        bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Mycircle.setRadius(event.getX()-Mycircle.getCenterX());
            }
        });
        bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bg.removeEventHandler(MouseEvent.MOUSE_PRESSED , handler1);
                bg.removeEventHandler(MouseEvent.MOUSE_DRAGGED,handler2);
            }
        });

    }

    public void triangle(){

        if(flag==0){
            bg.setFill(Color.WHITE);
            g.getChildren().addAll(bg);
            flag++;
        }

        double []points =new double[6];
        EventHandler<MouseEvent> handler1;
        bg.addEventHandler(MouseEvent.MOUSE_CLICKED, handler1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if(Counter==0)
                {
                    points[0]=event.getX();
                    points[1]=event.getY();
                    Counter++;

                }
                else if(Counter==1)
                {
                    points[2]=event.getX();
                    points[3]=event.getY();
                    Counter++;

                }
                else if(Counter==2)
                {
                    points[4]=event.getX();
                    points[5]=event.getY();
                    Counter++;
                    Polygon r = new Polygon(points);
                    r.setStrokeWidth(stroke);
                    r.setStroke(C);
                    r.setFill(Color.WHITE);
                    r.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            //Window.display();
                        }
                    });
                    g.getChildren().add(r);
                }
            }
        });
       if(Counter == 3){
           bg.removeEventHandler(MouseEvent.MOUSE_CLICKED , handler1);
           Counter=0;
       }
    }

    public void square (){
        if(flag==0){
            bg.setFill(Color.WHITE);
            g.getChildren().addAll(bg);
            flag++;
        }
        EventHandler<MouseEvent> handler1, handler2 ;
        Rectangle r=new Rectangle();
        r.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Rectangle copy=new Rectangle();
                int z=Window.display();
                EventHandler <MouseEvent> handler3;
                if(z==3)
                {
                    bg.addEventHandler(MouseEvent.MOUSE_PRESSED,handler3=new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            copy.setX(event.getX());
                            copy.setY(event.getY());
                            copy.setHeight(r.getHeight());
                            copy.setWidth(r.getWidth());
                            copy.setFill(Color.WHITE);
                            copy.setStroke(C);
                            copy.setStrokeWidth(stroke);
                            g.getChildren().add(copy);
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler(MouseEvent.MOUSE_PRESSED,handler3);
                        }
                    });
                }
                else if (z==2)
                {
                    bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler3=new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            r.setX(Math.abs(event.getX()));
                            r.setY(Math.abs(event.getY()));
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler(MouseEvent.MOUSE_RELEASED,handler3);
                        }
                    });
                }
                else if(z==1)
                {

                    bg.addEventHandler(MouseEvent.MOUSE_DRAGGED , handler3 = new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            r.setWidth(event.getX() - r.getX());
                            r.setHeight(event.getX() - r.getX());
                        }
                    });
                    bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            bg.removeEventHandler( MouseEvent.MOUSE_DRAGGED , handler3);
                        }
                    });

                }

                else if( z == 4){
                    g.getChildren().remove(r);
                }
                else if(z==5){
                    r.setFill(C);
                }
                else
                {
                    r.getTransforms().addAll(new Rotate(z,r.getX(),r.getY()));
                }

            }
        });


        r.setFill(Color.WHITE);
        r.setStroke(C);
        r.setStrokeWidth(stroke);

        bg.addEventHandler(MouseEvent.MOUSE_PRESSED,handler1 =  new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                x=event.getX();
                y=event.getY();
                r.setX(event.getX());
                r.setY(event.getY());
                g.getChildren().add(r);
            }
        });

        bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, handler2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                r.setX(x);
                r.setY(y);

                if( event.getX()-x > 0){
                    r.setWidth( Math.abs(event.getX()-x));
                    r.setHeight(Math.abs(event.getX()-x));
                }
                else if (event.getX()-x <0){
                    r.setX(event.getX());
                    r.setY(event.getY());
                    r.setWidth( Math.abs(event.getX()-x));
                    r.setHeight(Math.abs(event.getX()-x));
                }
            }
        });
        bg.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                bg.removeEventHandler(MouseEvent.MOUSE_PRESSED , handler1);
                bg.removeEventHandler(MouseEvent.MOUSE_DRAGGED,handler2);
            }
        });

    }

    public void clear () {
        g.getChildren().clear();
        flag=0;
    }

    public void freehand(){
        if(flag==0){
            bg.setFill(Color.WHITE);
            g.getChildren().addAll(bg);
            flag++;
        }
        bg.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Line l = new Line();
                l.setStroke(C);
                l.setStrokeWidth(stroke);
                l.setStrokeWidth(7);
                l.setStartX(event.getX());
                l.setEndX(event.getX());
                l.setStartY(event.getY());
                l.setEndY(event.getY());
                g.getChildren().add(l);
            }
        });
    }

    public void colorPicker(){
        clr.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                C = clr.getValue();
            }
        });
    }



    public void blue(){
        C = Color.BLUE;
    }
    public void black(){
        C = Color.BLACK;
    }
    public void white(){
        C=Color.WHITE;
    }
    public void gold(){
        C=Color.YELLOW;
    }
    public void green(){
        C = Color.GREEN;
    }
    public void red(){
        C=Color.RED;
    }
    public void brown(){C=Color.BROWN;}
    public void orange(){C=Color.ORANGE;}
    public void aqua(){C=Color.AQUA;}
    public void pink(){C=Color.HOTPINK;}
    public void purple(){C=Color.PURPLE;}
    public void magenta(){C=Color.MAGENTA;}
    public void maroon(){C=Color.MAROON;}
    public void grey(){C=Color.GREY;}

}
