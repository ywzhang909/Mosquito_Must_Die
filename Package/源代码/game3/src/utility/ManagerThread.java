package utility;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.OutputStream;

import com.userView.CoopView;
import com.userView.GameViewOne;

import android.bluetooth.BluetoothSocket;
import android.os.Message;
import android.util.Log;
/**
 * 
 * @author 山东大学android实验室刘昭呈
 *
 */
public class ManagerThread extends Thread {
	private  BluetoothSocket mSocket;
    private  InputStream mInStream;
    private  OutputStream mOutStream;
    private  ObjectInputStream mOjectInStream;
    private  ObjectOutputStream mOjectOutStream;
    public boolean flag=true;
    public CoopView coopView;
    public GameViewOne gameView;
    public ManagerThread(BluetoothSocket socket){
    	this.mSocket=socket;
    	Log.e("sssd", mSocket.toString());
    	try{
    	mInStream=mSocket.getInputStream();
    	mOutStream=mSocket.getOutputStream();	
    	
    	mOjectInStream=new ObjectInputStream(mInStream);
		mOjectOutStream=new ObjectOutputStream(mOutStream);
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
public void run(){
	while(flag){
	try {
		Target receive=null;
		receive=(Target)mOjectInStream.readObject();
		//发送给相应的surfaceView的handler处理
		if(coopView.currentFlag){
		Message.obtain(coopView.mHandler, 9, receive);
		}
		if(!coopView.currentFlag){
        //Message.obtain(gameView.mHandler, 0, receive);
		}
	} catch (OptionalDataException e) {
		Log.e("Object_run", "DataException");
	} catch (ClassNotFoundException e) {
		Log.e("Oject_run", "classNotFound");
	} catch (IOException e) {
		Log.e("Object_run", "IO");
	}
	}
	
}
public void write(Target t){
	try {
		mOjectOutStream.writeObject(t);
	} catch (IOException e) {
		Log.e("Oject_write", "IO");
	}
}
public void close(){
	try {
		if(mInStream!=null)
		mInStream.close();
		if(mOutStream!=null)
		mOutStream.close();
		if(mOjectInStream!=null)
		mOjectInStream.close();
		if(mOjectOutStream!=null)
		mOjectOutStream.close();
		if(mSocket!=null)
		mSocket.close();	
	} catch (IOException e) {
		Log.e("close","eorr");
	}
}
}
