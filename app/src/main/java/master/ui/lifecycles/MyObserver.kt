package master.ui.lifecycles

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent

/**
 * 1.创建MyObserver 继承 LifecycleObserver
 * 2.传入Lifecycles和Callback，Lifecycles为绑定Activity的生命周期，CallBack用于处理数据后的回调
 * 3.使用注解在每个方法上标记相应的生命周期
 */
class MyObserver(var lifecycle: Lifecycle, var callback: CallBack) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    public fun connectOnCreate() {
        p("connectOnCreate")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public fun connectOnResume() {
        p("connectOnResume")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public fun disConnectOnDestroy() {
        p("disConnectOnDestroy")
    }

    fun p(string: String) {
        callback.update(string)
    }
}
