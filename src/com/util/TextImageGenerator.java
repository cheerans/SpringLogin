package com.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class TextImageGenerator {
	
	public static void createTextImage(String fileName,String secretCode){

		// create the font you wish to use
		Font font = new Font("Alaska", Font.BOLD, 26);

		// create the FontRenderContext object which helps us to measure the
		// text
		FontRenderContext frc = new FontRenderContext(null, true, true);

		// get the height and width of the text
		Rectangle2D bounds = font.getStringBounds(secretCode, frc);
		int w = (int) bounds.getWidth();
		int h = (int) bounds.getHeight();

		// create a BufferedImage object
		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);

		// calling createGraphics() to get the Graphics2D
		Graphics2D g = image.createGraphics();

		// set color and other parameters
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);
		g.setColor(Color.MAGENTA);
		g.setFont(font);

		g.drawString(secretCode, (float) bounds.getX(), (float) -bounds.getY());

		// releasing resources
		g.dispose();

		File outputfile = new File(fileName);
		
		// creating the file
		try {
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}