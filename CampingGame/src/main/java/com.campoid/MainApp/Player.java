package main.java.com.campoid.MainApp;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
    private double x = 0;
    private double y = 0;
    private double dx = 0;
    public boolean faceLeft = false;
//    public Font textFont1 = new Font("Arial", Font.BOLD, 25);
    public int damage = 1;
    private double dy = 0;
    private double ax = 0;
    private double ay = 0;
    public int worldX = 0;
    public int worldY = 0;
    public CameraKnob cameraKnob;
    public boolean invincible = false;
    long gotHitTimer = System.currentTimeMillis();

    public long pickTimer = System.currentTimeMillis();
    public long hitTimer = System.currentTimeMillis();
    public long useItemTimer = System.currentTimeMillis();
    private MainApp mainApp;
    private BufferedImage texture;
    private BufferedImage textureFight;
    private BufferedImage textureDie;
    public int hunger = 100;
    public int thirst = 100;
    public InventoryGrid inventory;
    public ArrayList<Item> itemInventory = new ArrayList<>();
    public boolean fighting = false;
    public int health = 100;
    public boolean crafting = false;

    public Player(MainApp mainApp, double x, double y, InventoryGrid inventory) {
        super(x, y, 50, 50);
        worldX = (int)x;
        worldY = (int)y;
        this.x = x;
        this.y = y;
        this.mainApp = mainApp;
        cameraKnob = new CameraKnob(x, y, 0, 0);
        texture = mainApp.loadImage("playerPlaceholdr.png");
        textureFight = mainApp.loadImage("playerFight1.png");
        textureDie = mainApp.loadImage("death.png");

        this.inventory = inventory;

    }

    public void addHealth(int added) {
        health += added;
        if (health >= 100) {
            health = 100;
        }
        if (health <= 0) {
            health = 0;
        }
    }

    public void drink(int thirsty) {
        this.thirst += thirsty;
        if (this.thirst >= 100) {
            this.thirst = 100;
        }
    }

    public void eat(int nutrition) {
        this.hunger += nutrition;
        if (this.hunger >= 100) {
            this.hunger = 100;
        }
    }



    public boolean collideAnimal(Animal a) {
            double px = worldX;
            double py = worldY;
            double ax = a.position.x;
            double ay = a.position.y;
            return px + width > ax && px < ax + a.width && py + height > ay && py < ay + a.height;
    }
    public boolean collidesItem(Item a) {
        double px = worldX;
        double py = worldY;
        double ax = a.position.x;
        double ay = a.position.y;
        return px + width > ax && px < ax + a.width && py + height > ay && py < ay + a.height;
    }
    public boolean collideTree(Tree a) {
        double px = worldX;
        double py = worldY;
        double ax = a.worldX;
        double ay = a.worldY;
        return px + width > ax && px < ax + a.width && py + height > ay && py < ay + a.height;
    }

    public void collideAnimals() {
        for (Animal animal : mainApp.animals) {
            if (collideAnimal(animal)) {
                if (animal instanceof Deer) {
                    if (mainApp.keyH.space && System.currentTimeMillis() - hitTimer > 500){
                        hitTimer = System.currentTimeMillis();
                        fighting = true;
                        animal.health -= damage;
                        mainApp.explosions.add(new ParticleExplosion(mainApp, worldX, worldY, true));
                        System.out.println("Hit " + animal.toString() + " for " + damage + " damage. " + animal.health + " left");
                    }
                }
                if (animal instanceof Rabbit) {
                    if (mainApp.keyH.space && System.currentTimeMillis() - hitTimer > 500){
                        hitTimer = System.currentTimeMillis();
                        fighting = true;
                        animal.health -= damage;
                        mainApp.explosions.add(new ParticleExplosion(mainApp, worldX, worldY));
                        System.out.println("Hit " + animal.toString() + " for " + damage + " damage. " + animal.health + " left");
                    }
                }
                if (animal instanceof BlackBear) {
                    if (!invincible && System.currentTimeMillis() - gotHitTimer > 1000) {
                        invincible = true;
                        gotHitTimer = System.currentTimeMillis();
                        addHealth(-30);
                        System.out.println("OUCH");
                    }
                }
            }
        }
    }

    public void collideTrees() {
        for (Tree tree : mainApp.trees) {
            if (collideTree(tree)) {
                if (mainApp.keyH.space && System.currentTimeMillis() - hitTimer > 500){
                    hitTimer = System.currentTimeMillis();
                    fighting = true;
                    tree.health -= damage;
                    mainApp.explosions.add(new ParticleExplosion(mainApp, worldX, worldY, new Color(110, 38, 14)));
                }
            }
        }
    }

    public void consumeItem(int index) {
        useItemTimer = System.currentTimeMillis();
        Item i = itemInventory.get(index);
        if (!(i instanceof NoItem)) {
            System.out.println(i.toString());
            i.consume();
            itemInventory.set(index, new NoItem(mainApp, "", 0, 0));
        }
    }


    public void update() {
        if (System.currentTimeMillis() - hitTimer > 400) {
            fighting = false;
        }
        if (System.currentTimeMillis() - hitTimer > 1000) {
            invincible = false;
        }
        collideAnimals();
        collideTrees();
        this.dx = 0;
        this.dy = 0;
        this.ax = 0;
        this.ay = 0;
        if (System.currentTimeMillis() % 500 < 20) {
            hunger -= 2;
            thirst -= 3;
        }
        if (hunger >= 50 && thirst >= 50) {
            if (System.currentTimeMillis() % 500 < 20) {
                addHealth(1);
            }
        }
        if (hunger <= 0 || thirst <= 0 || health <= 0) {
            mainApp.gameOver = true;
        }
        if (mainApp.keyH.down) {
            this.dy = 5;
        }
        if (mainApp.keyH.left) {
            this.dx = -5;
        }
        if (mainApp.keyH.up) {
            this.dy = -5;
            //System.out.println("fuck you");
        }
        if (mainApp.keyH.right) {
            this.dx = 5;
        }
        if (System.currentTimeMillis() - useItemTimer > 500) {
            if (mainApp.keyH.one && itemInventory.size() > 0) {
                consumeItem(0);
            }
            if (mainApp.keyH.two && itemInventory.size() > 1) {
                consumeItem(1);
            }

            if (mainApp.keyH.three && itemInventory.size() > 2) {
                consumeItem(2);
            }

            if (mainApp.keyH.four && itemInventory.size() > 3) {
                consumeItem(3);
            }

            if (mainApp.keyH.five && itemInventory.size() > 4) {
                consumeItem(4);
            }

            if (mainApp.keyH.six && itemInventory.size() > 5) {
                consumeItem(5);
            }

            if (mainApp.keyH.seven && itemInventory.size() > 6) {
                consumeItem(6);
            }

            if (mainApp.keyH.eight && itemInventory.size() > 7) {
                consumeItem(7);
            }

            if (mainApp.keyH.nine && itemInventory.size() > 8) {
                consumeItem(8);
            }
        }
        for (Item item : mainApp.items) {
            item.drawText = false;
            if (collidesItem(item)) {
                item.drawText = true;
                if (mainApp.keyH.eKey && System.currentTimeMillis() - pickTimer > 500){
                    pickTimer = System.currentTimeMillis();
                    if (item instanceof Water) {
                        item.consume();
                    } else {
                        if (itemInventory.size() < 9) {
                            boolean placedItem = false;
                            for (int i = 0; i < itemInventory.size(); i++) {
                                if (itemInventory.get(i) instanceof NoItem) {
                                    itemInventory.set(i, item);
                                    item.moveToPlayerInventory(true, i);
                                    placedItem = true;
                                }
                            }
                            if (!placedItem) {
                                itemInventory.add(item);
                                int index = itemInventory.size() - 1;
                                item.moveToPlayerInventory(true, index);
                            }
                        }
                    }
                }
            }
        }

        this.worldX += this.dx;
        this.worldY += this.dy;
        this.dx += this.ax;
        this.dy += this.ay;
    }

    public void draw(Graphics2D g2) {
        if (mainApp.keyH.right) {
            faceLeft = false;
        }
        if (mainApp.keyH.left) {
            faceLeft = true;
        }
        if (health > 0) {
            if (faceLeft) {
                if (fighting) {
                    g2.drawImage(textureFight, (int) this.x+mainApp.G_WIDTH/2 + this.width, (int)this.y+mainApp.G_HEIGHT/2, -(int)this.width, (int)this.height, null);
                } else {
                    g2.drawImage(texture, (int) this.x+mainApp.G_WIDTH/2 + this.width, (int)this.y+mainApp.G_HEIGHT/2, -(int)this.width, (int)this.height, null);
                }
            } else {
                if (fighting) {
                    g2.drawImage(textureFight, (int) this.x+mainApp.G_WIDTH/2, (int)this.y+mainApp.G_HEIGHT/2, (int)this.width, (int)this.height, null);
                } else {
                    g2.drawImage(texture, (int) this.x+mainApp.G_WIDTH/2, (int)this.y+mainApp.G_HEIGHT/2, (int)this.width, (int)this.height, null);
                }
            }
        } else {
            g2.drawImage(textureDie, (int) this.x+mainApp.G_WIDTH/2 + this.width, (int)this.y+mainApp.G_HEIGHT/2, -(int)this.width, (int)this.height, null);
        }
        g2.setFont(MainApp.textFontBold);
        g2.setColor(new Color(0, 250, 0));
        int barsX = 150;
        int bw = 250;
        g2.fillRect(barsX, 30, (int)(((double)hunger / 100) * bw), 25);
        g2.setColor(new Color(0, 0, 250));
        g2.fillRect(barsX, 60, (int)(((double)thirst / 100) * bw), 25);
        g2.setColor(new Color(255, 0, 0));
        g2.fillRect(barsX, 90, (int)(((double)health / 100) * bw), 25);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(barsX, 30, bw, 25);
        g2.drawRect(barsX, 60, bw, 25);
        g2.drawRect(barsX, 90, bw, 25);
        g2.drawString("Hunger", 50, 45);
        g2.drawString("Thirst", 50, 75);
        g2.drawString("Health", 50, 105);
//        g2.drawString("Player World X: " + worldX, 200, 20);
//        g2.drawString(aw"Player World Y: " + worldY, 200, 40);
    }
    public double getX() {
        return this.x;
    }
    public double getY() {
        return this.y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getWidth() {
        return this.width;
    }
    public double getHeight() {
        return this.height;
    }
}
