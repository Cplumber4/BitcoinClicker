package Game;

import Engine.AbstractGame;
import Engine.GFX.Font;
import Engine.GFX.Image;
import Engine.GameContainer;
import Engine.Renderer;
import Engine.SaveData.Write;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class GameManager extends AbstractGame {

    private static final DecimalFormat bitcoinDF = new DecimalFormat("#,##0.00000");
    private static final DecimalFormat dollarsDF = new DecimalFormat("#,##0.00");

    private static final Font bigText = new Font("/Font/ArialBig.png");

    private static final int CLICKER = 0;
    private static final int STORE = 1;
    private static final int EXCHANGE = 2;

    private static final int CPU = 0;
    private static final int GCARD = 1;
    private static final int MININGRIG = 2;
    private static final int STORAGEFACILITY = 3;

    private int tab = CPU;
    private int scene = CLICKER;

    private Image background;
    private Image coin;
    private Image bill;
    private Image IntelPentiumA80501;
    private Image NVIDIAQUADRO;
    private Image ASICMiner;
    private Image facility;
    private Player player;
    private static Store shop;
    private static int numRigs = 0;
    private static int numFacilities = 5;
    private Write write;

    private int exchangeRate = 1865380;

    public GameManager(){
        background = new Image("/Clicker/motherboard.png");
        coin = new Image("/Clicker/Bitcoin.png");
        bill = new Image("/Exchange/100DollarBill.jpg");
        IntelPentiumA80501 = new Image("/Store/CPUs/KL Intel Pentium A80501.jpg");
        NVIDIAQUADRO = new Image("/Store/Graphics Cards/NVIDIA Quadro K620 2GB.png");
        ASICMiner = new Image("/Store/Mining Rigs/Antminer-S9-14TH.png");
        facility = new Image("/Store/Storage Facilities/storage.png");
        player = new Player();
        write = new Write(this);
    }

    public void update(GameContainer gc, float dt) {
        if(gc.getInput().isKeyUp(KeyEvent.VK_S)){
            Write.save();
        }
        player.addBitcoin(player.getPassiveValue()/60);
        player.setTotalBitcoinEarned(player.getTotalBitcoinEarned() + player.getPassiveValue()/60);

        if(gc.getInput().isButtonUp(MouseEvent.BUTTON1)) {
            if(1435 < gc.getInput().getMouseX() && gc.getInput().getMouseX() < 1590 && gc.getInput().getMouseY() < 30){
                scene = CLICKER;
            }else if(1815 < gc.getInput().getMouseX() && gc.getInput().getMouseY() < 30){
                scene = STORE;
            }else if(1605 < gc.getInput().getMouseX() && gc.getInput().getMouseX() < 1800 && gc.getInput().getMouseY() < 30){
                scene = EXCHANGE;
            }else if(scene == CLICKER){
                if(Math.hypot((gc.getInput().getMouseX() - gc.getWidth()/2), (gc.getInput().getMouseY() - gc.getHeight()/2)) < 150){
                    player.click();
                }
            }else if (scene == STORE){
                if(130 < gc.getInput().getMouseX() && gc.getInput().getMouseX() < 360 && 265 < gc.getInput().getMouseY() && gc.getInput().getMouseY() < 300){
                    tab = CPU;
                }else if(460 < gc.getInput().getMouseX() && gc.getInput().getMouseX() < 750 && 265 < gc.getInput().getMouseY() && gc.getInput().getMouseY() < 300){
                    tab = GCARD;
                }else if(1170 < gc.getInput().getMouseX() && gc.getInput().getMouseX() < 1370 && 265 < gc.getInput().getMouseY() && gc.getInput().getMouseY() < 300){
                    tab = MININGRIG;
                }else if(1530 < gc.getInput().getMouseX() && gc.getInput().getMouseX() < 1835 && 265 < gc.getInput().getMouseY() && gc.getInput().getMouseY() < 300){
                    tab = STORAGEFACILITY;
                }
                if(tab == CPU){
                    if(numRigs < numFacilities && 390 < gc.getInput().getMouseX() && gc.getInput().getMouseX() < 575 && 460 < gc.getInput().getMouseY() && gc.getInput().getMouseY() < 640 && player.getBalanceCents() >= (shop.getPentiumCost())){
                        player.setPassiveValue(player.getPassiveValue() + .00001);
                        player.setNumPentiums(player.getNumPentiums() + 1);
                        player.setBalanceCents(player.getBalanceCents() - shop.getPentiumCost());
                        shop.updateCosts();
                        numRigs++;
                    }
                }else if(tab == GCARD){
                    if(numRigs < numFacilities && gc.getInput().getMouseX() > 235 && gc.getInput().getMouseX() < 425 && gc.getInput().getMouseY() > 545 && gc.getInput().getMouseY() < 705 && player.getBalanceCents() >= shop.getQuadroCost()){
                        player.setPassiveValue(player.getPassiveValue() + .00005);
                        player.setNumQuadro(player.getNumQuadro() + 1);
                        player.setBalanceCents(player.getBalanceCents() - shop.getQuadroCost());
                        shop.updateCosts();
                        numRigs++;
                    }

                }else if(tab == MININGRIG){
                    if(numRigs < numFacilities && gc.getInput().getMouseX() > 755 && gc.getInput().getMouseX() < 1200 && gc.getInput().getMouseY() > 340 && gc.getInput().getMouseY() < 720 && player.getBalanceCents() >= shop.getASICCost()){
                        player.setPassiveValue(player.getPassiveValue() + .0015);
                        player.setNumASIC(player.getNumASIC() + 1);
                        player.setBalanceCents(player.getBalanceCents() - shop.getASICCost());
                        shop.updateCosts();
                        numRigs++;
                    }
                }else if(tab == STORAGEFACILITY){
                    if(gc.getInput().getMouseX() > 750 && gc.getInput().getMouseX() < 1190 && gc.getInput().getMouseY() > 350 && gc.getInput().getMouseY() < 750 && player.getBalanceCents() >= shop.getStorageCost()){
                        numFacilities += 10;
                        player.setNumStorage(player.getNumStorage() + 1);
                        player.setBalanceCents(player.getBalanceCents() - shop.getStorageCost());
                        shop.updateCosts();
                    }
                }
            }else if(scene == EXCHANGE){
                if(715 < gc.getInput().getMouseX() && gc.getInput().getMouseX() < 1230 && 440 < gc.getInput().getMouseY() && gc.getInput().getMouseY() < 660){
                    player.setBalanceCents(player.getBalanceCents() + (int)(player.getBalanceBitcoin() * exchangeRate));
                    player.setBalanceBitcoin(0);
                }
            }
        }
    }

    public void render(GameContainer gc, Renderer r) {
        r.drawImage(background, 0, 0);

        if(scene == CLICKER) {
            r.drawText(numRigs + "/" + numFacilities + " of free space", gc.getWidth()-500, gc.getHeight()/2 - 250, 0xffffffff);
            r.drawImage(coin, gc.getWidth()/2 - 150, gc.getHeight()/2 - 150);
        }else if(scene == STORE){
            r.drawText("Processors", gc.getWidth()/4-350, gc.getHeight()/2-275, 0xffffffff);
            r.drawText("Graphics Cards", gc.getWidth()/2-500, gc.getHeight()/2-275, 0xffffffff);
            r.drawText("Mining Rigs", gc.getWidth()/2+200, gc.getHeight()/2-275, 0xffffffff);
            r.drawText("Storage Facility", gc.getWidth()/2+550, gc.getHeight()/2-275, 0xffffffff);
            r.drawText("Shop", gc.getWidth()/2 - 100, gc.getHeight()/2 - 375, 0xffffffff, bigText);

            if(tab == CPU){
                r.drawImage(IntelPentiumA80501, gc.getWidth()/4-175/2, gc.getHeight()/2-87);
                r.drawText(shop.getPentiumCost()/100.0 + "", 430, gc.getHeight()/2+100, 0xffffffff);
            }else if (tab == GCARD){
                r.drawImage(NVIDIAQUADRO, gc.getWidth()/4-250, gc.getHeight()/2);
                r.drawText(shop.getQuadroCost()/100.0 + "", 280, gc.getHeight()/2 + 175, 0xffffffff);

            }else if (tab == MININGRIG){
                r.drawImage(ASICMiner, gc.getWidth()/2 - 300, gc.getHeight()/2 - 250);
                r.drawText(shop.getASICCost() / 100.0 + "", gc.getWidth()/2 - 100, gc.getHeight()/2 + 250, 0xffffffff);
            }else if (tab == STORAGEFACILITY){
                r.drawImage(facility, gc.getWidth()/2 - 220, gc.getHeight()/2 - 200);
                r.drawText(shop.getStorageCost() / 100.0 + "", gc.getWidth()/2 - 50, gc.getHeight()/2 + 275, 0xffffffff);
            }
        }else if(scene == EXCHANGE){
            r.drawText("Exchange", gc.getWidth()/2 - 175, gc.getHeight()/2 - 375, 0xffffffff, bigText);
            r.drawImage(bill, gc.getWidth()/2 - 250, gc.getHeight()/2);
            r.drawText("Exchange rate: " + dollarsDF.format(exchangeRate/100.0) + "", gc.getWidth()/2 - 210, gc.getHeight()/2 - 200, 0xffffffff);
        }

        r.drawText("B: " + bitcoinDF.format(player.getBalanceBitcoin()), gc.getWidth() / 2 - 100, 0, 0xffffffff);
        r.drawText("D: " + dollarsDF.format((double)player.getBalanceCents() / 100.0), gc.getWidth() / 2 - 100, 50, 0xffffffff);

        r.drawText("Store", gc.getWidth()-120, 0, 0xffffffff);
        r.drawText("Exchange", gc.getWidth() - 325, 0, 0xffffffff);
        r.drawText("Clicker", gc.getWidth() - 495, 0, 0xffffffff);

    }

    public static void main(String[] args) {
        GameManager gm = new GameManager();
        shop = new Store(gm);
        GameContainer gc = new GameContainer(gm);
        gc.start(gm);
    }

    public Player getPlayer() {
        return player;
    }
}

