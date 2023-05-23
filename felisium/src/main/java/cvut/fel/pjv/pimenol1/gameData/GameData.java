package cvut.fel.pjv.pimenol1.gameData;

import java.io.Serializable;
import java.util.ArrayList;

public class GameData implements Serializable {

    private static final long serialVersionUID = 1L;

    // BAG
    private ArrayList<String> bagsItemsNames = new ArrayList<>();

    // ALIENS
    private ArrayList<Integer> aliensX = new ArrayList<>(), aliensY = new ArrayList<>();
    private ArrayList<Integer> aliensLive = new ArrayList<>();


    // OBJ
    private ArrayList<String> itemsName = new ArrayList<>();
    private ArrayList<Integer> itemsX = new ArrayList<>(), itemsY = new ArrayList<>();

    // NPC
    private ArrayList<Integer> catX= new ArrayList<>(), catY= new ArrayList<>();
    private ArrayList<String> catsName = new ArrayList<>();

    // player
    private int playerxWorld, playeryWorld;
    private int playerLife;

    public ArrayList<String> getBagsItemsNames() {
        return bagsItemsNames;
    }

    public void setBagsItemsNames(ArrayList<String> bagsItemsNames) {
        this.bagsItemsNames = bagsItemsNames;
    }

    public ArrayList<Integer> getAliensX() {
        return aliensX;
    }

    public void setAliensX(ArrayList<Integer> aliensX) {
        this.aliensX = aliensX;
    }

    public ArrayList<Integer> getAliensY() {
        return aliensY;
    }

    public void setAliensY(ArrayList<Integer> aliensY) {
        this.aliensY = aliensY;
    }

    public ArrayList<Integer> getAliensLive() {
        return aliensLive;
    }

    public void setAliensLive(ArrayList<Integer> aliensLive) {
        this.aliensLive = aliensLive;
    }

    public ArrayList<String> getItemsName() {
        return itemsName;
    }

    public void setItemsName(ArrayList<String> itemsName) {
        this.itemsName = itemsName;
    }

    public ArrayList<Integer> getItemsX() {
        return itemsX;
    }

    public void setItemsX(ArrayList<Integer> itemsX) {
        this.itemsX = itemsX;
    }

    public ArrayList<Integer> getItemsY() {
        return itemsY;
    }

    public void setItemsY(ArrayList<Integer> itemsY) {
        this.itemsY = itemsY;
    }

    public ArrayList<Integer> getCatX() {
        return catX;
    }

    public void setCatX(ArrayList<Integer> catX) {
        this.catX = catX;
    }

    public ArrayList<Integer> getCatY() {
        return catY;
    }

    public void setCatY(ArrayList<Integer> catY) {
        this.catY = catY;
    }

    public ArrayList<String> getCatsName() {
        return catsName;
    }

    public void setCatsName(ArrayList<String> catsName) {
        this.catsName = catsName;
    }

    public int getPlayerxWorld() {
        return playerxWorld;
    }

    public void setPlayerxWorld(int playerxWorld) {
        this.playerxWorld = playerxWorld;
    }

    public int getPlayeryWorld() {
        return playeryWorld;
    }

    public void setPlayeryWorld(int playeryWorld) {
        this.playeryWorld = playeryWorld;
    }

    public int getPlayerLife() {
        return playerLife;
    }

    public void setPlayerLife(int playerLife) {
        this.playerLife = playerLife;
    }

}


