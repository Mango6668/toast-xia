package xyz.chimiandroid.toastxia.tool

import android.content.Context
import android.view.accessibility.AccessibilityManager

/**
 * 无障碍工具类
 * @author 赤米
 * @version 1.0
 * @since JDK 17
 * @see android.view.accessibility.AccessibilityManager
 */
class ReadUtils {
    object ReadUtils {

        /**
         * 此方法用于判断是否开启了屏幕阅读器
         * @param context 上下文
         * @return 是否开启了屏幕阅读器的布尔值，开启返回true，否则亦然
         */
        @JvmStatic
        fun isScreenReader(context: Context): Boolean {
            return checkEnabled(context) && checkTouch(context)
        }

        /**
         * 此方法用于判断是否开启无障碍服务
         * @param context 上下文
         * @return 是否开启无障碍服务的布尔值，开启返回true，否则亦然
         */
        private fun checkEnabled(context: Context): Boolean {
            // 获取无障碍管理器实例
            val manager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager?
            // 是否启用无障碍服务
            return manager!!.isEnabled
        }

        /**
         * 此方法用于判断是否启用触摸浏览
         * @param context 上下文
         * @return 是否启用触摸浏览的结果，开启返回true，否则亦然
         */
        private fun checkTouch(context: Context): Boolean {
            // 获取无障碍管理器实例
            val manager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager?
            // 是否启用触摸浏览
            return manager!!.isTouchExplorationEnabled
        }
    }
}