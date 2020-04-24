package de.rezkalla.todocleararchapp.framework.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import de.rezkalla.todocleararchapp.TodoApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class,
        AppModule::class,
        DatabaseModule::class,
        ActivityBuilder::class]
)
interface AppComponent {

    fun inject(app: TodoApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}