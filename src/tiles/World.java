package tiles;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import blood.Blood;
import entities.Entity;
import entities.Tree;
import entities.Vector2D;
import entities.creatures.Player;
import entities.creatures.Zombie;
import graphics.Assets;
import main.Handler;
import main.Window;
import particles.Particle;

public class World {
	
	public static int WIDTH;

	public static int HEIGHT;
	
	
	private int[][] tiles = new int[][]{
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
		{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
		{0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
		{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	};
	
	private Handler handler;
	private ArrayList<Entity> zombiesAndBullets;
	private ArrayList<Particle> particles;
	private ArrayList<Entity> obstacles;
	private ArrayList<Blood> bloodSplats;
	
	
	private Player player;
	
	private Timer timer = new Timer(2000, new ActionListener()
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			int x = (int)(Math.random()*1200); // use these to spawn zombies at random position
			int y = (int)(Math.random()*1000);
			zombiesAndBullets.add(new Zombie(new Vector2D(1000, 1000), 2, player));
			zombiesAndBullets.add(new Zombie(new Vector2D(2000, 1000), 2, player));
			
		}
		
	});
	
	public World(Handler handler){
		this.handler = handler;
		WIDTH = tiles[0].length;
		HEIGHT = tiles.length;
		zombiesAndBullets = new ArrayList<Entity>();
		particles = new ArrayList<Particle>();
		obstacles = new ArrayList<Entity>();
		bloodSplats = new ArrayList<Blood>();
		player = new Player(new Vector2D(500, 500), 4, this);
		timer.start();
		
		// trees
		
		obstacles.add(new Tree(handler, new Vector2D(260, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(130, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(260, 370), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 630), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 760), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(260, 240), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(260, 110), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(260, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(390, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(520, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(650, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(780, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(910, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1040, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1170, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1170, 130), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 890), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(130, 890), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(260, 890), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(260, 1020), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(260, 1150), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(260, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(390, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(520, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(650, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(780, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(910, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1040, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1170, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1170, 1150), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1170, 1020), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1170, 890), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1170, 760), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1170, 630), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1170, 500), Assets.tree));
		
		//bloco 1 etec
		obstacles.add(new Tree(handler, new Vector2D(2560, 630), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2690, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2820, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2950, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2950, 370), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2950, 240), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2950, 110), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2950, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3080, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3210, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3340, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3340, 110), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3340, 240), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3340, 370), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3340, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3470, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3600, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3730, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3730, 630), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 1150), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2690, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2820, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2950, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2950, 1410), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2950, 1540), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2950, 1670), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2950, 1800), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3080, 1800), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3210, 1800), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3340, 1800), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3340, 1670), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3340, 1540), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3340, 1410), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3340, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3470, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3600, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3730, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3730, 1150), Assets.tree));
		
		//bloco 2 etec
		obstacles.add(new Tree(handler, new Vector2D(4740, 630), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4740, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4870, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5000, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5130, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5130, 370), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5130, 240), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5130, 110), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5130, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5260, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5390, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5520, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5520, 110), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5520, 240), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5520, 370), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5520, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5650, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5780, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5910, 500), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5910, 630), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4740, 1150), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4740, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4870, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5000, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5130, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5130, 1410), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5130, 1540), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5130, 1670), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5130, 1800), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5260, 1800), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5390, 1800), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5520, 1800), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5520, 1670), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5520, 1540), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5520, 1410), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5520, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5650, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5780, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5910, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5910, 1150), Assets.tree));
	}
	
	
	public void update(){
		player.update();
		for(int i = 0; i < zombiesAndBullets.size(); i++)
			zombiesAndBullets.get(i).update();
		for(int i = 0; i < particles.size(); i++)
			if(particles.get(i).update())
				particles.remove(i);
		for(int i = 0; i < obstacles.size(); i++)
			obstacles.get(i).update();
		
	}
	
	public void render(Graphics g){
		int x = (int) handler.getWindow().getCamera().getOffset().getX();
		int y = (int) handler.getWindow().getCamera().getOffset().getY();
		
		int xStart = (int)Math.max(0, x/Tile.TILESIZE);
		int yStart = (int)Math.max(0, y/Tile.TILESIZE);
		int xEnd = (int)Math.min(WIDTH, (x + Window.WIDTH)/Tile.TILESIZE + 1);
		int yEnd = (int)Math.min(HEIGHT, (y + Window.WIDTH)/Tile.TILESIZE + 1);
		
		for(int i = yStart; i < yEnd; i++){
			for(int j = xStart; j < xEnd; j++){
				getTile(i, j).render(g, new Vector2D(j*Tile.TILESIZE - x,
						i*Tile.TILESIZE - y));
			}
		}
		
		for(int i = 0; i < bloodSplats.size(); i++)
			bloodSplats.get(i).render(g);
		
		
		player.render(g);
		
		for(int i = 0; i < particles.size(); i++)
			particles.get(i).render(g);
		
		for(int i = 0; i < obstacles.size(); i++)
			obstacles.get(i).render(g);
		
		
		for(int i = 0; i < zombiesAndBullets.size(); i++)
			zombiesAndBullets.get(i).render(g);
		
		player.getCurrentGun().render(g);
		
	}
	public Tile getTile(int x, int y){
		Tile tile = Tile.tiles[tiles[x][y]];
		return tile;
	}
	
	public Handler getHandler(){
		return handler;
	}
	public ArrayList<Entity> getZombiesAndBullets(){
		return zombiesAndBullets;
	}
	public ArrayList<Particle> getParticles(){
		return particles;
	}
	public ArrayList<Entity> getObstacles(){
		return obstacles;
	}
	public ArrayList<Blood> getBloodSplats(){
		return bloodSplats;
	}
}
	
