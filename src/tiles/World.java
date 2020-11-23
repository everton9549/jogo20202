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
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0},
		{0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0},
		{0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 0, 0, 1, 1, 0, 1, 1, 0},
		{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0},
		{1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
		{0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
		{0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
		{0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0},
		{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
		{0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0},
		{0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0},
		{0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 1, 1, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
		
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
			//zombiesAndBullets.add(new Zombie(new Vector2D(2000, 1000), 2, player));
			
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
		player = new Player(new Vector2D(128, 640), 4, this);
		timer.start();
		
		
		// trees
		
		//around
		obstacles.add(new Tree(handler, new Vector2D(0, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 768), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 896), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 1024), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 1152), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(128, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(256, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(384, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(512, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(640, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(768, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(896, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1024, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1280, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1408, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1536, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1664, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1792, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1920, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2048, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2176, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2304, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2432, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2688, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2816, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3072, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3200, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3456, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3584, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3840, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3968, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4096, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4224, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4352, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4480, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4608, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4736, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4864, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4992, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5248, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5376, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5632, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5760, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5888, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6016, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6144, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6272, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6400, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6528, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6656, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6784, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6912, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7040, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7168, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7424, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7552, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7808, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7936, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8064, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8192, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8320, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8448, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8576, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8832, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8960, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9088, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(128, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(256, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(384, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(512, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(640, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(768, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(896, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1024, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1280, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1408, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1536, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1664, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1792, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1920, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2048, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2176, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2304, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2432, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2688, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2816, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3072, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3200, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3456, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3584, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3840, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3968, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4096, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4224, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4352, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4480, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4608, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4736, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4864, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4992, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5248, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5376, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5632, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5760, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5888, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6016, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6144, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6272, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6400, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6528, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6656, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6784, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6912, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7040, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7168, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7424, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7552, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7808, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7936, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8064, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8192, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8320, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8448, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8576, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8832, 1792), Assets.tree));


		
		//rall de entrada
		obstacles.add(new Tree(handler, new Vector2D(256, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(128, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(256, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 768), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(256, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(256, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(256, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(384, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(512, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(640, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(768, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(896, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1024, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(0, 896), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(128, 896), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(256, 896), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(256, 1024), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(256, 1152), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(256, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(384, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(512, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(640, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(768, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(896, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1024, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 1152), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 1024), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 896), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 768), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(1152, 1408), Assets.tree));
		
		//bloco 1 etec
		obstacles.add(new Tree(handler, new Vector2D(2560, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2688, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2816, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3072, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3200, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3456, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3584, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 1152), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2560, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2688, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2816, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(2944, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3072, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3200, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3328, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3456, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3584, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 1152), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(3712, 1664), Assets.tree));
		
		//bloco 2 etec
		obstacles.add(new Tree(handler, new Vector2D(4736, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4736, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4864, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4992, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5248, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5376, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5632, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5760, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5888, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5888, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4736, 1152), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4736, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4864, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(4992, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5120, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5248, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5376, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5504, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5632, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5760, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5888, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(5888, 1152), Assets.tree));
		
		
		//bloco 1 fatec
		obstacles.add(new Tree(handler, new Vector2D(6912, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6912, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7040, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7168, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7424, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7552, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7808, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7936, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8064, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8064, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6912, 1152), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(6912, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7040, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7168, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7296, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7424, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7552, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7680, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7808, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(7936, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8064, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8064, 1152), Assets.tree));
		
		
		//bloco 2 fatec
		obstacles.add(new Tree(handler, new Vector2D(9088, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9088, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9216, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9344, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9472, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9472, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9472, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9472, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9472, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9600, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9728, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 0), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 128), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 256), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 384), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 512), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 640), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 768), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 896), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 1024), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 1152), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 1920), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 2048), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9728, 2048), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9600, 2048), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9728, 2176), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9728, 2304), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9728, 2432), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9728, 2560), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9728, 2688), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9728, 2816), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9728, 2944), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9728, 3072), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 2176), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 2304), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 2432), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 2560), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 2688), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 2816), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 2944), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9856, 3072), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9600, 3072), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9472, 3072), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9344, 3072), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9216, 3072), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9088, 3072), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8960, 3072), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8832, 3072), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 3072), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 2944), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 2816), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 2688), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 2560), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 2432), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 2304), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 2176), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8704, 2048), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8832, 2048), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(8960, 2048), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9088, 2048), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9216, 2048), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9344, 2048), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9344, 1920), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9344, 1792), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9344, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9472, 1664), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9472, 1536), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9472, 1408), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9472, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9344, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9216, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9088, 1280), Assets.tree));
		obstacles.add(new Tree(handler, new Vector2D(9088, 1152), Assets.tree));
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
	
