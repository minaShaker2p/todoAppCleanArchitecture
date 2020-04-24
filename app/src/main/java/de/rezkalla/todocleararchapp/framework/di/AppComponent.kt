package de.rezkalla.todocleararchapp.framework.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import de.rezkalla.todocleararchapp.TodoApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
        AppModule::class,
        DatabaseModule::class,
        ActivityBuilder::class]
)
interface AppComponent {

    fun inject(app: TodoApplication)

    /* fun inject(noteViewModel: NoteViewModel)

     fun inject(listViewModel: ListViewModel)*/

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}