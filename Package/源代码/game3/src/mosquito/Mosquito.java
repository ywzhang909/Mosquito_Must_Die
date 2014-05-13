package mosquito;

import com.publicClass.Constant;
import com.publicClass.R;
import com.userView.GameViewFive;
import com.userView.GameViewFour;
import com.userView.GameViewOne;
import com.userView.GameViewSix;
import com.userView.GameViewThree;
import com.userView.GameViewTwo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

/**
 * 
 * @author 山东大学android实验室刘昭呈、张昊
 * 
 */
public class Mosquito {
	GameViewOne gameViewOne;
	GameViewTwo gameViewTwo;
	GameViewThree gameViewThree;
	GameViewFour gameViewFour;
	GameViewFive gameViewFive;
	GameViewSix gameViewSix;

	// 总的行走图
	Bitmap monster1;
	// 横向向右移动的4张图
	Bitmap Heng_R_1;
	Bitmap Heng_R_2;
	Bitmap Heng_R_3;
	Bitmap Heng_R_4;

	// 横向向左移动的4张图
	Bitmap Heng_L_1;
	Bitmap Heng_L_2;
	Bitmap Heng_L_3;
	Bitmap Heng_L_4;

	// 纵向向下移动的4张图
	Bitmap Zong_D_1;
	Bitmap Zong_D_2;
	Bitmap Zong_D_3;
	Bitmap Zong_D_4;

	// 纵向向上移动的4张图
	Bitmap Zong_U_1;
	Bitmap Zong_U_2;
	Bitmap Zong_U_3;
	Bitmap Zong_U_4;
	// 标注走过的地图
	private int[][] stepMap;
	// 是否初始化
	private boolean isInit = true;

	// 标志位
	boolean flag = true;
	// 坐标
	int x;
	int y;
	float Sx = 0;
	float Sy = 0;
	// 面向
	int front = 0;
	// 图片组标志位
	int biaozhi = 0;
	// 延迟
	int delay = 0;
	// 方向
	public int d = 0;
	// 结束坐标
	private int endX, endY;

	public int dps;// 攻击力
	public float speed = 5;// 移动速度
	private boolean isSpeed,// 冲锋蚊
			isHelmet;// 有盔甲

	public int locX, locY;// 位置

	public int level;// 等级(1~4)，不同类型的蚊子
	public int hp;// 血量
	public int state;// 状态
	public int score;

	private int[][] map;

	/**
	 * 初始化一只蚊子
	 * 
	 * @param level等级
	 *            (0~3)，不同类型的蚊子
	 * @param locX进入点X值
	 * @param locY进入点Y值
	 */
	public Mosquito(int level, int locX, int locY, GameViewOne gameViewOne) {
		this.level = level;
		this.locX = 8 * locX;
		this.locY = 8 * locY;
		// 初始血量、攻击力关于蚊子等级的计算公式
		hp = level * 1;
		dps = level * 1;
		score = level * 50 + 50;

		state = 1;
		this.gameViewOne = gameViewOne;

		initBitmap();
		setmap(gameViewOne.map);
	}

	public Mosquito(int level, int locX, int locY, GameViewTwo gameViewTwo) {
		this.level = level;
		this.locX = 8 * locX;
		this.locY = 8 * locY;
		// 初始血量、攻击力关于蚊子等级的计算公式
		hp = level * 1;
		dps = level * 1;
		score = level * 50 + 50;

		state = 1;
		this.gameViewTwo = gameViewTwo;

		initBitmap();
		setmap(gameViewTwo.map);
	}

	public Mosquito(int level, int locX, int locY, GameViewThree gameViewThree) {
		this.level = level;
		this.locX = 8 * locX;
		this.locY = 8 * locY;
		// 初始血量、攻击力关于蚊子等级的计算公式
		hp = level * 1;
		dps = level * 1;
		score = level * 50 + 50;

		state = 1;
		this.gameViewThree = gameViewThree;

		initBitmap();
		setmap(gameViewThree.map);
	}

	public Mosquito(int level, int locX, int locY, GameViewFive gameViewFive) {
		this.level = level;
		this.locX = 8 * locX;
		this.locY = 8 * locY;
		// 初始血量、攻击力关于蚊子等级的计算公式
		hp = level * 1;
		dps = level * 1;
		score = level * 50 + 50;

		state = 1;
		this.gameViewFive = gameViewFive;

		initBitmap();
		setmap(gameViewFive.map);
	}

