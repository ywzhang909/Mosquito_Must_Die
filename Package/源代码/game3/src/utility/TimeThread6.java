package utility;

import java.util.ArrayList;

import mosquito.Mosquito;

import tool.ExpelIncense;
import tool.ExpelPlant;
import tool.Frog;
import tool.killspray;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.userView.GameViewOne;
import com.userView.GameViewSix;
import com.userView.GameViewThree;
import com.userView.GameViewTwo;
/**
 * 
 * @author ɽ����ѧandroidʵ�������ѳ�
 *
 */
public class TimeThread6 extends Thread {
	int i=1;//�����ؿ�ʱ��
	int time=0;
	int steady1=0;//�������ò�1����ʱ��
	int steady2=0;//�������ò�2����ʱ��
	int steady3=0;//�������ò�3����ʱ��
	int steady4=0;//�������ò�4����ʱ��
	int steady5=0;//�������ò�5����ʱ��
	int steady6=0;//�������ò�6����ʱ��
	int steady7=0;//�������ò�7����ʱ��
	int steady8=0;//�������ò�8����ʱ��
	int steady9=0;//�������ò�9����ʱ��
	int steady10=0;//�������ò�10����ʱ��
	
	int xiangsteady1=0;//��������1����ʱ��
	int xiangsteady2=0;//��������2����ʱ��
	int xiangsteady3=0;//��������3����ʱ��
	int xiangsteady4=0;//��������4����ʱ��
	int xiangsteady5=0;//��������5����ʱ��
	int xiangsteady6=0;//��������6����ʱ��
	int xiangsteady7=0;//��������7����ʱ��
	int xiangsteady8=0;//��������8����ʱ��
	int xiangsteady9=0;//��������9����ʱ��
	int xiangsteady10=0;//��������10����ʱ��
	
	
	
	boolean ToDate=true;//�ؿ�ʱ��
	boolean flag=true;
	
