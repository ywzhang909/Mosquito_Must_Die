package utility;



/**
 * 
 * @author ɽ����ѧandroidʵ�������ѳ�
 *
 */
//���ڼ�����Ļ���űȵĹ�����
public class ScreenScaleUtil {
	
	static final float sHpWidth=800;//ԭʼ�����Ŀ��
	static final float sHpHeight=480;//ԭʼ�����ĸ߶�
	static final float whHpRatio=sHpWidth/sHpHeight;//ԭʼ�����Ŀ�߱�

	public static ScreenScaleResult calScale
	(
		float targetWidth,	//Ŀ����
		float targetHeight	//Ŀ��߶�
	)
	{
		ScreenScaleResult result=null;
		
		//���к�������ؼ���
		
		//����Ŀ��Ŀ�߱�
		float targetRatio=targetWidth/targetHeight;
	
		//���Ŀ���߱ȴ���ԭʼ��߱ȣ�����Ŀ���߱�Ϊ׼��������ratio���Ը��������
		if(targetRatio>whHpRatio){
			//�Ը��������
			float ratio=targetHeight/sHpHeight;
			//���������Ļ�Ŀ�Ϊ׼�Ŀ�
			float realTargetWidth=ratio*sHpWidth;
			
			//ͼƬ����ļ���
			float lcuX=(targetWidth-realTargetWidth)/2.0f;
		    float lcuY=0;
			result=new ScreenScaleResult((int)lcuX,(int) lcuY, ratio);
		    return result;
		}
		//���Ŀ���߱�С��ԭʼ��߱ȣ�����Ŀ���߱�Ϊ׼��������ratio���Կ��������
		else{
			//�Կ��������
			float ratio=targetWidth/sHpWidth;
			//���������Ļ�ĸ�Ϊ׼�ĸ�
			float realTargetHeight=ratio*sHpHeight;
			//ͼƬ����ļ���
			float lcuY=(targetWidth-realTargetHeight)/2.0f;
		    float lcuX=0;
		    result=new ScreenScaleResult((int)lcuX,(int) lcuY, ratio);
		    return result;
		}
	}
}
