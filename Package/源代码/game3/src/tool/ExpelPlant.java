package tool;

import utility.PicScaleHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.publicClass.Constant;
import com.publicClass.R;
import com.userView.GameViewFour;
import com.userView.GameViewOne;
import com.userView.GameViewSix;
import com.userView.GameViewThree;
import com.userView.GameViewTwo;

public class ExpelPlant {

	/**
	 * @���ò���
	 */
	GameViewOne gameViewOne;
	GameViewTwo gameViewTwo;
	GameViewThree gameViewThree;
	GameViewSix gameViewSix;

	public int level; // �ȼ�
	public int lasttime = 100; // ����ʱ��
	private int i;// ���õ����ھ������
	private int j;// ���õ����ھ������
	private Bitmap[] levelOneBitmap = new Bitmap[4];// һ�����ò�ͼƬ
	private Bitmap[] levelTwoBitmap = new Bitmap[4];// �������ò�ͼƬ
	public int state = 0;

	/*
	 * ���췽��
	 */
	// ��һ�ع��췽��
	public ExpelPlant(GameViewOne gameViewOne, int level, int pressX, int pressY) {

		this.gameViewOne = gameViewOne;
		this.level = level;
		this.lasttime = lasttime * level;
		j = pressX / Constant.G_SIZE;
		i = pressY / Constant.G_SIZE;

		initBitmap();
	}

	// �ڶ��ع��췽��
	public ExpelPlant(GameViewTwo gameViewTwo, int level, int pressX, int pressY) {
		this.gameViewTwo = gameViewTwo;
		this.level = level;
		this.lasttime = lasttime * level;
		j = pressX / Constant.G_SIZE;
		i = pressY / Constant.G_SIZE;

		initBitmap();
	}

	// �����ع��췽��
	public ExpelPlant(GameViewThree gameViewThree, int level, int pressX,
			int pressY) {
		this.gameViewThree = gameViewThree;
		this.level = level;
		this.lasttime = lasttime * level;
		j = pressX / Constant.G_SIZE;
		i = pressY / Constant.G_SIZE;

		initBitmap();
	}

	public ExpelPlant(GameViewSix gameViewSix, int level, int pressX, int pressY) {
		this.gameViewSix = gameViewSix;
		this.level = level;
		this.lasttime = lasttime * level;
		j = pressX / Constant.G_SIZE;
		i = pressY / Constant.G_SIZE;

		initBitmap();
	}

	// �������õķ��������ر仯��ĵ�ͼ
	public int[][] effect(int[][] map) {

		if (map[i][j] == 1)
			map[i][j] = 0;

		return map;

	}

	// ���ò���ʧ�ķ���
	public int[][] reMap(int[][] map) {

		// ����������״̬
		if (map[i][j] == 0)
			map[i][j] = 1;
		return map;
	}

	// �����Լ�
	public void drawSelf(Canvas canvas, Paint paint) {

		int onX = j * 40;
		int onY = i * 40;
		canvas.drawBitmap(levelOneBitmap[state], onX, onY, paint);

	}