	public Mosquito(int level, int locX, int locY, GameViewFour gameViewFour) {
		this.level = level;
		this.locX = 8 * locX;
		this.locY = 8 * locY;
		// 初始血量、攻击力关于蚊子等级的计算公式
		hp = level * 1;
		dps = level * 1;
		score = level * 50 + 50;

		state = 1;
		this.gameViewFour = gameViewFour;

		initBitmap();
		setmap(gameViewFour.map);
	}

	public Mosquito(int level, int locX, int locY, GameViewSix gameViewSix) {
		this.level = level;
		this.locX = 8 * locX;
		this.locY = 8 * locY;
		// 初始血量、攻击力关于蚊子等级的计算公式
		hp = level * 1;
		dps = level * 1;
		score = level * 50 + 50;

		state = 1;
		this.gameViewSix = gameViewSix;

		initBitmap();
		setmap(gameViewSix.map);
	}

	/**
	 * 设置移动速度
	 * 
	 * @param x
	 *            (dp/50ms)
	 */
	public void setSpeed(int x) {
		speed = x;
	}

	/**
	 * refresh the logical map
	 * 
	 * @param map
	 */
	public void setmap(int[][] inputmap) {
		map = new int[8 * inputmap.length][8 * inputmap[0].length];

		for (int i = 0; i < inputmap.length - 1; i++) {
			for (int j = 0; j < inputmap[0].length - 1; j++) {
				map[8 * i][8 * j] = inputmap[i][j];
				for (int k = 1; k < 8; k++) {
					// ?????????????????
					map[8 * i + k][8 * j] = inputmap[i][j] * inputmap[i + 1][j];
					map[8 * i][8 * j + k] = inputmap[i][j] * inputmap[i][j + 1];
				}
			}
		}

		if (isInit) {
			stepMap = new int[map.length][map[0].length];
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if (map[i][j] == 0)
						stepMap[i][j] = 0;
					else
						stepMap[i][j] = 100;// ?????????????????
				}
			}
			stepMap[locY][locX]--;
			isInit = false;
		}

		else {
			for (int i = 1; i < map.length; i++) {
				for (int j = 1; j < map[0].length; j++) {
					if (map[i][j] == 100) {
						// ?????????????????
						stepMap[i][j] = map[i - 1][j] > map[i][j - 1] ? map[i - 1][j]
								: map[i][j - 1];
					}
				}
			}
		}

	}

	public void setEnd(int x, int y) {
		endX = 8 * x;
		endY = 8 * y;
	}

	public int[] move() {

		int[] retVal = new int[4];// [0]:x轴位置
									// [1]:y轴位置
									// [2]:状态：1：正常；:-1：死亡；2：到达终点
									// [3]:方向：0：右； 1：下； 2：左； 3：上
		int[][] direct = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };

		int x = 0, y = 0;
		int temp[][] = new int[4][3];
		// read the value
		for (int i = 0; i <= 3; i++) {
			x = locX + direct[(d + i) % 4][0];
			y = locY + direct[(d + i) % 4][1];
			if (setState() == 2)
				temp[i][0] = temp[i][1] = temp[i][2] = 0;
			else {
				temp[i][0] = stepMap[y][x];
				temp[i][1] = direct[(d + i) % 4][0];
				temp[i][2] = direct[(d + i) % 4][1];
			}

		}
		// compare the minimize
		int ahead[] = { 0, 0, 0 };
		for (int i = 0; i <= 3; i++) {
			int dx = temp[i][1];
			int dy = temp[i][2];
			if (temp[i][0] > ahead[0] && map[locY + dy][locX + dx] != 0
					&& state != 2 && state != -1) {
				ahead = temp[i];
				d = (d + i) % 4;
			}
		}
		// move
		x = locX + ahead[1];
		y = locY + ahead[2];
		locX = x;
		locY = y;

		// if(x)
		stepMap[y][x]--;

		retVal[0] = x;
		retVal[1] = y;
		retVal[2] = setState();
		// retVal[3] = d % 4;
		int dx = ahead[1], dy = ahead[2];
		if (dx == 1 && dy == 0)
			retVal[3] = 0;
		if (dx == 0 && dy == 1)
			retVal[3] = 1;
		if (dx == -1 && dy == 0)
			retVal[3] = 2;
		if (dx == 0 && dy == -1)
			retVal[3] = 3;

		return retVal;
	}

	/**
	 * 对应道具规则
	 * 
	 * @return
	 */
	int deadAreaX = 0, deadAreaY = 0;
	boolean intoDead = false;

	private int setState() {
		int value = map[locY][locX];
		if (locX == endX && locY == endY) {
			state = 2;// 到终点
			return state;
		}

		switch (value) {
		case -1:// 杀虫剂
			if (!isHelmet) {
				hp--;
				if (hp == 0) {
					state = -1;// 毒死死亡状态
					// dead();
				}
			} else {
				if (deadAreaX != locX / 8 && deadAreaY != locY / 8) {
					if (!intoDead) {
						deadAreaX = locX / 8;
						deadAreaY = locY / 8;
						intoDead = true;
					} else
						isHelmet = false;
				}
			}
			break;

		default:
			state = 1;// 正常状态
		}
		return state;
	}

	// 初始化图片
	public void initBitmap() {
		if (isSpeed) {
			if (gameViewOne != null)
				monster1 = BitmapFactory.decodeResource(
						gameViewOne.getResources(), R.drawable.move1);

			if (gameViewTwo != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewTwo.getResources(), R.drawable.move1);
			}

			if (gameViewThree != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewThree.getResources(), R.drawable.move1);
			}
			if (gameViewFour != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewFour.getResources(), R.drawable.move1);
			}
			if (gameViewFive != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewFive.getResources(), R.drawable.move1);
			}
			if (gameViewSix != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewSix.getResources(), R.drawable.move1);
			}
		}
		if (isHelmet) {
			if (gameViewOne != null)
				monster1 = BitmapFactory.decodeResource(
						gameViewOne.getResources(), R.drawable.move2);

			if (gameViewTwo != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewTwo.getResources(), R.drawable.move2);
			}

			if (gameViewThree != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewThree.getResources(), R.drawable.move2);
			}
			if (gameViewFour != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewFour.getResources(), R.drawable.move2);
			}
			if (gameViewFive != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewFive.getResources(), R.drawable.move2);
			}
			if (gameViewSix != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewSix.getResources(), R.drawable.move2);
			}
		}
		if (!isHelmet && !isSpeed) {
			Log.e("move normal", "");
			if (gameViewOne != null)
				monster1 = BitmapFactory.decodeResource(
						gameViewOne.getResources(), R.drawable.move);

			if (gameViewTwo != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewTwo.getResources(), R.drawable.move);
			}

			if (gameViewThree != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewThree.getResources(), R.drawable.move);
			}
			if (gameViewFour != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewFour.getResources(), R.drawable.move);
			}
			if (gameViewFive != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewFive.getResources(), R.drawable.move);
			}
			if (gameViewSix != null) {
				monster1 = BitmapFactory.decodeResource(
						gameViewSix.getResources(), R.drawable.move);
			}
		}

		Zong_D_1 = Bitmap.createBitmap(monster1, 0, 0, monster1.getWidth() / 4,
				monster1.getHeight() / 4);
		Zong_D_2 = Bitmap.createBitmap(monster1, monster1.getWidth() / 4, 0,
				monster1.getWidth() / 4, monster1.getHeight() / 4);
		Zong_D_3 = Bitmap.createBitmap(monster1, monster1.getWidth() / 2, 0,
				monster1.getWidth() / 4, monster1.getHeight() / 4);
		Zong_D_4 = Bitmap.createBitmap(monster1, monster1.getWidth() * 3 / 4,
				0, monster1.getWidth() / 4, monster1.getHeight() / 4);

		Heng_R_1 = Bitmap.createBitmap(monster1, 0, monster1.getHeight() / 2,
				monster1.getWidth() / 4, monster1.getHeight() / 4);
		Heng_R_2 = Bitmap.createBitmap(monster1, monster1.getWidth() / 4,
				monster1.getHeight() / 2, monster1.getWidth() / 4,
				monster1.getHeight() / 4);
		Heng_R_3 = Bitmap.createBitmap(monster1, monster1.getWidth() / 2,
				monster1.getHeight() / 2, monster1.getWidth() / 4,
				monster1.getHeight() / 4);
		Heng_R_4 = Bitmap.createBitmap(monster1, monster1.getWidth() * 3 / 4,
				monster1.getHeight() / 2, monster1.getWidth() / 4,
				monster1.getHeight() / 4);

		Heng_L_1 = Bitmap.createBitmap(monster1, 0, monster1.getHeight() / 4,
				monster1.getWidth() / 4, monster1.getHeight() / 4);
		Heng_L_2 = Bitmap.createBitmap(monster1, monster1.getWidth() / 4,
				monster1.getHeight() / 4, monster1.getWidth() / 4,
				monster1.getHeight() / 4);
		Heng_L_3 = Bitmap.createBitmap(monster1, monster1.getWidth() / 2,
				monster1.getHeight() / 4, monster1.getWidth() / 4,
				monster1.getHeight() / 4);
		Heng_L_4 = Bitmap.createBitmap(monster1, monster1.getWidth() * 3 / 4,
				monster1.getHeight() / 4, monster1.getWidth() / 4,
				monster1.getHeight() / 4);

		Zong_U_1 = Bitmap.createBitmap(monster1, 0,
				monster1.getHeight() * 3 / 4, monster1.getWidth() / 4,
				monster1.getHeight() / 4);
		Zong_U_2 = Bitmap.createBitmap(monster1, monster1.getWidth() / 4,
				monster1.getHeight() * 3 / 4, monster1.getWidth() / 4,
				monster1.getHeight() / 4);
		Zong_U_3 = Bitmap.createBitmap(monster1, monster1.getWidth() / 2,
				monster1.getHeight() * 3 / 4, monster1.getWidth() / 4,
				monster1.getHeight() / 4);
		Zong_U_4 = Bitmap.createBitmap(monster1, monster1.getWidth() * 3 / 4,
				monster1.getHeight() * 3 / 4, monster1.getWidth() / 4,
				monster1.getHeight() / 4);

	}

	// 绘制方法
	public void drawself(Canvas canvas, int x, int y, Paint paint, Mosquito m) {
		Bitmap[] move = new Bitmap[4];
		exchangeXY();
		int[] i = m.move();
		if (isSpeed)
			i = m.move();
		front = i[3];
		switch (front) {
		case 0:
			move = chooseBitmap(0);
			break;
		case 1:
			move = chooseBitmap(1);
			break;
		case 2:
			move = chooseBitmap(3);
			break;
		case 3:
			move = chooseBitmap(2);
			break;
		}

		if (delay % 4 == 0) {
			canvas.drawBitmap(move[0], Sx, Sy, paint);
		}
		if (delay % 4 == 1) {
			canvas.drawBitmap(move[1], Sx, Sy, paint);
		}
		if (delay % 4 == 2) {
			canvas.drawBitmap(move[2], Sx, Sy, paint);
		}
		if (delay % 4 == 3) {
			canvas.drawBitmap(move[3], Sx, Sy, paint);
		}
		canvas.drawRect(Sx, Sy + 5, Sx + 5 * hp, Sy, paint);
		delay++;
	}

	// public void drawBlood()

	// 标志位设定方法
	public void setFlag(boolean b) {
		this.flag = b;
	}

	// 分辨率与矩阵的变换
	public void exchangeXY() {
		Sx = locX * speed;
		Sy = locY * speed;

	}

	// 获取应该是用哪个图片组
	public Bitmap[] chooseBitmap(int i) {
		boolean b;

		Bitmap[] picture = new Bitmap[4];
		switch (i) {
		// 使用向右的图片组
		case 0:
			picture[0] = Heng_R_1;
			if (picture[0] == null)
				b = false;
			else
				b = true;
			picture[1] = Heng_R_2;
			picture[2] = Heng_R_3;
			picture[3] = Heng_R_4;
			biaozhi = 0;
			break;
		// 使用向下的图片组
		case 1:
			picture[0] = Zong_D_1;
			picture[1] = Zong_D_2;
			picture[2] = Zong_D_3;
			picture[3] = Zong_D_4;
			biaozhi = 1;
			break;
		// 使用向上的图片组
		case 2:
			picture[0] = Zong_U_1;
			picture[1] = Zong_U_2;
			picture[2] = Zong_U_3;
			picture[3] = Zong_U_4;
			biaozhi = 2;
			break;
		// 使用向左的图片组
		case 3:
			picture[0] = Heng_L_1;
			picture[1] = Heng_L_2;
			picture[2] = Heng_L_3;
			picture[3] = Heng_L_4;
			biaozhi = 3;
			break;
		}

		return picture;
	}

	public void setSpeed(boolean isSpeed) {
		this.isSpeed = isSpeed;
		initBitmap();
		this.score += 50;
	}

	public void setHelmet(boolean isHelmet) {
		this.isHelmet = isHelmet;
		initBitmap();
		this.score += 50;
	}
}
