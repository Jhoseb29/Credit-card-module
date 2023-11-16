package org.jala.university.utilities;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

@Getter
public class PaneCard extends JPanel {
    private final Color primaryColor;
    private final Color secondaryColor;
    private final Color insetsColor;
    private final int arcWidth = 20;
    private final int arcHeight = 20;
    public PaneCard(Color primaryColor, Color secondaryColor, Color insetsColor) {
        super();
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        this.insetsColor = insetsColor;
        setOpaque(false);
        setLayout(new GridLayout(3,5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Paint oldPaint = graphics2D.getPaint();
        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0,0,getWidth(),getHeight()-1, getArcWidth(), getArcHeight());
        graphics2D.clip(r2d);
        graphics2D.setPaint(new GradientPaint(0.0f, 0.0f, getPrimaryColor().darker(),0.0f, getHeight(), getSecondaryColor().darker()));
        graphics2D.fillRect(0,0,getWidth(),getHeight());

        graphics2D.setStroke(new BasicStroke(4f));
        graphics2D.setPaint(new GradientPaint(0.0f, 0.0f, getInsetsColor(),0.0f, getHeight(), getInsetsColor()));
        graphics2D.drawRoundRect(0, 0, getWidth()-2 , getHeight() -2,18,18);

        graphics2D.setPaint(oldPaint);
        super.paintComponent(g);
    }
}
