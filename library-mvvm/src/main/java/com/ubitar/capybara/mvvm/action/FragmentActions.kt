package com.ubitar.capybara.mvvm.action

import android.os.Bundle
import me.yokeyword.fragmentation.ISupportFragment

class FragmentActions {

    val onBackPressedSupportAction: OnBackPressedSupportAction by lazy { OnBackPressedSupportAction() }
    val postAction: PostAction by lazy { PostAction() }
    val setFragmentResultAction: SetFragmentResultAction by lazy { SetFragmentResultAction() }
    val putNewBundleAction: PutNewBundleAction by lazy { PutNewBundleAction() }
    val loadRootFragmentAction: LoadRootFragmentAction by lazy { LoadRootFragmentAction() }
    val startAction: StartAction by lazy { StartAction() }
    val startForResultAction: StartForResultAction by lazy { StartForResultAction() }
    val startWithPopAction: StartWithPopAction by lazy { StartWithPopAction() }
    val startWithPopToAction: StartWithPopToAction by lazy { StartWithPopToAction() }
    val replaceFragmentAction: ReplaceFragmentAction by lazy { ReplaceFragmentAction() }
    val popAction: PopAction by lazy { PopAction() }
    val popToAction: PopToAction by lazy { PopToAction() }

    class OnBackPressedSupportAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "ISupportFragment.onBackPressedSupport()"
        }
    }

    class PopAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "ISupportFragment.pop()方法"
        }

    }

    class PostAction : SingleLiveAction<Runnable>() {
        override fun describe(): String {
            return "Context.post()"
        }
    }

    class StartAction : SingleLiveAction<StartAction.Start>() {

        override fun describe(): String {
            return "ISupportFragment.start()"
        }

        data class Start(
            val toFragment: ISupportFragment,
            val fromParentFragment: Boolean = false,
            val launchMode: Int? = null
        )
    }

    class LoadRootFragmentAction : SingleLiveAction<LoadRootFragmentAction.LoadRootFragment>() {
        override fun describe(): String {
            return "Fragment.loadRootFragment()"
        }

        data class LoadRootFragment(
            val containerId: Int,
            val toFragment: ISupportFragment,
            val addToBackStack: Boolean? = null,
            val allowAnim: Boolean? = null
        )

    }

    class PopToAction : SingleLiveAction<PopToAction.PopTo>() {
        override fun describe(): String {
            return "ISupportFragment.popTo()方法"
        }

        data class PopTo(
            val targetFragmentClass: Class<*>,
            val includeTargetFragment: Boolean
        )

    }

    class PutNewBundleAction : SingleLiveAction<Bundle>() {

        override fun describe(): String {
            return "Fragment.putNewBundle()"
        }

    }

    class ReplaceFragmentAction : SingleLiveAction<ReplaceFragmentAction.ReplaceFragment>() {

        override fun describe(): String {
            return "ISupportFragment.replaceFragment()"
        }

        data class ReplaceFragment(
            val toFragment: ISupportFragment,
            val addToBackStack: Boolean
        )

    }

    class SetFragmentResultAction : SingleLiveAction<SetFragmentResultAction.SetFragmentResult>() {

        override fun describe(): String {
            return "Fragment.setFragmentResult()"
        }

        data class SetFragmentResult(
            val resultCode: Int,
            val data: Bundle
        )

    }

    class StartForResultAction : SingleLiveAction<StartForResultAction.StartForResult>() {

        override fun describe(): String {
            return "ISupportFragment.startForResult()"
        }

        data class StartForResult(
            val toFragment: ISupportFragment,
            val requestCode: Int
        )

    }

    class StartWithPopAction : SingleLiveAction<ISupportFragment>() {

        override fun describe(): String {
            return "ISupportFragment.startWithPop()"
        }

    }

    class StartWithPopToAction : SingleLiveAction<StartWithPopToAction.StartWithPopTo>() {

        override fun describe(): String {
            return "ISupportFragment.startWithPopTo()"
        }

        data class StartWithPopTo(
            val toFragment: ISupportFragment,
            val targetFragmentClass: Class<*>,
            val includeTargetFragment: Boolean
        )

    }

}