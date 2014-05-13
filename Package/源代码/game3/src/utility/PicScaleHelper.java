package utility;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
/**
 * 
 * @author 山东大学android实验室刘昭呈
 *
 */
//用于处理 图片缩放的工具类
public class PicScaleHelper {

	//从资源中加载图片
	public static Bitmap LoadBitmap(Resources res,int picId)
	   {
		   Bitmap result=BitmapFactory.decodeResource(res, picId);
		   return result;
	   }
	 
	
	/*
	 * 下面有两个缩放方法:
	 * 
	 * 第一个是等比缩放 
	 * 
	 * 第二个是不等比缩放
	 * 
	 * 请大家根据需要选择使用 
	 * 
	 */
	
	 
	public static Bitmap scaleToFit(Bitmap bm,float ratio)//ratio是缩放比
	   {
	   	float width = bm.getWidth(); //图片宽度
	   	float height = bm.getHeight();//图片高度	
	   	
	   	Matrix m1 = new Matrix(); 
	   	m1.postScale(ratio, ratio); //缩放矩阵  	
	   	Bitmap bmResult = Bitmap.createBitmap(bm, 0, 0, (int)width, (int)height, m1, true);//创建缩放后的位图        	
	   	return bmResult;
	   }
	
	
	public static Bitmap FullScreenScale(Bitmap bm,float wRatio,float hRatio){
		float width = bm.getWidth(); //图片宽度
	   	float height = bm.getHeight();//图片高度	
	   	Matrix m1 = new Matrix(); 
	   	m1.postScale(wRatio, hRatio);   	
	   	Bitmap bmResult = Bitmap.createBitmap(bm, 0, 0, (int)width, (int)height, m1, true);//声明位图        	
	   	return bmResult;
	}
}