	// �жϹ��ߵķ���λ���Ƿ���Ч
	public boolean haveRight(int[][] map) {
		if (gameViewOne != null) {
			int count = gameViewOne.Wlist.size();
			int[][] storeMosLocat = new int[count][2];
			for (int i = 0; i < count; i++) {
				if (gameViewOne.Wlist.get(i) != null) {
					storeMosLocat[i][0] = gameViewOne.Wlist.get(i).locY / 8;
					storeMosLocat[i][1] = gameViewOne.Wlist.get(i).locX / 8;
				}
			}

			// ������������ӵ����ܣ�����false
			if (meetMos(i, j, storeMosLocat)) {
				Log.i("meet", "" + meetMos(i, j, storeMosLocat));
				return false;
			}
		}

		else if (gameViewTwo != null) {
			int count = gameViewTwo.Wlist.size();
			int[][] storeMosLocat = new int[count][2];
			for (int i = 0; i < count; i++) {
				if (gameViewTwo.Wlist.get(i) != null) {
					storeMosLocat[i][0] = gameViewTwo.Wlist.get(i).locY / 8;
					storeMosLocat[i][1] = gameViewTwo.Wlist.get(i).locX / 8;
				}
			}

			// ������������ӵ����ܣ�����false
			if (meetMos(i, j, storeMosLocat)) {
				Log.i("meet", "" + meetMos(i, j, storeMosLocat));
				return false;
			}

		} else if (gameViewThree != null) {
			int count = gameViewThree.Wlist.size();
			int[][] storeMosLocat = new int[count][2];
			for (int i = 0; i < count; i++) {
				if (gameViewThree.Wlist.get(i) != null) {
					storeMosLocat[i][0] = gameViewThree.Wlist.get(i).locY / 8;
					storeMosLocat[i][1] = gameViewThree.Wlist.get(i).locX / 8;
				}
			}

			// ������������ӵ����ܣ�����false
			if (meetMos(i, j, storeMosLocat)) {
				Log.i("meet", "" + meetMos(i, j, storeMosLocat));
				return false;
			}

		} else if (gameViewSix != null) {
			int count = gameViewSix.Wlist.size();
			int[][] storeMosLocat = new int[count][2];
			for (int i = 0; i < count; i++) {
				if (gameViewSix.Wlist.get(i) != null) {
					storeMosLocat[i][0] = gameViewSix.Wlist.get(i).locY / 8;
					storeMosLocat[i][1] = gameViewSix.Wlist.get(i).locX / 8;
				}
			}

			// ������������ӵ����ܣ�����false
			if (meetMos(i, j, storeMosLocat)) {
				Log.i("meet", "" + meetMos(i, j, storeMosLocat));
				return false;
			}

		}

		// ���ô��ڵ�·��
		if (i < map.length && j < map[0].length && map[i][j] == 1) {

			return true;
		}

		// �жϴ��ص��ϡ��¡������Ƿ�ɷ���
		if (i - 1 >= 0) {
			if (map[i - 1][j] == 1) {
				i = i - 1;

				return true;
			}
		}

		if (i + 1 < Constant.MAP1.length) {
			if (map[i + 1][j] == 1) {
				i = i + 1;

				return true;
			}
		}

		if (j - 1 >= 0) {
			if (map[i][j - 1] == 1) {
				j = j - 1;

				return true;
			}
		}

		if (j + 1 < Constant.MAP1[0].length) {

			if (map[i][j + 1] == 1) {
				j = j + 1;

				return true;
			}
		}

		return false;

	}

	// �жϴ����Ƿ�����������
	public boolean meetMos(int i, int j, int[][] MosLocat) {
		for (int n = 0; n < MosLocat.length; n++) {

			if ((i == MosLocat[n][0] && j == MosLocat[n][1])
					|| (i == MosLocat[n][0] - 1 && j == MosLocat[n][1])
					|| (i == MosLocat[n][0] + 1 && j == MosLocat[n][1])
					|| (i == MosLocat[n][0] && j == MosLocat[n][1] - 1)
					|| (i == MosLocat[n][0] && j == MosLocat[n][1] + 1))
				return true;
		}
		return false;

	}

