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
 * @author ɽ����ѧandroidʵ�������ѳʡ����
 * 
 */
public class Mosquito {
	GameViewOne gameViewOne;
	GameViewTwo gameViewTwo;
	GameViewThree gameViewThree;
	GameViewFour gameViewFour;
	GameViewFive gameViewFive;
	GameViewSix gameViewSix;

	// �ܵ�����ͼ
	Bitmap monster1;
	// ���������ƶ���4��ͼ
	Bitmap Heng_R_1;
	Bitmap Heng_R_2;
	Bitmap Heng_R_3;
	Bitmap Heng_R_4;

	// ���������ƶ���4��ͼ
	Bitmap Heng_L_1;
	Bitmap Heng_L_2;
	Bitmap Heng_L_3;
	Bitmap Heng_L_4;

	// ���������ƶ���4��ͼ
	Bitmap Zong_D_1;
	Bitmap Zong_D_2;
	Bitmap Zong_D_3;
	Bitmap Zong_D_4;

	// ���������ƶ���4��ͼ
	Bitmap Zong_U_1;
	Bitmap Zong_U_2;
	Bitmap Zong_U_3;
	Bitmap Zong_U_4;
	// ��ע�߹��ĵ�ͼ
	private int[][] stepMap;
	// �Ƿ��ʼ��
	private boolean isInit = true;

	// ��־λ
	boolean flag = true;
	// ����
	int x;
	int y;
	float Sx = 0;
	float Sy = 0;
	// ����
	int front = 0;
	// ͼƬ���־λ
	int biaozhi = 0;
	// �ӳ�
	int delay = 0;
	// ����
	public int d = 0;
	// ��������
	private int endX, endY;

	public int dps;// ������
	public float speed = 5;// �ƶ��ٶ�
	private boolean isSpeed,// �����
			isHelmet;// �п���

	public int locX, locY;// λ��

	public int level;// �ȼ�(1~4)����ͬ���͵�����
	public int hp;// Ѫ��
	public int state;// ״̬
	public int score;

	private int[][] map;

	/**
	 * ��ʼ��һֻ����
	 * 
	 * @param level�ȼ�
	 *            (0~3)����ͬ���͵�����
	 * @param locX�����Xֵ
	 * @param locY�����Yֵ
	 */
	public Mosquito(int level, int locX, int locY, GameViewOne gameViewOne) {
		this.level = level;
		this.locX = 8 * locX;
		this.locY = 8 * locY;
		// ��ʼѪ�����������������ӵȼ��ļ��㹫ʽ
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
		// ��ʼѪ�����������������ӵȼ��ļ��㹫ʽ
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
		// ��ʼѪ�����������������ӵȼ��ļ��㹫ʽ
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
		// ��ʼѪ�����������������ӵȼ��ļ��㹫ʽ
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
		// ��ʼѪ�����������������ӵȼ��ļ��㹫ʽ
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
		// ��ʼѪ�����������������ӵȼ��ļ��㹫ʽ
		hp = level * 1;
		dps = level * 1;
		score = level * 50 + 50;

		state = 1;
		this.gameViewSix = gameViewSix;

		initBitmap();
		setmap(gameViewSix.map);
	}

	/**
	 * �����ƶ��ٶ�
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

		int[] retVal = new int[4];// [0]:x��λ��
									// [1]:y��λ��
									// [2]:״̬��1��������:-1��������2�������յ�
									// [3]:����0���ң� 1���£� 2���� 3����
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
	 * ��Ӧ���߹���
	 * 
	 * @return
	 */
	int deadAreaX = 0, deadAreaY = 0;
	boolean intoDead = false;

	private int setState() {
		int value = map[locY][locX];
		if (locX == endX && locY == endY) {
			state = 2;// ���յ�
			return state;
		}

		switch (value) {
		case -1:// ɱ���
			if (!isHelmet) {
				hp--;
				if (hp == 0) {
					state = -1;// ��������״̬
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
			state = 1;// ����״̬
		}
		return state;
	}

	// ��ʼ��ͼƬ
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

	// ���Ʒ���
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

	// ��־λ�趨����
	public void setFlag(boolean b) {
		this.flag = b;
	}

	// �ֱ��������ı任
	public void exchangeXY() {
		Sx = locX * speed;
		Sy = locY * speed;

	}

	// ��ȡӦ�������ĸ�ͼƬ��
	public Bitmap[] chooseBitmap(int i) {
		boolean b;

		Bitmap[] picture = new Bitmap[4];
		switch (i) {
		// ʹ�����ҵ�ͼƬ��
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
		// ʹ�����µ�ͼƬ��
		case 1:
			picture[0] = Zong_D_1;
			picture[1] = Zong_D_2;
			picture[2] = Zong_D_3;
			picture[3] = Zong_D_4;
			biaozhi = 1;
			break;
		// ʹ�����ϵ�ͼƬ��
		case 2:
			picture[0] = Zong_U_1;
			picture[1] = Zong_U_2;
			picture[2] = Zong_U_3;
			picture[3] = Zong_U_4;
			biaozhi = 2;
			break;
		// ʹ�������ͼƬ��
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
