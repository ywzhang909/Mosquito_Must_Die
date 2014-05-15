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
	 * @驱蚊草类
	 */
	GameViewOne gameViewOne;
	GameViewTwo gameViewTwo;
	GameViewThree gameViewThree;
	GameViewSix gameViewSix;

	public int level; // 等级
	public int lasttime = 100; // 持续时间
	private int i;// 放置点所在矩阵的行
	private int j;// 放置点所在矩阵的列
	private Bitmap[] levelOneBitmap = new Bitmap[4];// 一级驱蚊草图片
	private Bitmap[] levelTwoBitmap = new Bitmap[4];// 二级驱蚊草图片
	public int state = 0;

	/*
	 * 构造方法
	 */
	// 第一关构造方法
	public ExpelPlant(GameViewOne gameViewOne, int level, int pressX, int pressY) {

		this.gameViewOne = gameViewOne;
		this.level = level;
		this.lasttime = lasttime * level;
		j = pressX / Constant.G_SIZE;
		i = pressY / Constant.G_SIZE;

		initBitmap();
	}

	// 第二关构造方法
	public ExpelPlant(GameViewTwo gameViewTwo, int level, int pressX, int pressY) {
		this.gameViewTwo = gameViewTwo;
		this.level = level;
		this.lasttime = lasttime * level;
		j = pressX / Constant.G_SIZE;
		i = pressY / Constant.G_SIZE;

		initBitmap();
	}

	// 第三关构造方法
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

	// 发挥作用的方法，返回变化后的地图
	public int[][] effect(int[][] map) {

		if (map[i][j] == 1)
			map[i][j] = 0;

		return map;

	}

	// 驱蚊草消失的方法
	public int[][] reMap(int[][] map) {

		// 设置驱蚊香状态
		if (map[i][j] == 0)
			map[i][j] = 1;
		return map;
	}

	// 绘制自己
	public void drawSelf(Canvas canvas, Paint paint) {

		int onX = j * 40;
		int onY = i * 40;
		canvas.drawBitmap(levelOneBitmap[state], onX, onY, paint);

	}

	// 判断工具的放置位置是否有效
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

			// 如果触点在蚊子的四周，返回false
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

			// 如果触点在蚊子的四周，返回false
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

			// 如果触点在蚊子的四周，返回false
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

			// 如果触点在蚊子的四周，返回false
			if (meetMos(i, j, storeMosLocat)) {
				Log.i("meet", "" + meetMos(i, j, storeMosLocat));
				return false;
			}

		}

		// 正好触在道路上
		if (i < map.length && j < map[0].length && map[i][j] == 1) {

			return true;
		}

		// 判断触控点上、下、左、右是否可放置
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

	// 判断触点是否在蚊子四周
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

	// 加载图片的方法
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