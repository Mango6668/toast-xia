package xyz.chimiandroid.toastxia.tool

import android.content.Context
import android.view.accessibility.AccessibilityManager

/**
 * 读屏工具类
 * @author 赤米
 * @version 1.0
 * @since JDK 17
 */
class ReadUtils {
    object ReadUtils {

        //是否是屏幕阅读器用户
        @JvmStatic
        fun isScreenReader(context: Context): Boolean {
            return checkEnabled(context) && checkTouch(context)
        }

        /**
         * 此方法用于判断是否开启无障碍服务
         * @param context 上下文
         */
        private fun checkEnabled(context: Context): Boolean {
            //获取无障碍管理器实例
            val manager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager?
            //是否启用无障碍服务
            return manager!!.isEnabled
        }

        /**
         * 此方法用于判断是否启用触摸浏览
         * @param context 上下文
         */
        private fun checkTouch(context: Context): Boolean {
            //获取无障碍管理器实例
            val manager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager?
            //是否启用触摸浏览
            return manager!!.isTouchExplorationEnabled
        }
    }
}