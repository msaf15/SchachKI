package Chess_1VS1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tile extends JPanel {
    private Tile owner = null;
    private Piece piece;
    private BufferedImage image;
    private BufferedImage canMoveto;
    private boolean clicked = false;
    private boolean moveablePosition = false;
    private boolean pieceOn = false;
    private boolean kingzone = false;
    private boolean check = false;
    private boolean allowed = true;
    public Tile() {
        this.piece = new Piece();
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.setLayout(new GridLayout(1,1));
    }
    public void updateTile(Piece piece) {
        this.piece = piece;
        this.image = piece.getImage();
        owner = null;
        canMoveto = null;
        clicked = false;
        pieceOn = true;
        moveablePosition = false;
        rePaint();
    }
    public void removePiece() {
        this.piece = new Piece();
        this.image = null;
        this.canMoveto = null;
        clicked = false;
        moveablePosition = false;
        pieceOn = false;
        owner = null;
        rePaint();
    }
    public void setMoveSource(Team team) {
        if (piece.getType() != Type.PIECE && !clicked && piece.getSide() == team) {
            try {
                canMoveto = ImageIO.read(new File("main/resources/greencircle.png"));
                rePaint();
                clicked = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            this.canMoveto = null;
            rePaint();
            clicked = false;
        }
    }
    public void setOwner(Tile tile) {
        owner = tile;
    }
    public Tile getOwner() {
        return owner;
    }
    public void setCanMoveto() {
        try {
            canMoveto = ImageIO.read(new File("main/resources/greencircle.png"));
            moveablePosition = true;
            rePaint();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void removeCircle() {
        canMoveto = null;
        moveablePosition = false;
        rePaint();
    }

    public void setMoveablePosition() {
        moveablePosition = true;
    }

    public void setMoveablePositionFalse() {
        moveablePosition = false;
    }

    public boolean isClicked() {
        return clicked;
    }
    public void setClicked() {
        clicked = !clicked;
    }
    public Piece getPiece() {
        return piece;
    }
    public void rePaint() {
        this.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (canMoveto != null) {
            g.drawImage(canMoveto,0,0,getWidth(),getHeight(),this);
        }
        if (image != null) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
    public boolean isMoveablePosition() {
        return moveablePosition;
    }

    public boolean isPieceOn() {
        return pieceOn;
    }

    public void setPieceOn(boolean pieceOn) {
        this.pieceOn = pieceOn;
    }

    public boolean isKingzone() {
        return kingzone;
    }

    public void setKingzone(boolean kingzone) {
        this.kingzone = kingzone;
    }

    public boolean isAllowed() {
        return allowed;
    }

    public void setAllowed(boolean allowed) {
        this.allowed = allowed;
    }

    public boolean isCheck() {
        return check;
    }

    public boolean hasOwner() {
        return owner != null;
    }

    public void setCheck(boolean check) {
        if (check) {
            try {
                canMoveto = ImageIO.read(new File("main/resources/redcircle.png"));
                rePaint();
            }
            catch (IOException e) {
                System.err.println("failed getting image");
            }
        }
        this.check = check;
    }
}
