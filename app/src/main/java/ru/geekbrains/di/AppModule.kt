package ru.geekbrains.di

import dagger.Module
import ru.geekbrains.mvpuser.di.UserComponent

@Module(subcomponents = [UserComponent::class])
class AppModule() {
}