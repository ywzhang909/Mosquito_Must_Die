package utility;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
/**
 * 
 * @author ɽ����ѧandroidʵ�������ѳ�
 *
 */
//���ڴ��� ͼƬ���ŵĹ�����
public class PicScaleHelper {

	//����Դ�м���ͼƬ
	public static Bitmap LoadBitmap(Resources res,int picId)
	   {
		   Bitmap result=BitmapFactory.decodeResource(res, picId);
		   return result;
	   }
	 
	
	/*
	 * �������������ŷ���:
	 * 
	 * ��һ���ǵȱ����� 
	 * 
	 * �ڶ����ǲ��ȱ�����
	 * 
	 * ���Ҹ�����Ҫѡ��ʹ�� 
	 * 
	 */
	
	 
	public static Bitmap scaleToFit(Bitmap bm,float ratio)//ratio�����ű�
	   {
	   	float width = bm.getWidth(); //ͼƬ���
	   	float height = bm.getHeight();//ͼƬ�߶�	
	   	
	   	Matrix m1 = new Matrix(); 
	   	m1.postScale(ratio, ratio); //���ž���  	
	   	Bitmap bmResult = Bitmap.createBitmap(bm, 0, 0, (int)width, (int)height, m1, true);//�������ź��λͼ        	
	   	return bmResult;
	   }
	
	
	public static Bitmap FullScreenScale(Bitmap bm,float wRatio,float hRatio){
		float width = bm.getWidth(); //ͼƬ���
	   	float height = bm.getHeight();//ͼƬ�߶�	
	   	Matrix m1 = new Matrix(); 
	   	m1.postScale(wRatio, hRatio);   	
	   	Bitmap bmResult = Bitmap.createBitmap(bm, 0, 0, (int)width, (int)height, m1, true);//����λͼ        	
	   	return bmResult;
	}
}
