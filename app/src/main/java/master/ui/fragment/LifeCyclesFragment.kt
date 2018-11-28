package master.ui.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import master.ui.R
import master.ui.lifecycles.CallBack
import master.ui.lifecycles.MyObserver

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * SupportActivity和Fragment都已经实现了LifecycleOwner, 但是仅仅在AndroidN中
 * 保持UI控制器（活动和片段）尽可能，不应该在活动或片段中直接获取数据；相反，使用VIEW模型来做这件事，并观察LiveData 对象，以将更改反映回视图
 * 尝试编写数据驱动的UI，其中UI控制器的职责是在数据更改时更新视图，或将用户操作通知回VIEW模型。
 * 将数据逻辑放在VIEW模型类中。VIEW模型应充当UI控制器和应用程序其余部分之间的连接器。
 * 使用数据绑定来维护视图和UI控制器之间的干净接口。如BufferKnife
 * 避免引用VIEW模型中的视图或活动上下文
 */
class LifeCyclesFragment : Fragment() {
//    // ============== lifecycle =============
//    lateinit var lifecycleRegistry: LifecycleRegistry
//
//    override fun getLifecycle(): Lifecycle {
//        return lifecycleRegistry
//    }
//
//    var myObserver = MyObserver(lifecycle, object : CallBack {
//        override fun update(string: String) {
//            Toast.makeText(activity, string, Toast.LENGTH_SHORT).show()
//        }
//    })
//    // =========================================

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        // ============== lifecycle =============
//        lifecycleRegistry = LifecycleRegistry(this)
//        lifecycle.addObserver(myObserver)
//
//        lifecycleRegistry.markState(Lifecycle.State.CREATED)

        var myObserver = MyObserver(lifecycle, object : CallBack {
            override fun update(s: String) {
                Toast.makeText(activity, s, Toast.LENGTH_SHORT).show()
            }
        })
        lifecycle.addObserver(myObserver)
        // =========================================
    }

    override fun onResume() {
        super.onResume()
//        lifecycleRegistry.markState(Lifecycle.State.RESUMED)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_life_cycles, container, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
//        if (context is OnFragmentInteractionListener) {
//            listener = context
//        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
//        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LifeCyclesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
