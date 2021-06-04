import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {
    private final List<Cell> cells;
    private  final List <Colony> colonies;
    public final PlayerInfo leftPlayer, rightPlayer;
    private long lastTimeOfGeneratingCell;
    private long lastTimeOfIncreaseHealth;
    private long lastTimeOfMoveCell;
    private long lastTimeOfDecrease;
    private long lastTimeOfGeneratingColony;

    public GameBoard () {
        leftPlayer = new PlayerInfo('L');
        rightPlayer = new PlayerInfo('R');
        cells = new ArrayList<>();
        colonies = new ArrayList<>();
    }

    public boolean updateGame(long time, Pane pane) {
        if (time - lastTimeOfGeneratingCell >= GameSettings.TimeBetweenCellGenerating * 1_000_000_000) {
            Cell cell = new Cell ();
            cells.add(cell);
            lastTimeOfGeneratingCell = time;
        }
        if (time - lastTimeOfGeneratingColony >= GameSettings.TimeBetweenColonyGeneration * 1_000_000_000) {
            Colony colony = new Colony();
            colonies.add(colony);
            lastTimeOfGeneratingColony = time;
        }
        if (time - lastTimeOfMoveCell >= 15_000_000) {
            double timeBetween = ((double)time - (double)lastTimeOfMoveCell)/1_000_000_000;
            for (Cell cell : cells) {
                cell.move(timeBetween);
                cell.draw(pane);
            }
            for (Colony colony : colonies){
                colony.move(timeBetween);
                colony.draw(pane);
            }
            cells.removeIf(cell -> cell.getY() > GameSettings.WindowHeight - GameSettings.WidthOfTankBorder);
            cells.removeIf(cell -> cell.getCurrentSize() <= 0);
            cells.removeIf(cell -> cell.getCurrentHp() <= 0);
            lastTimeOfMoveCell = time;
        }

        if (time - lastTimeOfDecrease >= 1_000_000_000 * GameSettings.Interval) {
            for (Cell cell : cells) {
                cell.resize();
            }
            for (Colony colony : colonies){
                colony.resize();
            }
            for (Bullet bullet : leftPlayer.getTank().getBullets()) {
                bullet.resize();
                if (bullet.getCurrentSize() <= 0) {
                    bullet.erase(pane);
                    leftPlayer.getTank().removeBullet(bullet);
                    break;
                }
            }
            for (Bullet bullet : rightPlayer.getTank().getBullets()) {
                bullet.resize();
                if (bullet.getCurrentSize() <= 0) {
                    bullet.erase(pane);
                    rightPlayer.getTank().removeBullet(bullet);
                    break;
                }
            }
            lastTimeOfDecrease = time;
            GameSettings.CellVelocity += GameSettings.CellVelocityIncrease;
            GameSettings.BulletVelocity += GameSettings.BulletVelocityIncrease;
        }

        if (checkAllBulletCollisions(leftPlayer, pane) || checkAllBulletCollisions(rightPlayer, pane)) {
            return true;
        }

        if (time - lastTimeOfIncreaseHealth >= GameSettings.CellRegenerationInterval * 1_000_000_000) {
            for(Cell cell : cells){
                cell.regenerate();
            }
            lastTimeOfIncreaseHealth = time;
        }
        leftPlayer.drawScore(pane, 'L');
        rightPlayer.drawScore(pane, 'R');
        return false;
    }

    public Cell cellCollision (Bullet bullet, List<Cell> cells) {
        for (Cell cell : cells) {
            double cellCenterX = cell.getX();
            double cellCenterY = cell.getY() + cell.getCurrentSize()/2;
            if (Math.abs(bullet.getX() - cellCenterX) < (bullet.getCurrentSize() + cell.getCurrentSize()) / 2 &&
                Math.abs(bullet.getY() - cellCenterY) < (bullet.getCurrentSize() + cell.getCurrentSize()) / 2) {
                return cell;
            }
        }
        return null;
    }

    public boolean bombCollision (Bullet bullet) {
        boolean yCondition = bullet.getY() >= GameSettings.WindowHeight - GameSettings.WidthOfTankBorder - Bomb.height;
        boolean xCondition = bullet.getX() >= GameSettings.WindowWidth/2 - Bomb.width/2 && bullet.getX() <= GameSettings.WindowWidth/2 + Bomb.width/2;
        return yCondition && xCondition;
    }

    public void removeAllCells(){
        cells.clear();
        colonies.clear();
    }

    private boolean checkAllBulletCollisions (PlayerInfo player, Pane pane) {
        var tank = player.getTank();
        for (Bullet bullet : tank.getBullets()) {
            var cell = cellCollision(bullet, cells);
            if (cell != null) {
                if (cell.getCurrentHp() > 0) {
                    cell.getDamaged();
                    if(cell.getCurrentHp() == 0)
                        player.increaseScore(cell.getInitialHp());
                }
                bullet.erase(pane);
                tank.removeBullet(bullet);
                break;
            }
            for (Colony colony : colonies) {
                cell = cellCollision(bullet, colony.getCells());
                if (cell != null) {
                    if (cell.getCurrentHp() > 0) {
                        cell.getDamaged();
                        if(cell.getCurrentHp() == 0 && colony.getCells().size() == 0)
                            player.increaseScore(cell.getInitialHp());
                    }
                    bullet.erase(pane);
                    tank.removeBullet(bullet);
                    break;
                }
            }
            if (bombCollision(bullet)) {
                bullet.erase(pane);
                tank.removeBullet(bullet);
                if (Bomb.fatalCollision(bullet)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateTankPosition (Pane layerPane) {
        //Left Player
        leftPlayer.getTank().draw(layerPane);
        if (Controller.leftMoveUpPressed && !Controller.leftMoveDownPressed) {
            leftPlayer.getTank().move(GameSettings.LeftPlayerMoveUp);
        }
        if (Controller.leftMoveDownPressed && !Controller.leftMoveUpPressed) {
            leftPlayer.getTank().move(GameSettings.LeftPlayerMoveDown);
        }
        if (Controller.leftBarrelDownPressed && !Controller.leftBarrelUpPressed) {
            leftPlayer.getTank().rotateBarrel(GameSettings.LeftPlayerBarrelDown);
        }
        if (Controller.leftBarrelUpPressed && !Controller.leftBarrelDownPressed) {
            leftPlayer.getTank().rotateBarrel(GameSettings.LeftPlayerBarrelUp);
        }
        if (Controller.leftPlayerShootPressed && Controller.leftPlayerAllowedToShoot) {
            leftPlayer.getTank().shoot();
            Controller.leftPlayerAllowedToShoot = false;
        }
        //Right Player
        rightPlayer.getTank().draw(layerPane);
        if (Controller.rightMoveUpPressed && !Controller.rightMoveDownPressed) {
            rightPlayer.getTank().move(GameSettings.RightPlayerMoveUp);
        }
        if (Controller.rightMoveDownPressed && !Controller.rightMoveUpPressed) {
            rightPlayer.getTank().move(GameSettings.RightPlayerMoveDown);
        }
        if (Controller.rightBarrelUpPressed && !Controller.rightBarrelDownPressed) {
            rightPlayer.getTank().rotateBarrel(GameSettings.RightPlayerBarrelUp);
        }
        if (Controller.rightBarrelDownPressed && !Controller.rightBarrelUpPressed) {
            rightPlayer.getTank().rotateBarrel(GameSettings.RightPlayerBarrelDown);
        }
        if (Controller.rightPlayerShootPressed && Controller.rightPlayerAllowedToShoot) {
            rightPlayer.getTank().shoot();
            Controller.rightPlayerAllowedToShoot = false;
        }
    }
}