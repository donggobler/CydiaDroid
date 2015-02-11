package u.can.i.up.hook;

import android.util.Log;

import com.saurik.substrate.MS;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import u.can.i.up.cydiadroid.Main;

/**
 * Created by lczgywzyy on 2015/2/4.
 */
public class MyHelloWorldHook {

    public static void MyHookStart(){
        MS.hookClassLoad("u.can.i.up.helloworld.MyHelloWorld",
                new MS.ClassLoadHook() {
                    public void classLoaded(Class<?> myinstance) {
                        Method MyCallFunc;
                        try {
                            MyCallFunc = myinstance.getDeclaredMethod("myRealCall", new Class<?>[0]);
                            MyCallFunc.setAccessible(true);
                        } catch (NoSuchMethodException e) {
                            MyCallFunc = null;
                            e.printStackTrace();
                        }
                        MS.hookMethod(myinstance, MyCallFunc, new MS.MethodAlteration() {
                                public Object invoked(Object obj, Object... args) throws Throwable {
                                    Log.i("UCanIUp", "MyCALLLLLLLLLLLLLLLLLLLLL!");
                                    return invoke(obj, args);
                                }
                            });
                    }
                });
    }
}