	boolean plantFlag1=false;//��ʶ�������ò�1�Ƿ�ʼ��ʱ
	boolean plantFlag2=false;//��ʶ�������ò�2�Ƿ�ʼ��ʱ
	boolean plantFlag3=false;//��ʶ�������ò�3�Ƿ�ʼ��ʱ
	boolean plantFlag4=false;//��ʶ�������ò�4�Ƿ�ʼ��ʱ
	boolean plantFlag5=false;//��ʶ�������ò�5�Ƿ�ʼ��ʱ
	boolean plantFlag6=false;//��ʶ�������ò�6�Ƿ�ʼ��ʱ
	boolean plantFlag7=false;//��ʶ�������ò�7�Ƿ�ʼ��ʱ
	boolean plantFlag8=false;//��ʶ�������ò�8�Ƿ�ʼ��ʱ
	boolean plantFlag9=false;//��ʶ�������ò�9�Ƿ�ʼ��ʱ
	boolean plantFlag10=false;//��ʶ�������ò�10�Ƿ�ʼ��ʱ
	
	
	boolean xiangFlag1=false;//��ʶ��������1�Ƿ�ʼ��ʱ
	boolean xiangFlag2=false;//��ʶ��������2�Ƿ�ʼ��ʱ
	boolean xiangFlag3=false;//��ʶ��������3�Ƿ�ʼ��ʱ
	boolean xiangFlag4=false;//��ʶ��������4�Ƿ�ʼ��ʱ
	boolean xiangFlag5=false;//��ʶ��������5�Ƿ�ʼ��ʱ
	boolean xiangFlag6=false;//��ʶ��������6�Ƿ�ʼ��ʱ
	boolean xiangFlag7=false;//��ʶ��������7�Ƿ�ʼ��ʱ
	boolean xiangFlag8=false;//��ʶ��������8�Ƿ�ʼ��ʱ
	boolean xiangFlag9=false;//��ʶ��������9�Ƿ�ʼ��ʱ
	boolean xiangFlag10=false;//��ʶ��������10�Ƿ�ʼ��ʱ
	
	
	
	
	GameViewSix gameview;
	 public Handler mhandler;
	public TimeThread6(GameViewSix gameViewSix){
		this.gameview=gameViewSix;
		mhandler=new Handler(){
			public void handleMessage(Message msg){
				switch(msg.what){
				//case1,case2��������
				case 1:
					plantFlag1=true;
					break;
				case 2:
					plantFlag2=true;
					break;
				case 3:
					plantFlag3=true;
					break;
				case 4:
					plantFlag4=true;
					break;
				case 5:
					plantFlag5=true;
					break;
				case 6:
					plantFlag6=true;
					break;							
				case 7:
					plantFlag7=true;
					break;
				case 8:
					plantFlag8=true;
					break;					
				case 9:
					plantFlag9=true;
					break;
				case 10:
					plantFlag10=true;
					break;
					
					
				case 11:
					xiangFlag1=true;
					break;
				case 12:
					xiangFlag2=true;
					break;		
				case 13:
					xiangFlag3=true;
					break;
				case 14:
					xiangFlag4=true;
					break;
				case 15:
					xiangFlag5=true;
					break;
				case 16:
					xiangFlag6=true;
					break;							
				case 17:
					xiangFlag7=true;
					break;							
				case 18:
					xiangFlag8=true;
					break;					
				case 19:
					xiangFlag9=true;
					break;
				case 20:
					xiangFlag10=true;
					break;
				}
				
			}
		};
	}
	@Override
    public void run(){
	while(ToDate){
		
		//��ʱ����һ���ֱ仯һ��
		if(time==20){
			 gameview.timer.subtractTime(1);
			 time=0;
		}
		time++;
		//���õ��ߵ�cd����
		
		
	try {
		Thread.sleep(50);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//ǰ��ֻ���ӳ���
	if(i==80){
		//gameview.isBornWenzi=true;
	}
	
	//��һ��ʱ�䵽�������ж��Ƿ���Ϸʤ���ķ���
	if(i>3600){
		ToDate=false;
	}
	i++;
	
	//��һ�����õ���
	if(plantFlag1){
		Log.e("plant", ""+plantFlag1);
		ExpelPlant temp = gameview.expelPlantList.get(0);
		

		if(temp.level==1)
			steady1+=2;
			else 
			steady1++;

		//�ڶ���ͼƬ
		if(steady1==30){
			temp.state = 1;
			gameview.expelPlantList.set(0, temp);
			}
		//������ͼƬ
		if(steady1==60){
			temp.state = 2;
			gameview.expelPlantList.set(0, temp);	
			}
		//������ͼƬ
		if(steady1==90){
			temp.state = 3;
			gameview.expelPlantList.set(0, temp);
			}
		//����ʱ��
		if(steady1==120){
			gameview.mhandler.sendEmptyMessage(1);
			plantFlag1=false;
		}
	}
	//�ڶ������õ���
	if(plantFlag2){
		
		ExpelPlant temp = gameview.expelPlantList.get(1);
		if(temp.level==1)
			steady2+=2;
			else 
			steady2+=1;

		//�ڶ���ͼƬ
		if(steady2==30){
			temp.state = 1;
			gameview.expelPlantList.set(1, temp);
		}
		//������ͼƬ
		if(steady2==60){
			temp.state = 2;
			gameview.expelPlantList.set(1, temp);
		}
		//������ͼƬ
		if(steady2==90){
			temp.state = 3;
			gameview.expelPlantList.set(1, temp);
		}
		//����ʱ��
		if(steady2==120){
			gameview.mhandler.sendEmptyMessage(2);
			plantFlag2=false;
		}
	}
	//���������õ���
		if(plantFlag3){
			
			ExpelPlant temp = gameview.expelPlantList.get(2);

			if(temp!=null){
			if(temp.level==1)
				steady3+=2;
				else if(temp.level==2)
				steady3+=1;
			}

			//�ڶ���ͼƬ
			if(steady3==30){
				temp.state = 1;
				gameview.expelPlantList.set(2, temp);
				}
			//������ͼƬ
			if(steady3==60){
				temp.state = 2;
				gameview.expelPlantList.set(2, temp);
				}
			//������ͼƬ
			if(steady3==90){
				temp.state = 3;
				gameview.expelPlantList.set(2, temp);
				}
			//����ʱ��
			if(steady3==120){
				gameview.mhandler.sendEmptyMessage(3);
				plantFlag3=false;
			}
		}
		//���ĸ����õ���
		if(plantFlag4){
			ExpelPlant temp = gameview.expelPlantList.get(3);

			if(temp.level==1)
				steady4+=2;
				else 
				steady4+=1;

			//�ڶ���ͼƬ
			if(steady4==30){
				temp.state = 1;
				gameview.expelPlantList.set(3, temp);
				}
			//������ͼƬ
			if(steady4==60){
				temp.state = 2;
				gameview.expelPlantList.set(3, temp);
				}
			//������ͼƬ
			if(steady4==90){
				temp.state = 3;
				gameview.expelPlantList.set(3, temp);
				}
			//����ʱ��
			if(steady4==120){
				gameview.mhandler.sendEmptyMessage(4);
				plantFlag4=false;
			}
		}
		//��������õ���
		if(plantFlag5){
			ExpelPlant temp = gameview.expelPlantList.get(4);

			if(temp.level==1)
				steady5+=2;
				else 
				steady5+=1;

			//�ڶ���ͼƬ
			if(steady5==30){
				temp.state = 1;
				gameview.expelPlantList.set(4, temp);
				}
			//������ͼƬ
			if(steady5==60){
				temp.state = 2;
				gameview.expelPlantList.set(4, temp);
				}
			//������ͼƬ
			if(steady5==90){
				temp.state = 3;
				gameview.expelPlantList.set(4, temp);
				}
			//����ʱ��
			if(steady5==120){
				gameview.mhandler.sendEmptyMessage(5);
				plantFlag5=false;
			}
		}
		//���������õ���
		if(plantFlag6){
			ExpelPlant temp = gameview.expelPlantList.get(5);

			if(temp.level==1)
				steady6+=2;
				else 
				steady6+=1;

			//�ڶ���ͼƬ
			if(steady6==30){
				temp.state = 1;
				gameview.expelPlantList.set(5, temp);
				}
			//������ͼƬ
			if(steady6==60){
				temp.state = 2;
				gameview.expelPlantList.set(5, temp);
				}
			//������ͼƬ
			if(steady6==90){
				temp.state = 3;
				gameview.expelPlantList.set(5, temp);
				}
			//����ʱ��
			if(steady6==120){
				gameview.mhandler.sendEmptyMessage(6);
				plantFlag6=false;
			}
		}
		//���߸����õ���
			if(plantFlag7){
					ExpelPlant temp = gameview.expelPlantList.get(6);

					if(temp.level==1)
						steady7+=2;
						else 
						steady7+=1;

					//�ڶ���ͼƬ
					if(steady7==30){
						temp.state = 1;
						gameview.expelPlantList.set(6, temp);
						}
					//������ͼƬ
					if(steady7==60){
						temp.state = 2;
						gameview.expelPlantList.set(6, temp);
						}
					//������ͼƬ
					if(steady7==90){
						temp.state = 3;
						gameview.expelPlantList.set(6, temp);
						}
					//����ʱ��
					if(steady7==120){
						gameview.mhandler.sendEmptyMessage(7);
						plantFlag7=false;
					}
				}
				
				//�ڰ˸����õ���
				if(plantFlag8){
					
					ExpelPlant temp = gameview.expelPlantList.get(7);

					if(temp.level==1)
						steady8+=2;
						else 
							steady8+=1;

					//�ڶ���ͼƬ
					if(steady8==30){
						temp.state = 1;
						gameview.expelPlantList.set(7, temp);
						}
					//������ͼƬ
					if(steady8==60){
						temp.state = 2;
						gameview.expelPlantList.set(7, temp);
						}
					//������ͼƬ
					if(steady8==90){
						temp.state = 3;
						gameview.expelPlantList.set(7, temp);
						}
					//����ʱ��
					if(steady8==120){
						gameview.mhandler.sendEmptyMessage(8);
						plantFlag8=false;
					}
				}
				
				//�ھŸ����õ���
				if(plantFlag9){
					ExpelPlant temp = gameview.expelPlantList.get(8);

					if(temp.level==1)
						steady9+=2;
						else 
							steady9+=1;

					//�ڶ���ͼƬ
					if(steady9==30){
						temp.state = 1;
						gameview.expelPlantList.set(8, temp);
						}
					//������ͼƬ
					if(steady9==60){
						temp.state = 2;
						gameview.expelPlantList.set(8, temp);
						}
					//������ͼƬ
					if(steady9==90){
						temp.state = 3;
						gameview.expelPlantList.set(8, temp);
						}
					//����ʱ��
					if(steady9==120){
						gameview.mhandler.sendEmptyMessage(9);
						plantFlag9=false;
					}
				}
				
				//��ʮ�����õ���
				if(plantFlag10){
					ExpelPlant temp = gameview.expelPlantList.get(9);

					if(temp.level==1)
						steady10+=2;
						else 
							steady10+=1;

					//�ڶ���ͼƬ
					if(steady10==30){
						temp.state = 1;
						gameview.expelPlantList.set(9, temp);
						}
					//������ͼƬ
					if(steady10==60){
						temp.state = 2;
						gameview.expelPlantList.set(9, temp);
						}
					//������ͼƬ
					if(steady10==90){
						temp.state = 3;
						gameview.expelPlantList.set(9, temp);
						}
					//����ʱ��
					if(steady10==120){
						gameview.mhandler.sendEmptyMessage(10);
						plantFlag10=false;
					}
				}
				
		//��һ���������
		if(xiangFlag1){
			ExpelIncense temp = gameview.incenseList.get(0);

			if(temp.level==1)
				xiangsteady1+=2;
				else 
				xiangsteady1+=1;

			//�ڶ���ͼƬ
			if(xiangsteady1==30){
				temp.state = 1;
				gameview.incenseList.set(0, temp);
				}
			//������ͼƬ
			if(xiangsteady1==60){
				temp.state = 2;
				gameview.incenseList.set(0, temp);
				}
			//������ͼƬ
			if(xiangsteady1==90){
				temp.state = 3;
				gameview.incenseList.set(0, temp);
				}
			//����ʱ��
			if(xiangsteady1==120){
				gameview.mhandler.sendEmptyMessage(11);
				xiangFlag1=false;
			}
		}
		//�ڶ����������
				if(xiangFlag2){
					ExpelIncense temp = gameview.incenseList.get(1);

					if(temp.level==1)
						xiangsteady2+=2;
						else 
						xiangsteady2+=1;

					//�ڶ���ͼƬ
					if(xiangsteady2==30){
						temp.state = 1;
						gameview.incenseList.set(1, temp);
						}
					//������ͼƬ
					if(xiangsteady2==60){
						temp.state = 2;
						gameview.incenseList.set(1, temp);
						}
					//������ͼƬ
					if(xiangsteady2==90){
						temp.state = 3;
						gameview.incenseList.set(1, temp);
						}
					//����ʱ��
					if(xiangsteady2==120){
						gameview.mhandler.sendEmptyMessage(12);
						xiangFlag2=false;
					}
				}
				//�������������
				if(xiangFlag3){
					ExpelIncense temp = gameview.incenseList.get(2);

					if(temp.level==1)
						xiangsteady3+=2;
						else 
						xiangsteady3+=1;

					//�ڶ���ͼƬ
					if(xiangsteady3==30){
						temp.state = 1;
						gameview.incenseList.set(2, temp);
						}
					//������ͼƬ
					if(xiangsteady3==60){
						temp.state = 2;
						gameview.incenseList.set(2, temp);
						}
					//������ͼƬ
					if(xiangsteady3==90){
						temp.state = 3;
						gameview.incenseList.set(2, temp);
						}
					//����ʱ��
					if(xiangsteady3==120){
						gameview.mhandler.sendEmptyMessage(13);
						xiangFlag3=false;
					}
				}
				//���ĸ��������
				if(xiangFlag4){
					ExpelIncense temp = gameview.incenseList.get(3);

					if(temp.level==1)
						xiangsteady4+=2;
						else 
						xiangsteady4+=1;

					//�ڶ���ͼƬ
					if(xiangsteady4==30){
						temp.state = 1;
						gameview.incenseList.set(3, temp);
						}
					//������ͼƬ
					if(xiangsteady4==60){
						temp.state = 2;
						gameview.incenseList.set(3, temp);
						}
					//������ͼƬ
					if(xiangsteady4==90){
						temp.state = 3;
						gameview.incenseList.set(3, temp);
						}
					//����ʱ��
					if(xiangsteady4==120){
						gameview.mhandler.sendEmptyMessage(14);
						xiangFlag4=false;
					}
				}
				
				
				
				//������������
				if(xiangFlag5){
					ExpelIncense temp = gameview.incenseList.get(4);

					if(temp.level==1)
						xiangsteady5+=2;
						else 
						xiangsteady5+=1;

					//�ڶ���ͼƬ
					if(xiangsteady5==30){
						temp.state = 1;
						gameview.incenseList.set(4, temp);
						}
					//������ͼƬ
					if(xiangsteady5==60){
						temp.state = 2;
						gameview.incenseList.set(4, temp);
						}
					//������ͼƬ
					if(xiangsteady5==90){
						temp.state = 3;
						gameview.incenseList.set(4, temp);
						}
					//����ʱ��
					if(xiangsteady5==120){
						gameview.mhandler.sendEmptyMessage(15);
						xiangFlag5=false;
					}
				}
				
				//�������������
				if(xiangFlag6){
					ExpelIncense temp = gameview.incenseList.get(5);

					if(temp.level==1)
						xiangsteady6+=2;
						else 
							xiangsteady6+=1;

					//�ڶ���ͼƬ
					if(xiangsteady6==30){
						temp.state = 1;
						gameview.incenseList.set(5, temp);
						}
					//������ͼƬ
					if(xiangsteady6==60){
						temp.state = 2;
						gameview.incenseList.set(5, temp);
						}
					//������ͼƬ
					if(xiangsteady6==90){
						temp.state = 3;
						gameview.incenseList.set(5, temp);
						}
					//����ʱ��
					if(xiangsteady6==120){
						gameview.mhandler.sendEmptyMessage(16);
						xiangFlag6=false;
					}
				}
				//���߸��������
				if(xiangFlag7){
					ExpelIncense temp = gameview.incenseList.get(6);

					if(temp.level==1)
						xiangsteady7+=2;
						else 
							xiangsteady7+=1;

					//�ڶ���ͼƬ
					if(xiangsteady7==30){
						temp.state = 1;
						gameview.incenseList.set(6, temp);
						}
					//������ͼƬ
					if(xiangsteady7==60){
						temp.state = 2;
						gameview.incenseList.set(6, temp);
						}
					//������ͼƬ
					if(xiangsteady7==90){
						temp.state = 3;
						gameview.incenseList.set(6, temp);
						}
					//����ʱ��
					if(xiangsteady7==120){
						gameview.mhandler.sendEmptyMessage(17);
						xiangFlag7=false;
					}
				}
				//�ڰ˸��������
				if(xiangFlag8){
					ExpelIncense temp = gameview.incenseList.get(7);

					if(temp.level==1)
						xiangsteady8+=2;
						else 
							xiangsteady8+=1;

					//�ڶ���ͼƬ
					if(xiangsteady8==30){
						temp.state = 1;
						gameview.incenseList.set(7, temp);
						}
					//������ͼƬ
					if(xiangsteady8==60){
						temp.state = 2;
						gameview.incenseList.set(7, temp);
						}
					//������ͼƬ
					if(xiangsteady8==90){
						temp.state = 3;
						gameview.incenseList.set(7, temp);
						}
					//����ʱ��
					if(xiangsteady8==120){
						gameview.mhandler.sendEmptyMessage(18);
						xiangFlag8=false;
					}
				}
				
				//�ھŸ��������
				if(xiangFlag9){
					ExpelIncense temp = gameview.incenseList.get(8);

					if(temp.level==1)
						xiangsteady9+=2;
						else 
							xiangsteady9+=1;

					//�ڶ���ͼƬ
					if(xiangsteady9==30){
						temp.state = 1;
						gameview.incenseList.set(8, temp);
						}
					//������ͼƬ
					if(xiangsteady9==60){
						temp.state = 2;
						gameview.incenseList.set(8, temp);
						}
					//������ͼƬ
					if(xiangsteady9==90){
						temp.state = 3;
						gameview.incenseList.set(8, temp);
						}
					//����ʱ��
					if(xiangsteady9==120){
						gameview.mhandler.sendEmptyMessage(19);
						xiangFlag9=false;
					}
				}
				
				//��ʮ���������
				if(xiangFlag10){
					ExpelIncense temp = gameview.incenseList.get(9);

					if(temp.level==1)
						xiangsteady10+=2;
						else 
							xiangsteady10+=1;

					//�ڶ���ͼƬ
					if(xiangsteady10==30){
						temp.state = 1;
						gameview.incenseList.set(9, temp);
						}
					//������ͼƬ
					if(xiangsteady10==60){
						temp.state = 2;
						gameview.incenseList.set(9, temp);
						}
					//������ͼƬ
					if(xiangsteady10==90){
						temp.state = 3;
						gameview.incenseList.set(9, temp);
						}
					//����ʱ��
					if(xiangsteady10==120){
						gameview.mhandler.sendEmptyMessage(20);
						xiangFlag10=false;
					}
				}
	
				
	}
}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void setToDate(boolean flag){
		this.ToDate=flag;
	}
	
}
