package de.rezkalla.todocleararchapp

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import de.rezkalla.todocleararchapp.framework.di.DaggerAppComponent
import javax.inject.Inject


class TodoApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    /*  companion object {
          private var component: ApplicationComponent? = null

          fun getViewModelComponent(): ApplicationComponent? {
              return component
          }
      }*/

    override fun onCreate() {
        super.onCreate()
        /*     component = DaggerViewModelComponent.builder()
                 .applicationModule(ApplicationModule(applicationContext))
                 .databaseModule(DatabaseModule(applicationContext))
                 .build()*/
        DaggerAppComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }


}