	// ����ͼƬ�ķ���
	public void initBitmap() {
		if (gameViewOne != null) {

			levelOneBitmap[0] = BitmapFactory.decodeResource(
					gameViewOne.getResources(), R.drawable.plant1);
			levelOneBitmap[0] = PicScaleHelper.scaleToFit(levelOneBitmap[0],
					Constant.ssr.ratio);
			levelOneBitmap[1] = BitmapFactory.decodeResource(
					gameViewOne.getResources(), R.drawable.plant2);
			levelOneBitmap[1] = PicScaleHelper.scaleToFit(levelOneBitmap[1],
					Constant.ssr.ratio);
			levelOneBitmap[2] = BitmapFactory.decodeResource(
					gameViewOne.getResources(), R.drawable.plant3);
			levelOneBitmap[2] = PicScaleHelper.scaleToFit(levelOneBitmap[2],
					Constant.ssr.ratio);
			levelOneBitmap[3] = BitmapFactory.decodeResource(
					gameViewOne.getResources(), R.drawable.plant4);
			levelOneBitmap[3] = PicScaleHelper.scaleToFit(levelOneBitmap[3],
					Constant.ssr.ratio);

		} else if (gameViewTwo != null) {

			levelOneBitmap[0] = BitmapFactory.decodeResource(
					gameViewTwo.getResources(), R.drawable.plant1);
			levelOneBitmap[0] = PicScaleHelper.scaleToFit(levelOneBitmap[0],
					Constant.ssr.ratio);
			levelOneBitmap[1] = BitmapFactory.decodeResource(
					gameViewTwo.getResources(), R.drawable.plant2);
			levelOneBitmap[1] = PicScaleHelper.scaleToFit(levelOneBitmap[1],
					Constant.ssr.ratio);
			levelOneBitmap[2] = BitmapFactory.decodeResource(
					gameViewTwo.getResources(), R.drawable.plant3);
			levelOneBitmap[2] = PicScaleHelper.scaleToFit(levelOneBitmap[2],
					Constant.ssr.ratio);
			levelOneBitmap[3] = BitmapFactory.decodeResource(
					gameViewTwo.getResources(), R.drawable.plant4);
			levelOneBitmap[3] = PicScaleHelper.scaleToFit(levelOneBitmap[3],
					Constant.ssr.ratio);

		} else if (gameViewThree != null) {

			levelOneBitmap[0] = BitmapFactory.decodeResource(
					gameViewThree.getResources(), R.drawable.plant1);
			levelOneBitmap[0] = PicScaleHelper.scaleToFit(levelOneBitmap[0],
					Constant.ssr.ratio);
			levelOneBitmap[1] = BitmapFactory.decodeResource(
					gameViewThree.getResources(), R.drawable.plant2);
			levelOneBitmap[1] = PicScaleHelper.scaleToFit(levelOneBitmap[1],
					Constant.ssr.ratio);
			levelOneBitmap[2] = BitmapFactory.decodeResource(
					gameViewThree.getResources(), R.drawable.plant3);
			levelOneBitmap[2] = PicScaleHelper.scaleToFit(levelOneBitmap[2],
					Constant.ssr.ratio);
			levelOneBitmap[3] = BitmapFactory.decodeResource(
					gameViewThree.getResources(), R.drawable.plant4);
			levelOneBitmap[3] = PicScaleHelper.scaleToFit(levelOneBitmap[3],
					Constant.ssr.ratio);

		}

		else if (gameViewSix != null) {

			levelOneBitmap[0] = BitmapFactory.decodeResource(
					gameViewSix.getResources(), R.drawable.plant1);
			levelOneBitmap[0] = PicScaleHelper.scaleToFit(levelOneBitmap[0],
					Constant.ssr.ratio);
			levelOneBitmap[1] = BitmapFactory.decodeResource(
					gameViewSix.getResources(), R.drawable.plant2);
			levelOneBitmap[1] = PicScaleHelper.scaleToFit(levelOneBitmap[1],
					Constant.ssr.ratio);
			levelOneBitmap[2] = BitmapFactory.decodeResource(
					gameViewSix.getResources(), R.drawable.plant3);
			levelOneBitmap[2] = PicScaleHelper.scaleToFit(levelOneBitmap[2],
					Constant.ssr.ratio);
			levelOneBitmap[3] = BitmapFactory.decodeResource(
					gameViewSix.getResources(), R.drawable.plant4);
			levelOneBitmap[3] = PicScaleHelper.scaleToFit(levelOneBitmap[3],
					Constant.ssr.ratio);

		}

	}
}