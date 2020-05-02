package com.capybara.mvvm.action

class DialogActions {
    val postAction: PostAction by lazy { PostAction() }
    val dismissAction: DismissAction by lazy { DismissAction() }
    val dismissAllowingStateLossAction: DismissAllowingStateLossAction by lazy { DismissAllowingStateLossAction() }

    class PostAction : SingleLiveAction<Runnable>() {
        override fun describe(): String {
            return "Context.post()"
        }
    }


    class DismissAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "DialogFragment.dismiss()"
        }
    }

    class DismissAllowingStateLossAction : SingleLiveAction<Any>() {
        override fun describe(): String {
            return "DialogFragment.dismissAllowingStateLoss()"
        }
    }
}