package app.olauncher

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val appContext = application.applicationContext
    private val prefs = Prefs(appContext)

    private val selectedApp = MutableLiveData<AppModelWithFlag>()
    val refreshHome = MutableLiveData<Any>()

    fun selectedApp(appModel: AppModel, flag: Int) {
        when (flag) {
            Constants.FLAG_LAUNCH_APP -> {
                launchApp(appModel.appPackage)
            }
            Constants.FLAG_SET_HOME_APP_1 -> {
                prefs.appName1 = appModel.appLabel
                prefs.appPackage1 = appModel.appPackage
                refreshHome(true)
            }
            Constants.FLAG_SET_HOME_APP_2 -> {
                prefs.appName2 = appModel.appLabel
                prefs.appPackage2 = appModel.appPackage
                refreshHome(true)
            }
            Constants.FLAG_SET_HOME_APP_3 -> {
                prefs.appName3 = appModel.appLabel
                prefs.appPackage3 = appModel.appPackage
                refreshHome(true)
            }
            Constants.FLAG_SET_HOME_APP_4 -> {
                prefs.appName4 = appModel.appLabel
                prefs.appPackage4 = appModel.appPackage
                refreshHome(true)
            }
        }
        selectedApp.value = AppModelWithFlag(appModel, flag)
    }

    private fun refreshHome(value: Any) {
        refreshHome.value = value
    }

    private fun launchApp(packageName: String) {
        val intent: Intent? = appContext.packageManager.getLaunchIntentForPackage(packageName)
        intent?.addCategory(Intent.CATEGORY_LAUNCHER)
        appContext.startActivity(intent)
    }
}

data class AppModelWithFlag(
    val appModel: AppModel,
    val flag: Int
)