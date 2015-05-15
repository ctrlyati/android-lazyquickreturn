# android-lazyquickreturn
##introduction
Do easy Quick Return with this project

![Image of LazyQuickReturn](http://dev.ctrlyati.in.th/lazyQuickReturn/lazyQuickReturn.gif) 

##limitation
The observe class cannot `setOnTouchListener()` because QuickReturnHelper will `setOnTouchListener()` on it.

##Usage
just import this project and use `QuickReturnHelper` class

```java
public class someClass {
    
    //...
    public void useQuickReturn(){
        QuickReturnHelper qrHelper = new QuickReturnHelper(mContext, mObserverView);
        QuickReturnListner qrListener = new SimpleTopElementQuickReturnListener(mQuickReturnView, mContext);
        qrHelper.registerAction(qrListener);
    
    }
    //..
}
```

##Customization
You can custom QuickReturn Behavior by implements `QuickReturnListener` or `SimpleQuickReturnListener` to custom it's behavior


by Yati Dumrongsukit
